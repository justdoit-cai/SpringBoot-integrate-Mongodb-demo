package com.just.service;

import com.just.pojo.User;

import java.util.List;

public interface UserService {
    public boolean insert(User user);
    public List<User> selectAll();
    public boolean checkUsername(String username);
    public boolean login(String username, String password);
    public boolean updatePassword(String id, String password);
    public User selectByUsername(String username);
    public boolean deleteUser(String id);
}

