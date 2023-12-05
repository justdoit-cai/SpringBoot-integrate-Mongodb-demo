package com.just.controller;

import com.just.pojo.Result;
import com.just.pojo.User;
import com.just.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session){
        System.out.println("登录: " + user);
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("账号和密码均不能为空");
        }
        if(!userService.login(user.getUsername(), user.getPassword())){
            return Result.error("账号或密码错误");
        }
        User userSession = userService.selectByUsername(user.getUsername());
        userSession.setPassword(null);
        session.setAttribute("user", userSession);
        return Result.success("登录成功");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        System.out.println("注册: " + user);
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("账号和密码均不能为空");
        }
        if (!userService.checkUsername(user.getUsername())) {
            return Result.error("账号已存在");
        }
        user.setId(null);
        boolean success = userService.insert(user);
        if (!success) {
            return Result.error("出现未知异常，注册失败");
        }
        return Result.success("注册成功");
    }
    @PostMapping("/logout")
    public Result logout(HttpSession session){
        System.out.println("退出: " + session.getAttribute("user"));
        session.removeAttribute("user");
        return Result.success("退出成功");
    }
}
