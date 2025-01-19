package com.nbmly.renting.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbmly.renting.account.model.AccountDTO;
import com.nbmly.renting.dao.FirstMessageDAO;
import com.nbmly.renting.dao.MessageDAO;
import com.nbmly.renting.util.TokenUtil;
import com.nbmly.renting.websocket.pojo.FirstMessage;
import com.nbmly.renting.websocket.pojo.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessageHandler extends TextWebSocketHandler {

    @Autowired
    private MessageDAO messageDAO;
    @Autowired
    private FirstMessageDAO firstMessageDAO;

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static final Map<Long, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");
        SESSIONS.remove(userId);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");
        // 将当前用户的session放置到map中，后面会使用相应的session通信
        WebSocketSession toSession = SESSIONS.get(userId);
        SESSIONS.put(userId, session);

    }

    void sendHeartbeat(Long toId) throws Exception {
        Message message = Message.builder()
                .id(null)
                .sendDate(new Date())
                .from(-1L)
                .to(-1L)
                .msg("heartbeat")
                .status(1)
                .build();
        String msgJson = MAPPER.writeValueAsString(message);

        this.sendOne(toId, msgJson);
    }

    // 一对一单聊
    void sendOne(Long toId, String msgJson) throws Exception {
        // 判断to用户是否在线
        WebSocketSession toSession = SESSIONS.get(toId);
        if (toSession != null && toSession.isOpen()) {
            // TODO 具体格式需要和前端对接
            toSession.sendMessage(new TextMessage(msgJson));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");

        JsonNode jsonNode = MAPPER.readTree(textMessage.getPayload());

        String msgId = jsonNode.get("msgId").asText();
        Long toId = jsonNode.get("toId").asLong();
        String msg = jsonNode.get("msg").asText();
        // 心跳机制
        if ("heartbeat".equals(msgId.trim())) {
            sendHeartbeat(toId);
            return;
        }
        // 正在聊天，修改为已读
        if (!("-1".equals(msgId.trim()))) {
            ObjectId objectId = new ObjectId(msgId);
            messageDAO.updateMessageState(objectId, 2);
            firstMessageDAO.updateFirstMsgState(objectId, 2);
            return;
        }
        // 构造消息
        Message message = Message.builder()
                .id(ObjectId.get())
                .sendDate(new Date())
                .from(userId)
                .to(toId)
                .msg(msg)
                .status(1)
                .build();

        FirstMessage firstMessage = new FirstMessage();
        BeanUtils.copyProperties(message, firstMessage);
        firstMessage.setMsgId(message.getId());

        String msgJson = MAPPER.writeValueAsString(message);
        // 保存消息
        messageDAO.saveMessage(message);
        // 保存最新消息
        firstMessageDAO.upsertFirstMsg(firstMessage);

        // 一对一单聊处理
        sendOne(toId, msgJson);
    }
}
