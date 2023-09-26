package com.atguigu.service;

import com.atguigu.pojo.Type;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author haonan
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-09-25 21:41:45
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
