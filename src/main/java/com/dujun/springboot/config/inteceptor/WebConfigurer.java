/**
 * author     : dujun
 * date       : 2021/12/31 10:49
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.inteceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 来注册拦截器
 *
 * **/
//@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    /**登陆校验的拦截器注入*/
    @Autowired
    private SessionInterceptor sessionInterceptor;

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login");
    }

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

}
