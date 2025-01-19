package com.nbmly.renting.service.impl;

import com.nbmly.renting.dao.MessageDAO;
import com.nbmly.renting.service.MessageService;
import com.nbmly.renting.websocket.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDAO messageDAO;

    @Override
    public List<Message> findListByFromAndTo(Long fromId, Long toId, Integer page, Integer rows) {
        return messageDAO.findListByFromAndTo(fromId, toId, page, rows);
    }
}
