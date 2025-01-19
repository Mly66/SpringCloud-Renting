package com.nbmly.renting.controller;

import com.alibaba.fastjson.JSON;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.service.UserService;
import com.nbmly.renting.user.UserAPI;
import com.nbmly.renting.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "统一权限服务的API", tags = { "权限服务接口" })
public class UserController implements UserAPI {

    @Autowired
    private UserService userService;

    @ApiOperation("修改当前用户权限")
    @GetMapping("/u/revisePower")
    @Override
    public RestResponse<String> revisePower(@RequestHeader("Authorization") String jwtToken) {
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);

        userService.revisePower(accountDTO.getId());
        return RestResponse.success("申请成功");
    }

    @ApiOperation("管理员修改用户权限")
    @GetMapping("/m/reviseByIdPower/{accountId}/{powerId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "powerId", value = "权限id", required = true, dataType = "Long") })
    @Override
    public RestResponse<String> reviseByIdPower(@PathVariable("accountId") Long accountId,
            @PathVariable("powerId") Long powerId) {
        userService.reviseUserPower(accountId, powerId);
        return RestResponse.success("修改权限成功");
    }
}
