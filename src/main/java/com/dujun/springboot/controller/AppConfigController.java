package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.AppConfig;
import com.dujun.springboot.service.AppConfigService;
import com.dujun.springboot.service.impl.AppConfigServiceImpl;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * APP 启动配置表 前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/appConfig")
public class AppConfigController {

    @Autowired
    @Qualifier("AppConfigServiceImpl")
    private AppConfigService appConfigService;

    @GetMapping("/list")
    public Result<?> appConfigList(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "1")Integer current,@RequestParam(required = false,value = "name")String name){
        return appConfigService.appConfigList(size,current,name);
    }

    @DeleteMapping("del/{id}")
    public Result<?> delAppConfig(@PathVariable("id") Integer id){
        return appConfigService.delAppConfig(id);
    }

    @PostMapping("/update")
    public Result<?> updateAppConf(@RequestBody AppConfig appConfig){
        return appConfigService.updateAppConf(appConfig);
    }

}

