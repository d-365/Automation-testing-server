///*
// * author     : dujun
// * date       : 2022/6/15 14:50
// * description: 告诉大家我是干啥的
// */
//
//package com.dujun.springboot.config.config;
//
//import com.dujun.springboot.config.springSecurity.CustomizeAuthenticationEntryPoint;
//import com.dujun.springboot.config.springSecurity.MyPasswordEncoder;
//import com.dujun.springboot.config.springSecurity.SysUserDetailService;
//import com.dujun.springboot.config.springSecurity.UserAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
////@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private SysUserDetailService userService;
//
//    @Autowired
//    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
//
//    /**
//     配置Security加密解密方式
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new MyPasswordEncoder();
//    }
//
//    @Bean
//    UserAuthenticationFilter myAuthenticationFilter() throws Exception {
//        UserAuthenticationFilter filter = new UserAuthenticationFilter();
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setFilterProcessesUrl("/user/login");
//        return filter;
//    }
//
//    @Bean
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    /**
//     * 认证校验
//     * @param auth security中认证信息
//     *             设置security获取用户信息以及密码加密解密方式
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                csrf().disable()
//                .cors()
//                .and().authorizeRequests()
//                .antMatchers("/test/test1").hasAuthority("/interface")
//                .anyRequest().authenticated()
//                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//                .and().addFilterAt(myAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
//    }
//
//}
