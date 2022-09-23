/*
 * author     : dujun
 * date       : 2022/3/8 16:35
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.dujun.springboot.config.inteceptor.MyInterceptor;
import com.dujun.springboot.config.myFilter.MyFilter;
import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    /*拦截器*/
    @Autowired
    private MyInterceptor myInterceptor;

    /*注册拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**");
    }

    /*注册过滤器*/
    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean(){
        FilterRegistrationBean<MyFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new MyFilter());
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    /*跨域处理*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    /**
     * MybatisPlus分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 静态资源路径
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * 不对jar包的META-INF/MANIFEST.MF进行扫描加载
     */
    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
            }
        };
    }


}


