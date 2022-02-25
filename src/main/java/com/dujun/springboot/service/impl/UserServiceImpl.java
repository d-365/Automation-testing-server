package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.entity.UserEnv;
import com.dujun.springboot.entity.UserToken;
import com.dujun.springboot.entity.sonEntity.UserInfo;
import com.dujun.springboot.mapper.EnvMapper;
import com.dujun.springboot.mapper.UserEnvMapper;
import com.dujun.springboot.mapper.UserMapper;
import com.dujun.springboot.mapper.UserTokenMapper;
import com.dujun.springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
    @Resource
    private UserTokenMapper userTokenMapper;
    @Resource
    private UserEnvMapper userEnvMapper;
    @Resource
    private EnvMapper envMapper;

    // 用户登录
    public Result login(String account, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        queryWrapper.eq("password",password);
        // 查找用户是否存在
        User user = userMapper.selectOne(queryWrapper);
        if(user!=null){
            String token = getNewToken(String.valueOf(System.currentTimeMillis()),user.getId());
            UserToken userToken = userTokenMapper.selectOne(new QueryWrapper<UserToken>().eq("user_id",user.getId()));
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);
            if(userToken== null){
                userToken = new UserToken();
                userToken.setUserId(user.getId());
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expireTime);
                userTokenMapper.insert(userToken);
            }else {
                //判断token是否过期
                Date expire = userToken.getExpireTime();
                Date currentTime = new Date(now.getTime());
                if(expire.getTime()-currentTime.getTime() > 0){
                    return Result.success(userToken);
                } else {
                    userToken.setToken(token);
                    userToken.setUpdateTime(now);
                    userToken.setExpireTime(expireTime);
                    userTokenMapper.updateById(userToken);
                }
            }
            return Result.success(userToken);
        }else {
            return Result.error("用户名或密码错误");
        }
    }

    public Result saveUser(User user){
        String account = user.getAccount();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        List<User> selectAccount = userMapper.selectList(queryWrapper);
        System.out.println(selectAccount);
        if(selectAccount.size()==0){
            userMapper.insert(user);
            return Result.success();
        }else {
            return Result.error("账号已经存在");
        }
    }

    public Result<List<User>> userList(String account){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!Objects.equals(account, "") && account !=null ){
            queryWrapper.eq("account",account);
        }
        List<User> userList = userMapper.selectList(queryWrapper);
        return Result.success(userList);
    }

    public Integer updateUser(User user){
        return userMapper.updateById(user);
    }

    public Integer deleteUser(int id){
        return userMapper.deleteById(id);
    }

    private String getNewToken(String timeStr, Long userId) {
        Random random = new Random();
        String randomInt = String.valueOf(100000 + random.nextInt(899999));
        return timeStr + userId + randomInt;
    }

    public Result userInfo( int userId){
        UserInfo userInfo = new UserInfo();
        Integer envId = null;
        String EnvName = "";
        try{
            envId = userEnvMapper.selectOne(new QueryWrapper<UserEnv>().eq("user_id",userId)).getEnvId();
            EnvName = envMapper.selectById(envId).getEnvName();
        }catch (NullPointerException e){
            System.out.println(e);
        }
        userInfo.setEnvId(envId);
        userInfo.setEnvName(EnvName);
        return Result.success(userInfo);
    }

}
