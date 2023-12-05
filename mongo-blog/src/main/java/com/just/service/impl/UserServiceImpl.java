package com.just.service.impl;

import com.just.dao.UserDao;
import com.just.pojo.User;
import com.just.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public boolean insert(User user) {
        return userDao.insert(user);
    }

    public boolean checkUsername(String username) {
        List<User> userList = userDao.getUserByUsername(username);
        System.out.println(userList);
        return userList == null || userList.size() == 0;
    }

    public boolean login(String username, String password) {
        User user = userDao.getUserByUsernameAndPassword(username, password);
        return user != null;
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }
    public boolean updatePassword(String id, String password) {
        return userDao.updatePassword(id, password);
    }

    @Override
    public User selectByUsername(String username) {
        List<User> userList = userDao.getUserByUsername(username);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public boolean deleteUser(String id) {
        return userDao.deleteById(id);
    }

}
