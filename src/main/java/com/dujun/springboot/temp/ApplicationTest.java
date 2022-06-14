/*
 * author     : dujun
 * date       : 2022/5/16 11:34
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.dujun.springboot.temp");
        Person blue = applicationContext.getBean(Person.class);
        System.out.println(blue);
        
    }
}
