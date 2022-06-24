package com.dujun.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.MobilePhone;
import com.dujun.springboot.service.impl.MobilePhoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * APP-执行机 前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/mobilePhone")
public class MobilePhoneController {

    @Autowired
    private MobilePhoneServiceImpl mobilePhoneService;

    @GetMapping("/list")
    public Result<IPage<MobilePhone>> mobilePhoneList(@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "1")Integer current,@RequestParam(value = "status",required = false)Integer status){
        return  mobilePhoneService.mobilePhoneList(current,size,status);
    }

}

