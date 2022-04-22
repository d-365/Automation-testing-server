/*
 * author     : dujun
 * date       : 2022/3/1 15:48
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller;

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
    public String test(MultipartFile file){
        try {
            file.transferTo(new File("C:\\Users\\dujun\\Desktop\\1.jpg"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
