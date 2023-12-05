package com.just.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passage {
    @MongoId
    private String id;
    private String title;
    private String content;
    private String author;
    private String authorId;
    private Long time;
    private String tag;
}
