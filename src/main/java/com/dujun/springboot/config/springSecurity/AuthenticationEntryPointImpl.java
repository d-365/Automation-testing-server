/**
 * author     : dujun
 * date       : 2022/8/29 11:24
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.springSecurity;

import com.alibaba.fastjson.JSON;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl  implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Result<String> result = Result.error(String.valueOf(HttpStatus.UNAUTHORIZED.value()),"用户认证失败");

        String json = JSON.toJSONString(result);
        WebUtil.renderString(response,json);
    }

}
