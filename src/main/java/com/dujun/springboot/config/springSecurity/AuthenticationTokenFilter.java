/**
 * author     : dujun
 * date       : 2022/8/29 11:04
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.springSecurity;

import com.dujun.springboot.entity.User;
import com.dujun.springboot.mapper.UserMapper;
import com.dujun.springboot.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String userId;
        userId = JwtUtil.getTokenStr(token,"uId");
        if (userId == null || userId.equals("null")){
            throw new RuntimeException("token非法");
        }
        // TODO 从Redis中取出对应的token
        User user = userMapper.selectById(userId);
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUser(user);
        List<String> permissions = userMapper.findPermission(user.getId()).getPermissionUrl();
        securityUser.setPermission(permissions);
        // TODO Redis取不出来 - token失效
        // TODO redis取出来--校验通过
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUser,null,securityUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }

}
