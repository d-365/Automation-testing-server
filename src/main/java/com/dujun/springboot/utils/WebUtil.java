/**
 * author     : dujun
 * date       : 2022/8/29 11:32
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebUtil {

    public static void renderString(HttpServletResponse response, String data){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.append(data);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
