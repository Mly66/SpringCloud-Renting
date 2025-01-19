package com.nbmly.renting.service;

import com.nbmly.renting.websocket.pojo.FirstMessage;

import java.util.List;

public interface FirstMessageService {
    List<FirstMessage> findListUserMsg(Long fromOrTo);
}
