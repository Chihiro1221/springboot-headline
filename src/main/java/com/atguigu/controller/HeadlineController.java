package com.atguigu.controller;

import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestHeader String token, @RequestBody Headline headline) {
        Result r = headlineService.publish(token, headline);
        return r;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineService.getById(hid);
        return Result.ok(headline);
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline) {
        Result r = headlineService.updateHeadline(headline);
        return r;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid) {
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
