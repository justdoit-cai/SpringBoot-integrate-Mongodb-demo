package com.just.config;

import com.just.pojo.Passage;
import com.just.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {
    /**
     * Spring注入静态变量的方式：给set方法加上@Autowired
     */
    private static MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        DbConfig.mongoTemplate = mongoTemplate;
    }

    public static void initDb() {
        System.out.println("[=== start initDb ===]");
        if (!mongoTemplate.collectionExists("user")) {
            mongoTemplate.insert(new User(null, "admin1", "123"));
            mongoTemplate.insert(new User(null, "admin2", "123"));
            mongoTemplate.insert(new User(null, "admin3", "123"));
            mongoTemplate.insert(new User(null, "admin4", "123"));
            mongoTemplate.insert(new User(null, "admin5", "123"));
            mongoTemplate.insert(new User(null, "admin6", "123"));
            mongoTemplate.insert(new User(null, "admin7", "123"));
            mongoTemplate.insert(new User(null, "admin8", "123"));
            mongoTemplate.insert(new User(null, "admin9", "123"));
        }
        if (!mongoTemplate.collectionExists("passage")) {
            initPassage("web安全", "this is content", "admin1", 1701758184L, "web");
            initPassage("python", "this is content", "admin1", 1701758154L, "python");
            initPassage("java", "this is content", "admin1", 1701757184L, "java");
            initPassage("cpp", "this is content", "admin1", 1701758084L, "cpp");
            initPassage("pwn", "this is content", "admin3", 1701758184L, "pwn");
            initPassage("逆向入门", "this is content", "admin3", 1701758184L, "逆向");
            initPassage("反序列化", "this is content", "admin2", 1701758184L, "反序列化");
            initPassage("安卓开发", "this is content", "admin2", 1701758184L, "安卓");
            initPassage("php", "this is content", "admin5", 1701758184L, "php");
            initPassage("thinkphp", "this is content", "admin6", 1701750184L, "php");
            initPassage("yii", "this is content", "admin5", 1701752184L, "php");
            initPassage("web安全1", "this is web1 安全 content", "admin6", 1701758984L, "web");
            initPassage("web安全2", "this is web2 安全 content", "admin9", 1701758684L, "web");
            initPassage("web安全3", "this is we3 安全 content", "admin8", 1701758194L, "web");
        }
        System.out.println("[=== finish initDb ===]");
    }
    public static void initPassage(String title, String content, String username, Long time, String tag) {
        Query query = new Query(Criteria.where("username").is(username));
        User user = mongoTemplate.findOne(query, User.class);
        assert user != null;
        mongoTemplate.insert(new Passage(null, title, content, username, user.getId(), time,tag));
    }
}
