/*
 * author     : dujun
 * date       : 2022/6/15 14:50
 * description: SpringSecurity配置类
 */

package com.dujun.springboot.config.config;


import com.dujun.springboot.config.springSecurity.MyAuthenticationFail;
import com.dujun.springboot.config.springSecurity.MyAuthenticationSuccess;
import io.appium.java_client.pagefactory.OverrideWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationSuccess myAuthenticationSuccess;

    @Autowired
    MyAuthenticationFail myAuthenticationFail;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().formLogin()
                .loginPage("/user/login").loginProcessingUrl("/user/login")
                .usernameParameter("account").passwordParameter("password")
                .successHandler(myAuthenticationSuccess).failureHandler(myAuthenticationFail)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login").anonymous()
                .anyRequest().authenticated()
                ;
    }

}
