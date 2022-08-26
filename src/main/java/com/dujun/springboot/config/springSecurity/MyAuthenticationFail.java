/**
 * author     : dujun
 * date       : 2022/8/26 17:56
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.springSecurity;

import com.dujun.springboot.VO.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Component
public class MyAuthenticationFail implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        OutputStream out = response.getOutputStream();
        String result;
        ObjectMapper mapper = new ObjectMapper();

        if (e instanceof BadCredentialsException) {
            result = mapper.writeValueAsString(Result.error("用户名或者密码错误"));
        } else if (e instanceof DisabledException) {
            result = mapper.writeValueAsString(Result.error("该账户已禁用"));
        } else {
            result = mapper.writeValueAsString(Result.error(e.getMessage()));
        }
        out.write(result.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
