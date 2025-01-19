package com.nbmly.renting.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.nbmly.renting.dao.FirstMessageDAO;
import com.nbmly.renting.websocket.pojo.FirstMessage;
import com.nbmly.renting.websocket.pojo.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class FirstMessageDAOImpl implements FirstMessageDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void upsertFirstMsg(FirstMessage firstMessage) {

        // 用户A发送给用户B的条件
        Criteria criteriaFrom = new Criteria().andOperator(
                Criteria.where("from").is(firstMessage.getFrom()),
                Criteria.where("to").is(firstMessage.getTo()));

        // 用户B发送给用户A的条件
        Criteria criteriaTo = new Criteria().andOperator(
                Criteria.where("to").is(firstMessage.getFrom()),
                Criteria.where("from").is(firstMessage.getTo()));

        Criteria criteria = new Criteria().orOperator(criteriaFrom, criteriaTo);

        // 设置查询条件
        Query query = Query.query(criteria);
        // 修改数据
        Update update = new Update();
        update.set("msg_id", firstMessage.getMsgId());
        update.set("msg", firstMessage.getMsg());
        update.set("status", firstMessage.getStatus());
        update.set("send_date", firstMessage.getSendDate());
        update.set("from", firstMessage.getFrom());
        update.set("to", firstMessage.getTo());

        mongoTemplate.upsert(query, update, FirstMessage.class);
    }

    @Override
    public UpdateResult updateFirstMsgState(ObjectId id, Integer status) {
        Query query = Query.query(Criteria.where("msg_id").is(id));
        Update update = Update.update("status", status);
        if (status.intValue() == 2) {
            update.set("read_date", new Date());
        }
        return this.mongoTemplate.updateFirst(query, update, FirstMessage.class);
    }

    @Override
    public List<FirstMessage> findListUserMessage(Long fromOrTo) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("from").is(fromOrTo),
                Criteria.where("to").is(fromOrTo));
        // 设置查询条件，分页
        Query query = Query.query(criteria);
        query.with(Sort.by(Sort.Order.desc("send_date")));

        return this.mongoTemplate.find(query, FirstMessage.class);
    }
}
