package com.nbmly.renting.controller;

import com.alibaba.fastjson.JSON;
import com.nbmly.renting.BusinessException;
import com.nbmly.renting.account.AccountAPI;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.account.model.AccountLoginDTO;
import com.nbmly.renting.account.model.AccountRegisterDTO;
import com.nbmly.renting.common.AccountErrorCode;
import com.nbmly.renting.pojo.Account;
import com.nbmly.renting.service.AccountService;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.service.UserService;
import com.nbmly.renting.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "账户服务的API", tags = { "账户服务接口" })
public class AccountController implements AccountAPI {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @ApiImplicitParam(name = "accountLoginDTO", value = "登录信息", required = true, dataType = "AccountLoginDTO", paramType = "body")
    @PostMapping("/overt/h/login")
    @Override
    public RestResponse<AccountDTO> login(@RequestBody AccountLoginDTO accountLoginDTO) {
        return accountService.login(accountLoginDTO);
    }

    @ApiOperation("用户注册")
    @PostMapping("/overt/h/register")
    @ApiImplicitParam(name = "accountRegisterDTO", value = "注册信息", required = true, dataType = "AccountRegisterDTO", paramType = "body")
    @Override
    public RestResponse<String> register(@RequestBody AccountRegisterDTO accountRegisterDTO) {
        accountService.register(accountRegisterDTO);
        return RestResponse.success("注册成功");
    }

    @ApiOperation("用户修改")
    @PostMapping("/u/revise")
    @ApiImplicitParam(name = "accountRegisterDTO", value = "修改信息", required = true, dataType = "AccountRegisterDTO", paramType = "body")
    @Override
    public RestResponse<String> revise(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody AccountRegisterDTO accountRegisterDTO) {
        if (accountRegisterDTO == null) {
            throw new BusinessException(AccountErrorCode.E_130103);
        }
        // 获取当前用户Id
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        accountRegisterDTO.setId(accountDTO.getId());

        accountService.revise(accountRegisterDTO);
        return RestResponse.success("注册成功");
    }

    @ApiOperation("获取用户列表信息")
    @PostMapping("/m/getAccounts/{page}/{rows}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "username", value = "名称模糊查询", required = true, dataType = "String") })
    @Override
    public RestResponse<List<AccountDTO>> getAccounts(
            @PathVariable("page") int page, @PathVariable("rows") int rows,
            @RequestParam("username") String username) {
        if (page == -1) {
            return null;
        }
        if ("null".equals(username)) {
            username = null;
        }
        List<Account> accounts = accountService.getAccountList(page, rows, username);
        List<AccountDTO> accountDTOS = new ArrayList<>();
        // 转成DTO
        accounts.forEach(account -> {
            AccountDTO accountDTO = new AccountDTO();
            BeanUtils.copyProperties(account, accountDTO);
            accountDTOS.add(accountDTO);
        });
        // 响应结果
        return RestResponse.success(accountDTOS);
    }

    @ApiOperation("获取用户列表数")
    @PostMapping("/m/getAccountCounts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "名称模糊查询", required = true, dataType = "String") })
    @Override
    public RestResponse<Integer> getAccountCounts(@RequestParam("username") String username) {
        if ("null".equals(username)) {
            username = null;
        }
        Integer accountCounts = accountService.getAccountCounts(username);
        return RestResponse.success(accountCounts);
    }

    @ApiOperation("管理员修改用户状态")
    @GetMapping("/m/reviseAccountState/{accountId}/{state}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "state", value = "状态(0:冻结,1:正常)", required = true, dataType = "Integer") })
    @Override
    public RestResponse<String> reviseAccountState(@PathVariable("accountId") Long accountId,
            @PathVariable("state") Integer state) {
        accountService.reviseAccountState(accountId, state);
        return RestResponse.success("修改成功");
    }

    @ApiOperation("获取当前令牌信息")
    @GetMapping("/u/getJwtToken")
    @Override
    public RestResponse<AccountDTO> getJwtToken(@RequestHeader("Authorization") String jwtToken) {
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);

        Account account = accountService.getById(accountDTO.getId());
        BeanUtils.copyProperties(account, accountDTO);
        // 获取权限
        RestResponse<List<String>> listRestResponse = userService.lookupPowers(accountDTO.getId());
        accountDTO.setPowers(listRestResponse.getData());
        // 隐藏隐私信息
        accountDTO.setDomain(null);
        return RestResponse.success(accountDTO);
    }

    @ApiOperation("获取用户信息")
    @PostMapping("/u/getByIdAccount")
    @ApiImplicitParam(name = "ids", value = "id列表", required = true, dataType = "Long", paramType = "body")
    @Override
    public RestResponse<List<AccountDTO>> getByIdAccount(@RequestBody Long[] ids) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Long id : ids) {
            AccountDTO accountDTO = new AccountDTO();
            // 查询并写入缓存
            Account account = accountService.getById(id);
            BeanUtils.copyProperties(account, accountDTO);
            accountDTOS.add(accountDTO);
        }
        return RestResponse.success(accountDTOS);
    }
}
