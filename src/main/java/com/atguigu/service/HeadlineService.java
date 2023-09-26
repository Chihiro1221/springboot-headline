package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.utils.Result;
import com.atguigu.vo.PortalVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author haonan
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-09-25 21:41:45
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 查询头条信息
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 查询头条详情
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     * 发布头条
     * @param token
     * @param headline
     * @return
     */
    Result publish(String token, Headline headline);

    /**
     * 修改头条信息
     * @param headline
     * @return
     */
    Result updateHeadline(Headline headline);
}
