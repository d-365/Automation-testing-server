/**
 * author     : dujun
 * date       : 2022/7/28 14:50
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import com.dujun.springboot.common.myAnnotation.DingRobotAn;
import com.dujun.springboot.common.myAnnotation.MyLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import java.io.Serializable;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Temp {

    @DingRobotAn
    public static void main(String[] args) throws Exception {
        System.out.println("运行代码");
    }

    @Before
    public void test_fun1() {
        Locale locale = Locale.forLanguageTag("en");
        System.out.println(locale.getDisplayName());
        System.out.println(Arrays.toString(Locale.getISOLanguages()));
    }

}

class myThread extends Thread {

    private ArrayList<Integer> myList;

    myThread(ArrayList<Integer> list) {
        this.myList = list;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("执行5次了 退出");
        }
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person implements Serializable {
    String name = "dujun";
}


@Data
class Son implements Serializable {
    String name = "son";

    Son() {
        System.out.println("构造方法");
    }

    public static void fun1() {
        System.out.println("静态方法");
    }
}

@Aspect
@Component
class log{

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.dujun.springboot.common.myAnnotation.MyLog)")
    public void logPointCut(){};

    @Around("logPointCut()")
    public Object round(ProceedingJoinPoint point) throws Throwable {
        System.out.println("开始执行");
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("执行结束");
        return result;
    }

}