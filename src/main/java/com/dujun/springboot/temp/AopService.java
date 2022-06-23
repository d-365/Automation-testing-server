/*
 * author     : dujun
 * date       : 2022/6/1 14:23
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
public class AopService {

    @MyAnnotationAop
    public String share(String url){
        System.out.println(url);
        return url;
    }


}

@SpringBootTest
class myTest{
    @Autowired
    AopService aopService;

    @Test
    public void fun1(){
        aopService.share("dujun");
    }
}