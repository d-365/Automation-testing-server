/**
 * author     : dujun
 * date       : 2022/2/10 11:53
 * description: 数据统计
 */

package com.dujun.springboot.controller;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.statics.HomeStatics;
import com.dujun.springboot.service.impl.StaticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StaticServiceImpl staticService;

    /*
      首页数据统计
     */
    @GetMapping("/home")
    public Result<HomeStatics> homeStatics(){
        return staticService.home();
    }

    @GetMapping("/line")
    public Result<?> homeLine(){
        return staticService.line();
    }

}
