/*
 * author     : dujun
 * date       : 2022/8/25 17:01
 * description: springSecurity获取用户详情和权限信息
 */

package com.dujun.springboot.config.springSecurity;

import com.dujun.springboot.mapper.RoleMapper;
import com.dujun.springboot.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.dujun.springboot.entity.User user = userMapper.selectByAccount(username);
        if (user == null){
            throw new RuntimeException("用户名密码错误");
        }
        List<String> permissions = userMapper.findPermission(user.getId()).getPermissionUrl();
        return new SecurityUser(user,permissions);
    }

}
