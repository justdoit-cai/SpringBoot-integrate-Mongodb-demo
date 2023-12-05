package com.just.dao;


import com.just.pojo.Passage;
import com.just.pojo.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassageDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Passage> selectAll() {
        return mongoTemplate.findAll(Passage.class);
    }

    public List<Passage> selectByAuthorId(String authorId) {
        Query query = new Query(Criteria.where("authorId").is(authorId));
        List<Passage> passageList = mongoTemplate.find(query, Passage.class);
        return passageList;
    }
    public Passage selectById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        Passage passage = mongoTemplate.findOne(query, Passage.class);
        return passage;
    }

    public boolean insert(Passage passage) {
        mongoTemplate.insert(passage);
        return true;
    }

    public boolean deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query);
        return result.getDeletedCount() != 0;
    }

    public boolean update(Passage passage) {
        Query query = new Query(Criteria.where("id").is(passage.getId()));
        Update update = new Update().set("content", passage.getContent()).set("title", passage.getTitle()).set("tag", passage.getTag());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Passage.class);
        System.out.println(result);
        return result.getMatchedCount() != 0;
    }
}
