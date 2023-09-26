package com.atguigu.service;

import com.atguigu.pojo.User;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author haonan
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-09-25 21:41:45
*/
public interface UserService extends IService<User> {

    /**
     * 登录服务
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 检查用户名是否可用
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 用户注册
     * @param user
     * @return
     */
    Result regist(User user);

    /**
     * 检查token是否过期
     * @param token
     * @return
     */
    Result checkLogin(String token);
}
