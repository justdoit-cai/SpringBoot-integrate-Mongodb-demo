package com.just.controller;

import com.just.pojo.Result;
import com.just.pojo.User;
import com.just.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Result selectAll() {
        System.out.println("查询所有用户");
        List<User> userList = userService.selectAll();
        return Result.success(userList);
    }

    @PutMapping("/password")
    public Result updatePassword(@RequestBody User user, HttpSession session) {
        System.out.println("用户修改密码: " + user);
        User userSession = (User) session.getAttribute("user");
        if (userSession == null) {
            return Result.error("请先登录");
        }
        if(!userService.updatePassword(userSession.getId(), user.getPassword())){
            return Result.error("修改失败");
        }
        session.removeAttribute("user");
        return Result.success("修改成功，请重新登录");
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        boolean success = userService.deleteUser(id);
        if (!success) {
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
}
