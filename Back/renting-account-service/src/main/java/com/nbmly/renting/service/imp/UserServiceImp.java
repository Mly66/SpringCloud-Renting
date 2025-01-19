package com.nbmly.renting.service.imp;

import com.nbmly.renting.BusinessException;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.common.UserErrorCode;
import com.nbmly.renting.mapper.AccountMapper;
import com.nbmly.renting.mapper.UserMapper;
import com.nbmly.renting.pojo.Account;
import com.nbmly.renting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public RestResponse<List<String>> lookupPowers(Long accountName) {
        String power = userMapper.lookupPowers(accountName).trim();
        if (power == null) {
            return RestResponse.error();
        }
        List<String> powerList = new ArrayList<>();
        String[] powers = power.split(",");
        Arrays.stream(powers).forEach(s -> {
            powerList.add(s);
        });
        return RestResponse.success(powerList);
    }

    @Override
    public void powerRegister(Long accountName) {
        System.out.println(accountName + "----------------");
        // 新添用户权限
        if (userMapper.powerRegister(accountName) <= 0) {
            throw new BusinessException(UserErrorCode.E_130101);
        }
    }

    @CachePut(cacheNames = { "account" }, key = "#accountId", unless = "#result == null")
    @Override
    public Account revisePower(Long accountId) {
        userMapper.revisePower(accountId);
        // 修改缓存
        Account account = accountMapper.getById(accountId);
        return account;
    }

    @CachePut(cacheNames = { "account" }, key = "#accountId", unless = "#result == null")
    @Override
    public Account reviseUserPower(Long accountId, Long powerId) {
        userMapper.reviseUserPower(accountId, powerId);
        // 修改缓存
        Account account = accountMapper.getById(accountId);
        return account;
    }
}
