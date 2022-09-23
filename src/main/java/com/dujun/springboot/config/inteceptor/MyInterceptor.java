/**
 * author     : dujun
 * date       : 2021/12/31 10:19
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.inteceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dujun.springboot.config.annotation.SameUrlData;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义拦截器
 * */
@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    //请求处理前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(request.getSession().getAttribute("repeatData"));
        log.debug("请求前拦截");
        return true;
    }

    //请求处理后，渲染ModelAndView前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String uri = request.getRequestURI();
    }

    //渲染ModelAndView后调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
