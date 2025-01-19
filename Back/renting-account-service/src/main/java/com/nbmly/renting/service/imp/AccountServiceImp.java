package com.nbmly.renting.service.imp;

import com.alibaba.fastjson.JSONObject;
import com.nbmly.renting.BusinessException;
import com.nbmly.renting.account.model.AccountRegisterDTO;
import com.nbmly.renting.common.AccountErrorCode;
import com.nbmly.renting.service.UserService;
import com.nbmly.renting.util.EncryptUtil;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.account.model.AccountLoginDTO;
import com.nbmly.renting.mapper.AccountMapper;
import com.nbmly.renting.pojo.Account;
import com.nbmly.renting.service.AccountService;
import com.nbmly.renting.util.MD5DES;
import com.nbmly.renting.common.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public RestResponse<AccountDTO> login(AccountLoginDTO accountLoginDTO) {
        // 数据解码
        accountLoginDTO.setUsername(EncryptUtil.decodeUTF8StringBase64(accountLoginDTO.getUsername()));
        try {
            // 加密
            accountLoginDTO.setPassword(MD5DES.encrypt(accountLoginDTO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 查询数据库
        Account account = accountMapper.login(accountLoginDTO);
        if (account == null) {
            return null;
        }

        // 获取权限
        RestResponse<List<String>> response = userService.lookupPowers(account.getId());

        // 封装数据
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setCellPhone(account.getCellPhone());
        accountDTO.setState(account.getState());
        accountDTO.setDomain(account.getDomain());
        accountDTO.setPowers(response.getData());

        // 返回结果
        return RestResponse.success(accountDTO);
    }

    @Override
    @Transactional
    public RestResponse<String> register(AccountRegisterDTO accountRegisterDTO) {
        // 名称是否重复
        if (accountMapper.isAccountUsername(accountRegisterDTO.getUsername()) > 0) {
            throw new BusinessException(AccountErrorCode.E_130111);
        }
        // 注册
        try {
            // 密码加密
            accountRegisterDTO.setPassword(MD5DES.encrypt(accountRegisterDTO.getPassword()));
            if (accountMapper.register(accountRegisterDTO) <= 0) {
                // 注册失败异常
                throw new BusinessException(AccountErrorCode.E_130101);
            }
            userService.powerRegister(accountRegisterDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            // 注册失败异常
            throw new BusinessException(AccountErrorCode.E_130101);
        }

        // 返回结果
        return RestResponse.success("注册成功");
    }

    @Cacheable(cacheNames = { "account" }, key = "#id")
    @Override
    public Account getById(Long id) {
        System.out.println("查询数据库了");
        return accountMapper.getById(id);
    }

    @CachePut(cacheNames = { "account" }, key = "#accountRegisterDTO.id", unless = "#result == null")
    @Override
    public Account revise(AccountRegisterDTO accountRegisterDTO) {
        // 判断是否未改名称
        if (accountRegisterDTO.getUsername() != null) {
            // 名称是否重复
            if (accountMapper.isAccountUsername(accountRegisterDTO.getUsername()) > 0) {
                throw new BusinessException(AccountErrorCode.E_130111);
            }
        }
        Account account = null;
        try {
            // 密码加密
            if (accountRegisterDTO.getPassword() != null) {
                accountRegisterDTO.setPassword(MD5DES.encrypt(accountRegisterDTO.getPassword()));
            }
            // 修改数据
            accountMapper.revise(accountRegisterDTO);
            // 更新缓存
            account = accountMapper.getById(accountRegisterDTO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(AccountErrorCode.E_130103);
        }

        return account;
    }

    @Override
    public List<Account> getAccountList(int page, int rows, String username) {
        page = (page - 1) * rows;
        List<Account> accountList = accountMapper.getAccountList(page, rows, username);
        return accountList;
    }

    @Override
    public Integer getAccountCounts(String username) {
        int accountCounts = accountMapper.getAccountCounts(username);
        return accountCounts;
    }

    @CachePut(cacheNames = { "account" }, key = "#accountId", unless = "#result == null")
    @Override
    public Account reviseAccountState(Long accountId, Integer state) {
        accountMapper.reviseAccountState(accountId, state);
        // 修改缓存
        Account account = accountMapper.getById(accountId);
        return account;
    }
}
