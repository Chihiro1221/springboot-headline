package com.atguigu.controller;

import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
import com.atguigu.vo.PortalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes() {
        Result r = typeService.findAllTypes();
        return r;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        Result r = headlineService.findNewsPage(portalVo);
        return r;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid) {
        Result r = headlineService.showHeadlineDetail(hid);
        return r;
    }
}
