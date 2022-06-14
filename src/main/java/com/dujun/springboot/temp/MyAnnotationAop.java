/*
 * author     : dujun
 * date       : 2022/6/1 15:38
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotationAop {

}
