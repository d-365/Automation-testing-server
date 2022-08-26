/**
 * author     : dujun
 * date       : 2022/8/26 17:59
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.springSecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccess implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) {
        response.setContentType("application/json; charset=UTF-8");
        ServletOutputStream out;
        try {
            System.out.println("成功");
            out = response.getOutputStream();
            out.write("成功了".getBytes());
            out.flush();
            out.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}
