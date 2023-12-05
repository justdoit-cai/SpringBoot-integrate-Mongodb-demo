package com.just.service;

import com.just.pojo.Passage;

import java.util.List;

public interface PassageService {
    public List<Passage> selectAll();
    public List<Passage> selectByAuthorId(String authorId);
    public Passage selectById(String id);
    public boolean deleteById(String id);
    public boolean update(Passage passage);
    public boolean insert(Passage passage);
}
