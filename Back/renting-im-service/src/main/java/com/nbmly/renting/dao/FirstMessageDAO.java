package com.nbmly.renting.dao;

import com.mongodb.client.result.UpdateResult;
import com.nbmly.renting.websocket.pojo.FirstMessage;
import org.bson.types.ObjectId;

import java.util.List;

public interface FirstMessageDAO {
    void upsertFirstMsg(FirstMessage firstMessage);

    UpdateResult updateFirstMsgState(ObjectId id, Integer status);

    List<FirstMessage> findListUserMessage(Long fromOrTo);
}
