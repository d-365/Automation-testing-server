/*
 * author     : dujun
 * date       : 2022/3/8 16:35
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.dujun.springboot.config.inteceptor.MyInterceptor;
import com.dujun.springboot.config.myFilter.MyFilter;
import com.dujun.springboot.config.myListener.MyListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    /*注册监听器*/
    @Bean
    public ServletListenerRegistrationBean<MyListener> myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new MyListener());
        return registrationBean;
    }


    /*注册过滤器*/
    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean(){
        FilterRegistrationBean<MyFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new MyFilter());
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

//    /*注册拦截器*/
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> excludePath = new ArrayList<String>(){{
//            add("/user/login");
//            add("/user/save");
//            add("/loginPlus");
//            add("/test/*");
//            add("/error");
//        }};
//        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns(excludePath);
//    }

    /*跨域处理*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    /**
     * MybatisPlus分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }


}
