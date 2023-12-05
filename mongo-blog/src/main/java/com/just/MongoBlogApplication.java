package com.just;

import com.just.config.DbConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MongoBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoBlogApplication.class, args);
        DbConfig.initDb();
    }
}
