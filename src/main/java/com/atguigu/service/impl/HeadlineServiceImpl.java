package com.atguigu.service.impl;

import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.vo.PortalVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.mapper.HeadlineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haonan
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2023-09-25 21:41:45
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {

    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        Page<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(page, portalVo);
        Map<String, Map> data = new HashMap<>();
        HashMap<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", page.getRecords());
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());
        data.put("pageInfo", pageInfo);

        return Result.ok(data);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map map = headlineMapper.selectDetailMap(hid);
        HashMap<String, Object> headline = new HashMap<>();
        headline.put("headline", map);

        Headline headline1 = new Headline();
        headline1.setHid((Integer) map.get("hid"));
        headline1.setVersion((Integer) map.get("version"));
        headline1.setPageViews((Integer) map.get("pageViews") + 1);
        headlineMapper.updateById(headline1);

        return Result.ok(headline);
    }

    @Override
    public Result publish(String token, Headline headline) {
        Long userId = jwtHelper.getUserId(token);
        headline.setPublisher(Math.toIntExact(userId));
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    @Override
    public Result updateHeadline(Headline headline) {
        Headline data = headlineMapper.selectById(headline.getHid());
        headline.setVersion(data.getVersion());
        headlineMapper.updateById(headline);

        return Result.ok(null);
    }
}




