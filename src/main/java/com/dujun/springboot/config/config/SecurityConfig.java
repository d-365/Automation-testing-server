/*
 * author     : dujun
 * date       : 2022/6/15 14:50
 * description: SpringSecurity配置类
 */

package com.dujun.springboot.config.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() //表单登陆1
                .and()
                .authorizeRequests()
                .antMatchers("").permitAll()
                .anyRequest().authenticated(); //访问任何资源都需要身份认证5
    }

}
