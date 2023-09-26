package com.atguigu.service.impl;

import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.mapper.UserMapper;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author haonan
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2023-09-25 21:41:45
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(userLambdaQueryWrapper);

        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR.getCode(), "用户不存在");
        }
        if (user.getUserPwd() == null || !loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR.getCode(), "密码错误");
        }

        String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);

        return Result.ok(data);
    }

    @Override
    public Result getUserInfo(String token) {
        if (token == null || jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN.getCode(), "token失效");
        }

        Long userId = jwtHelper.getUserId(token);
        User user = userMapper.selectById(userId);
        user.setUserPwd(null);
        return Result.ok(user);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);
        Long count = userMapper.selectCount(userLambdaQueryWrapper);
        if (count != 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR.getCode(), "用户名已存在");
        }

        return Result.ok(null);
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        Long i = userMapper.selectCount(userLambdaQueryWrapper);
        if (i > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED.getCode(), "用户名占用");
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }

    @Override
    public Result checkLogin(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }

        return Result.ok(null);
    }
}




