/*
 * author     : dujun
 * date       : 2022/6/16 13:52
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.config.springSecurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.entity.Role;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.mapper.RoleMapper;
import com.dujun.springboot.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//@Component
public class SysUserDetailService implements UserDetailsService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> user = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getAccount, s));
        if (user.size() == 0) {
            throw new UsernameNotFoundException("用户未找到");
        }
        User loginUser = user.get(0);
        List<GrantedAuthority> roles = new ArrayList<>();
        // 获取用户权限
        Role role = roleMapper.selectById(loginUser.getRoleId());
        for (String permission : role.getPermissionUrl()) {
            roles.add(new SimpleGrantedAuthority(permission));
        }
        return new org.springframework.security.core.userdetails.User(loginUser.getAccount(), loginUser.getPassword(), roles);
    }

}
