/*
 * author     : dujun
 * date       : 2022/8/25 17:01
 * description: springSecurity获取用户详情和权限信息
 */

package com.dujun.springboot.config.springSecurity;

import com.dujun.springboot.entity.Role;
import com.dujun.springboot.mapper.RoleMapper;
import com.dujun.springboot.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        com.dujun.springboot.entity.User user = userMapper.selectByAccount(username);
        Role role = roleMapper.selectById(user.getRoleId());
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(role.getRoleName());
        role.getPermissionUrl().forEach((s)-> authorityList.add(new SimpleGrantedAuthority(s)));
        return new User(user.getAccount(),user.getPassword(),authorityList);
    }

}
