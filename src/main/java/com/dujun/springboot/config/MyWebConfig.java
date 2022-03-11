/*
 * author     : dujun
 * date       : 2022/3/8 16:35
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config;

import com.dujun.springboot.config.inteceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Autowired
    MyInterceptor myInterceptor;

    @Bean
    public ServletListenerRegistrationBean<MyListener> myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new MyListener());
        return registrationBean;
    }

    @Bean
    public MyInterceptor getMyInterceptor(){
        return new MyInterceptor();
    }

    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean(){
        FilterRegistrationBean<MyFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new MyFilter());
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<String>(){{
            add("/user/login");
            add("/user/save");
            add("/loginPlus");
        }};
        registry.addInterceptor(myInterceptor).excludePathPatterns(excludePath).addPathPatterns("/**");
    }


}
