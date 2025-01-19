package com.nbmly.renting.controller;

import com.alibaba.fastjson.JSON;
import com.nbmly.renting.account.AccountModAPI;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.service.AccountModService;
import com.nbmly.renting.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Api(value = "账户功能服务的API", tags = { "账户功能服务接口" })
public class AccountModController implements AccountModAPI {

    @Autowired
    private AccountModService accountModService;

    @ApiOperation("保存收藏")
    @PostMapping("/u/saveCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "房屋信息", required = true, dataType = "String") })
    @Override
    public RestResponse<String> saveCollection(
            @RequestHeader("Authorization") String jwtToken, @RequestParam("info") String info) {
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        accountModService.saveAccountCollection(accountDTO.getId().toString(), info);
        return RestResponse.success("成功");
    }

    @ApiOperation("删除收藏")
    @PostMapping("/u/delCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "房屋信息", required = true, dataType = "String") })
    @Override
    public RestResponse<String> delCollection(
            @RequestHeader("Authorization") String jwtToken, @RequestParam("info") String info) {
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        accountModService.delAccountCollection(accountDTO.getId().toString(), info);
        return RestResponse.success("成功");
    }

    @ApiOperation("获取收藏")
    @GetMapping("/u/getCollection")
    @Override
    public RestResponse<Set<String>> getCollection(@RequestHeader("Authorization") String jwtToken) {
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        return RestResponse.success(accountModService.getAccountCollection(accountDTO.getId().toString()));
    }

    @ApiOperation("是否收藏")
    @PostMapping("/u/isCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "房屋信息", required = true, dataType = "String") })
    @Override
    public RestResponse<Boolean> isCollection(@RequestHeader("Authorization") String jwtToken,
            @RequestParam("info") String info) {
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        boolean isBg = accountModService.isAccountCollectionValue(accountDTO.getId().toString(), info);
        return RestResponse.success(isBg);
    }
}
