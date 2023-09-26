package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.vo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author haonan
 * @description 针对表【news_headline】的数据库操作Mapper
 * @createDate 2023-09-25 21:41:45
 * @Entity com.atguigu.pojo.Headline
 */
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map<String, Object>> selectMyPage(IPage iPage, @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}




