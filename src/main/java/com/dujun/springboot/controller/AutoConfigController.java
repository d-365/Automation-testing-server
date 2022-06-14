package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.service.impl.AutoConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 测试平台配置
 * @author dujun
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/config")
public class AutoConfigController {

    @Autowired
    private AutoConfigServiceImpl configService;


    /**
     * 定位方式列表
     */
    @PostMapping("/locationWay")
    public Result<?> locationWay(){
        return configService.locationWay();
    }

    /**
     * 元素类型
     */
    @PostMapping("/elementType")
    public Result<?> elementType(){
        return configService.elementType();
    }



}

