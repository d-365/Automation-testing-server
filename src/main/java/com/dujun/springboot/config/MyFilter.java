/*
 * author     : dujun
 * date       : 2022/3/8 16:46
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config;


import lombok.extern.log4j.Log4j2;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Log4j2
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig){
        log.debug("-----------------" +"容器创建过滤器时执行");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String requestUri = request.getRequestURI();
        if (requestUri.contains("favicon.ico")){
            System.out.println("过滤掉图标请求");
        }else {
            log.info("请求地址是："+requestUri);
            try {
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IOException | ServletException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    @Override
    public void destroy() {
        log.debug("-----------------" +"容器销毁过滤器时执行");
    }

}
