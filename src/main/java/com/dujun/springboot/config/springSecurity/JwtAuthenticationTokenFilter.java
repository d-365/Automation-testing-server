///*
// * author     : dujun
// * date       : 2022/6/16 10:50
// * description: 告诉大家我是干啥的
// */
//
//package com.dujun.springboot.config.springSecurity;
//
//import com.dujun.springboot.entity.User;
//import com.dujun.springboot.utils.JwtUtil;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Objects;
//
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = request.getHeader("token");
//        System.out.println("token"+token);
//        if (token == null || !JwtUtil.verify(token) ) {
//            return;
//        }else {
//            Integer uId = Integer.valueOf(Objects.requireNonNull(JwtUtil.getTokenStr(token, "uId")));
//            User user = new User();
//            user.setId(uId);
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(token, "");
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        filterChain.doFilter(request,response);
//    }
//
//}
