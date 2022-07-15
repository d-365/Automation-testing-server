/*
 * author     : dujun
 * date       : 2022/6/29 10:02
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.common.appium.AppiumApi;
import io.appium.java_client.AppiumDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/temp")
public class Temp {

    @PostMapping("/zhuang")
    public String temp(){
        JSONObject param = new JSONObject();
        param.put("code", 1);
        param.put("msg", "success");
        List<String> list = new ArrayList<>();
        list.add("13888888880");
        list.add("13888888881");
        list.add("13888888882");
        list.add("13888888883");
        list.add("13888888884");
        list.add("13888888885");
        list.add("13888888886");
        list.add("13888888887");
        list.add("13888888888");
        list.add("13888888889");
        list.add("18397858213");
        param.put("data", list);

        return param.toJSONString();
    }

    @PostMapping("/into")
    public String into(){
        JSONObject param = new JSONObject();
        param.put("code", 1);
        param.put("msg", "success");
        return param.toJSONString();
    }

    @GetMapping("/1")
    public String data(){
        AppiumDriver driver = null;
        driver.quit();
        return "";
    }


}
