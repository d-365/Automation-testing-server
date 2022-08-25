/*
 * author     : dujun
 * date       : 2022/6/29 10:02
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.mapper.UserMapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/temp")
public class Temp {

    Integer mark = 1;
    @Resource
    private UserMapper userMapper;

    @PostMapping("/zhuang")
    public String temp() {
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
    public String into() {
        JSONObject param = new JSONObject();
        param.put("code", 1);
        param.put("msg", "success");
        return param.toJSONString();
    }

    @PostMapping("/test1")
    public Result<?> test1(@Validated @RequestBody(required = false) User user, BindingResult bindingResult) {
        bindingResult.getAllErrors().forEach(System.out::println);
        return Result.success("success");
    }

    @GetMapping("/start")
    public String data() {
        mark = 1;
        Thread thread = new Thread(() -> {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    if (mark == 1) {
                        System.out.println(i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        });
        thread.start();
        return "执行";
    }

    @GetMapping("/end")
    public String end() {
        this.mark = 2;
        return "结束";
    }

}
