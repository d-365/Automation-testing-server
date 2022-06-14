package com.dujun.springboot.temp;

import antlr.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 定义切面类
@Aspect
@Component
public class AopServiceAspect {

    // 定义切入点(切入点指示器类型为 注解：检查方法中是否包含某个注解)
    @Pointcut("@annotation(MyAnnotationAop)")
    public void myPointCut() {};

    // 定义通知和切入点关联
    @Around(value = "myPointCut()")
    public Object round(ProceedingJoinPoint pjp){
        Object[] args = pjp.getArgs();
        System.out.println("方法中参数为："+ Arrays.toString(args));
        String name = pjp.getSignature().getName();
        System.out.println(name);
        Object result=null;
        try {
            System.out.println("环绕前置通知");
            result = pjp.proceed(args);
            System.out.println("环绕返回通知");
        } catch (Throwable throwable) {
            System.out.println("环绕异常通知");
            throwable.printStackTrace();
        }finally {
            System.out.println("result:===="+result);
            System.out.println("环绕最终通知");
            System.out.println();
        }
        return result;
    }

}