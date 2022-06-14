/**
 * author     : dujun
 * date       : 2021/12/31 10:19
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.inteceptor;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 自定义拦截器
 * */
@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    //请求处理前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        if (token == null || !JwtUtil.verify(token) ) {
            JSONObject jsonObject = new JSONObject(){{
                put("msg","用户token已失效");
                put("code","201004");
            }};
            out = response.getWriter();
            out.append(jsonObject.toJSONString());
            return false;
        }
        return true;
    }

    //请求处理后，渲染ModelAndView前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
    }

    //渲染ModelAndView后调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
