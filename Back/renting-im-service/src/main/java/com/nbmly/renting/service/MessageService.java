package com.nbmly.renting.service;

import com.nbmly.renting.websocket.pojo.Message;

import java.util.List;

public interface MessageService {
    List<Message> findListByFromAndTo(Long fromId, Long toId, Integer page, Integer rows);
}
