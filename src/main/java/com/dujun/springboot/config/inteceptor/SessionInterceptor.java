/**
 * author     : dujun
 * date       : 2021/12/31 10:19
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.inteceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dujun.springboot.entity.UserToken;
import com.dujun.springboot.mapper.UserTokenMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义拦截器，实现校验用户是否登陆，未登录返回错误信息：尚未登陆。登陆成功则放行
 * */
@Component
@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    @Resource
    private UserTokenMapper userTokenMapper;

    //请求处理前调用,我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("=============进入拦截器了=================================");
        String token = request.getHeader("token");
        if (token == null) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            try {
                JSONObject res = new JSONObject();
                res.put("success", false);
                res.put("message", "用户未登录！");
                res.put("code","201004");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            } catch (Exception e) {
                response.sendError(500);
                e.printStackTrace();
                return false;
            }
        }else {
            UserToken userToken = userTokenMapper.selectOne(new QueryWrapper<UserToken>().eq("token",token));
            if(userToken == null){
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = null;
                try {
                    JSONObject res = new JSONObject();
                    res.put("success", false);
                    res.put("message", "用户未登录！");
                    res.put("code","201004");
                    out = response.getWriter();
                    out.append(res.toString());
                    return false;
                } catch (Exception e) {
                    response.sendError(500);
                    e.printStackTrace();
                    return false;
                }
            }else{
                Date expire = userToken.getExpireTime();
                Date now = new Date();
                if(expire.getTime()-now.getTime() < 0 ){
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = null;
                    try {
                        JSONObject res = new JSONObject();
                        res.put("success", false);
                        res.put("message", "用户未登录！");
                        res.put("code","201004");
                        out = response.getWriter();
                        out.append(res.toString());
                        return false;
                    } catch (Exception e) {
                        response.sendError(500);
                        e.printStackTrace();
                        return false;
                    }
                }
            }

        }
        return true;
    }

    //请求处理后，渲染ModelAndView前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("=============进入拦截器了,请求处理后，渲染ModelAndView前调用。=================================");
    }

    //渲染ModelAndView后调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("=============进入拦截器了,渲染ModelAndView后调用。=================================");
    }






}
