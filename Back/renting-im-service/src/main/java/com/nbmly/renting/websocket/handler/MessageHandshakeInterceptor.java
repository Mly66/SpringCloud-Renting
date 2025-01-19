package com.nbmly.renting.websocket.handler;

import com.alibaba.fastjson.JSON;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class MessageHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        String path = request.getURI().getPath();
        String[] ss = StringUtils.split(path, '/');
        if (ss.length != 3) {
            return false;
        }
        String user_name = TokenUtil.chekaToken(ss[2]);
        AccountDTO accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        if (accountDTO == null) {
            return false;
        }
        attributes.put("userId", accountDTO.getId());
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception exception) {

    }
}
