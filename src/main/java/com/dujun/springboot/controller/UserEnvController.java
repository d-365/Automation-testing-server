package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.UserEnv;
import com.dujun.springboot.service.impl.UserEnvServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-01-07
 */
@RestController
@RequestMapping("/userEnv")
public class UserEnvController {

    @Autowired
    private UserEnvServiceImpl userEnvService;

    @PostMapping("/bind")
    public Result<?> user_env(@RequestBody  UserEnv userEnv){
        return userEnvService.user_env(userEnv);
    }

}

