///*
// * author     : dujun
// * date       : 2022/6/15 18:12
// * description: 告诉大家我是干啥的
// */
//
//package com.dujun.springboot.config.springSecurity;
//
//import com.alibaba.fastjson.JSON;
//import com.dujun.springboot.VO.Result;
//import com.dujun.springboot.config.common.ResultCode;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
////@Component
//public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
//        Result<?> result = Result.error(String.valueOf(ResultCode.USER_NOT_LOGIN.getCode()),ResultCode.USER_NOT_LOGIN.getMessage());
//        httpServletResponse.setContentType("text/json;charset=utf-8");
//        httpServletResponse.getWriter().write(JSON.toJSONString(result));
//    }
//
//}
