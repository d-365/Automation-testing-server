/*
 * author     : dujun
 * date       : 2022/6/1 14:23
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.springframework.stereotype.Service;

@Service
public class AopService {

    @MyAnnotationAop
    public String share(String url){
        System.out.println(url);
        return url;
    }

}
