/**
 * author     : dujun
 * date       : 2022/8/18 16:15
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.common.myAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyLog {
}
