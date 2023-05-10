/*
 * author     : dujun
 * date       : 2022/3/1 15:48
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.timing.ChartsTiming;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/upload")
    public String test(MultipartFile file) {
        try {
            file.transferTo(new File("C:\\Users\\dujun\\Desktop\\1.jpg"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 轻易花填单性能测试报告
     */
    @GetMapping("/chart")
    public Result<?> getCharts() {
        return Result.success(ChartsTiming.chartsData);
    }

}
