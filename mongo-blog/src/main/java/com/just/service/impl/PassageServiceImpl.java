package com.just.service.impl;

import com.just.dao.PassageDao;
import com.just.pojo.Passage;
import com.just.service.PassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassageServiceImpl implements PassageService {
    @Autowired
    private PassageDao passageDao;
    @Override
    public List<Passage> selectAll() {
        return passageDao.selectAll();
    }

    @Override
    public List<Passage> selectByAuthorId(String authorId) {
        return passageDao.selectByAuthorId(authorId);
    }

    @Override
    public Passage selectById(String id) {
        return passageDao.selectById(id);
    }

    @Override
    public boolean deleteById(String id) {
        return passageDao.deleteById(id);
    }

    @Override
    public boolean update(Passage passage) {
        return passageDao.update(passage);
    }

    @Override
    public boolean insert(Passage passage) {
        return passageDao.insert(passage);
    }
}
