package com.just.dao;

import com.just.pojo.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.ListUI;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean insert(User user) {
        mongoTemplate.insert(user);
        return true;
    }

    public List<User> selectAll() {
        return mongoTemplate.findAll(User.class);
    }

    public User getUserById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    public List<User> getUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        List<User> userList = mongoTemplate.find(query, User.class);
        return userList;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    public boolean deleteByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        DeleteResult result = mongoTemplate.remove(query);
        return result.getDeletedCount() != 0;
    }

    public boolean deleteById(String  id) {
        Query query = new Query(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query);
        return result.getDeletedCount() != 0;
    }

    public boolean updatePassword(String id, String password) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("password", password);
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        return result.getMatchedCount() != 0;
    }
}
