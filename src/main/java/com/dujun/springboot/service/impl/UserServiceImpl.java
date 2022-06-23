package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Role;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.entity.sonEntity.UserInfo;
import com.dujun.springboot.mapper.RoleMapper;
import com.dujun.springboot.mapper.UserMapper;
import com.dujun.springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.utils.JwtUtil;
import com.dujun.springboot.utils.encryptionUtils;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2021-11-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Resource
    private UserMapper userMapper;

    public Result<?> login(String account, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        queryWrapper.eq("password",encryptionUtils.md5Encryption(password));
        // 查找用户是否存在
        User user = userMapper.selectOne(queryWrapper);
        if(user!=null){
            String token = JwtUtil.sign(user);
            user.setPassword(null);
            user.setToken(token);
            return Result.success(user);
        }else {
            return Result.error("用户名或密码错误");
        }

    }

    public Result<?> saveUser(User user){
        String account = user.getAccount();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        List<User> selectAccount = userMapper.selectList(queryWrapper);
        if(selectAccount.size()==0){
            user.setPassword(encryptionUtils.md5Encryption(user.getPassword()));
            userMapper.insert(user);
            return Result.success();
        }else {
            return Result.error("账号已经存在");
        }
    }

    public Result<List<User>> userList(String account,Integer roleId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!Objects.equals(account, "") && account !=null ){
            queryWrapper.eq("account",account);
        }
        if (roleId !=null){
            queryWrapper.eq("role_id",roleId);
        }
        List<User> userList = userMapper.selectList(queryWrapper);
        return Result.success(userList);
    }

    public Integer updateUser(User user){
        user.setPassword(encryptionUtils.md5Encryption(user.getPassword()));
        return userMapper.updateById(user);
    }

    public Integer deleteUser(int id){
        return userMapper.deleteById(id);
    }

    public Result<?> userInfo( int userId){
        UserInfo userInfo = new UserInfo();
        Integer envId = null;
        String EnvName = "";
//        try{
////            envId = userEnvMapper.selectOne(new QueryWrapper<UserEnv>().eq("user_id",userId)).getEnvId();
////            EnvName = envMapper.selectById(envId).getEnvName();
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }
        userInfo.setEnvId(envId);
        userInfo.setEnvName(EnvName);
        return Result.success(userInfo);
    }

}
