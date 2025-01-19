package com.nbmly.renting.service.impl;

import com.nbmly.renting.dao.FirstMessageDAO;
import com.nbmly.renting.service.FirstMessageService;
import com.nbmly.renting.websocket.pojo.FirstMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstMessageServiceImpl implements FirstMessageService {

    @Autowired
    private FirstMessageDAO firstMessageDAO;

    @Override
    public List<FirstMessage> findListUserMsg(Long fromOrTo) {
        return firstMessageDAO.findListUserMessage(fromOrTo);
    }
}
