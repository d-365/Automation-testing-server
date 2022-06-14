/*
 * author     : dujun
 * date       : 2022/5/16 11:34
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import com.dujun.springboot.VO.DebugApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Demo {

    @MyAnnotation()
    public static String AutoName;

    public static void main(String[] args) {
        Class<Demo>  c = Demo.class;
        for (Field field : c.getDeclaredFields()) {
            if (field.isAnnotationPresent(MyAnnotation.class)){
                MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
                System.out.println(annotation.value());
            }
        }
    }

}

@Retention(RetentionPolicy.RUNTIME)  // 保留到运行时，可通过注解获取
@Target(ElementType.FIELD)
@interface MyAnnotation{

    String value() default "999";

}