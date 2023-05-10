/*
 * author     : dujun
 * date       : 2022/8/29 16:19
 * description: 鉴权失败处理器
 */

package com.dujun.springboot.config.springSecurity;

import com.alibaba.fastjson.JSON;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) {
        Result<String> result = Result.error(String.valueOf(HttpStatus.FORBIDDEN.value()), "用户权限不足了");
        String json = JSON.toJSONString(result);
        WebUtil.renderString(response,json);
    }

}
