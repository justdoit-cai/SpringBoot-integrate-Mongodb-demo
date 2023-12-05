package com.just;

import com.just.dao.PassageDao;
import com.just.dao.UserDao;
import com.just.pojo.Passage;
import com.just.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.sql.Date;

@SpringBootTest
class MongoBlogApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PassageDao passageDao;
    @Test
    void contextLoads() {

        Passage passage = new Passage();
        passage.setId("656ed6aeec258d63d8590fb7");
        passage.setTitle("test1111");
        passage.setContent("test1111 content");
        passage.setTag("1111");
        passageDao.update(passage);

        Query query = new Query(Criteria.where("id").is(passage.getId()));
        Passage passage2 = mongoTemplate.findOne(query, Passage.class);
        System.out.println(passage2);
    }

}
