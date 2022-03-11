/*
 * author     : dujun
 * date       : 2022/3/1 15:48
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
