package com.nbmly.renting.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.nbmly.renting.dao.MessageDAO;
import com.nbmly.renting.websocket.pojo.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 实现：查询点对点消息记录
     * 
     * @param fromId
     * @param toId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<Message> findListByFromAndTo(Long fromId, Long toId, Integer page, Integer rows) {

        // 用户A发送给用户B的条件
        Criteria criteriaFrom = new Criteria().andOperator(
                Criteria.where("from").is(fromId),
                Criteria.where("to").is(toId));

        // 用户B发送给用户A的条件
        Criteria criteriaTo = new Criteria().andOperator(
                Criteria.where("from").is(toId),
                Criteria.where("to").is(fromId));

        Criteria criteria = new Criteria().orOperator(criteriaFrom, criteriaTo);

        // PageRequest pageRequest = PageRequest.of(page - 1, rows,
        // Sort.by(Sort.Direction.ASC, "send_date"));

        // 设置查询条件，分页
        Query query = Query.query(criteria);
        query.with(Sort.by(Sort.Order.desc("send_date")));
        query.skip((page - 1) * rows);
        query.limit(rows);

        return this.mongoTemplate.find(query, Message.class);
    }

    @Override
    public Message findMessageById(String id) {
        return this.mongoTemplate.findById(new ObjectId(id), Message.class);
    }

    @Override
    public UpdateResult updateMessageState(ObjectId id, Integer status) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("status", status);
        if (status.intValue() == 2) {
            update.set("read_date", new Date());
        }
        return this.mongoTemplate.updateFirst(query, update, Message.class);
    }

    @Override
    public void saveMessage(Message message) {
        this.mongoTemplate.save(message);
    }

    @Override
    public DeleteResult deleteMessage(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query, Message.class);
    }
}
