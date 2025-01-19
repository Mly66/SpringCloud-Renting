package com.nbmly.renting.controller;

import com.alibaba.fastjson.JSON;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.im.ImAPI;
import com.nbmly.renting.service.FirstMessageService;
import com.nbmly.renting.service.MessageService;
import com.nbmly.renting.common.RestResponse;
import com.nbmly.renting.util.TokenUtil;
import com.nbmly.renting.websocket.pojo.FirstMessage;
import com.nbmly.renting.websocket.pojo.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "聊天服务的API", tags = { "聊天服务接口" })
public class ImController implements ImAPI {
    @Autowired
    private FirstMessageService findListUserMessage;
    @Autowired
    private MessageService messageService;

    @GetMapping("/u/getFirstMsg")
    @ApiOperation("查询当前用户给谁发过消息")
    @Override
    public RestResponse<Map<String, Object>> getFirstMsg(
            @RequestHeader("Authorization") String jwtToken) {
        // 获取当前用户ID
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        if (accountDTO == null) {
            return null;
        }
        Map<String, Object> rspData = new HashMap<>();
        List<FirstMessage> listUserMessage = findListUserMessage.findListUserMsg(accountDTO.getId());
        rspData.put("accountId", accountDTO.getId());
        rspData.put("msg", listUserMessage);
        return RestResponse.success(rspData);
    }

    @GetMapping("/u/getFromAndToMsg/{toId}/{page}/{rows}")
    @ApiOperation("查询当前一对一聊天记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "toId", value = "对方id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "page", value = "浏览页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "rows", value = "每页条数", required = true, dataType = "Integer") })
    @Override
    public RestResponse<Map<String, Object>> getFromAndToMsg(
            @RequestHeader("Authorization") String jwtToken, @PathVariable("toId") Long toId,
            @PathVariable("page") Integer page, @PathVariable("rows") Integer rows) {
        // 获取当前用户ID
        String user_name = TokenUtil.chekaToken(TokenUtil.workToken(jwtToken));
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        if (accountDTO == null) {
            return null;
        }
        Map<String, Object> rspData = new HashMap<>();
        List<Message> listByFromAndTo = messageService.findListByFromAndTo(accountDTO.getId(), toId, page, rows);
        rspData.put("accountId", accountDTO.getId());
        rspData.put("msg", listByFromAndTo);
        return RestResponse.success(rspData);
    }
}
