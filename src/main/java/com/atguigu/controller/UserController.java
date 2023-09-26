package com.atguigu.controller;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result r = userService.login(user);
        return r;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        Result r = userService.getUserInfo(token);
        return r;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(String username) {
        Result r = userService.checkUserName(username);
        return r;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        Result r = userService.regist(user);
        return r;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        Result r = userService.checkLogin(token);
        return r;
    }
}
