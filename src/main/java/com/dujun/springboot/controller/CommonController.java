/**
 * author     : dujun
 * date       : 2021/12/13 17:51
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.sonEntity.EnvDataBase;
import com.dujun.springboot.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
公共controller
 */

@RestController
@RequestMapping("/common")
public class CommonController {

    CommonServiceImpl commonService = new CommonServiceImpl();

    @PostMapping("/ebDebug")
    // 数据库连接测试
    public Result dbDebug(@RequestBody  EnvDataBase envDataBase){
        return commonService.dbDebug(envDataBase);
    }

}
