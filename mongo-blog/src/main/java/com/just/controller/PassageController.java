package com.just.controller;

import com.just.pojo.Passage;
import com.just.pojo.Result;
import com.just.pojo.User;
import com.just.service.PassageService;
import com.just.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passage")
public class PassageController {
    @Autowired
    private PassageService passageService;
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public Result selectAll() {
        System.out.println("查询所有文章");
        List<Passage> passageList = passageService.selectAll();
        return Result.success(passageList);
    }
    @GetMapping("/{authorId}")
    public Result selectByAuthorId(@PathVariable("authorId") String authorId) {
        System.out.println("查询作者文章: " + authorId);
        List<Passage> passageList = passageService.selectByAuthorId(authorId);
        return Result.success(passageList);
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id, HttpSession session) {
        System.out.println("删除文章: " + id);
        User userSession = (User) session.getAttribute("user");
        if (userSession == null) {
            return Result.error("请先登录");
        }
        Passage passage = passageService.selectById(id);
        if (!passage.getAuthorId().equals(userSession.getId())) {
            return Result.error("无此权限");
        }
        passageService.deleteById(id);
        return Result.success("删除成功");
    }
    @PutMapping("/")
    public Result update(@RequestBody Passage passage, HttpSession session) {
        System.out.println("修改文章: " + passage);
        User userSession = (User) session.getAttribute("user");
        if (userSession == null) {
            return Result.error("请先登录");
        }
        Passage passage1 = passageService.selectById(passage.getId());
        if (passage1 == null) {
            return Result.error("无此文章");
        }
        if (!passage1.getAuthorId().equals(userSession.getId())) {
            return Result.error("无此权限");
        }
        boolean success = passageService.update(passage);
        if (!success) {
            Result.error("修改失败");
        }
        return Result.success("修改成功");
    }
    @PostMapping("/")
    public Result insert(@RequestBody Passage passage, HttpSession session) {
        System.out.println("创建文章: " + passage);
        User userSession = (User) session.getAttribute("user");
        if (userSession == null) {
            return Result.error("请先登录");
        }
        passage.setId(null);
        passage.setTime(System.currentTimeMillis() / 1000);
        passage.setAuthor(userSession.getUsername());
        passage.setAuthorId(userSession.getId());
        boolean success = passageService.insert(passage);
        if (!success) {
            Result.error("添加失败");
        }
        return Result.success("添加成功");
    }
}
