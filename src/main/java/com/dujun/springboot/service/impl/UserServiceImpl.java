package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.VO.exportVo.UserExport;
import com.dujun.springboot.config.springSecurity.SecurityUser;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.entity.sonEntity.UserInfo;
import com.dujun.springboot.mapper.UserMapper;
import com.dujun.springboot.service.UserService;
import com.dujun.springboot.utils.ExcelExportUtils;
import com.dujun.springboot.utils.ExcelUtils;
import com.dujun.springboot.utils.JwtUtil;
import com.dujun.springboot.utils.encryptionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private UserMapper userMapper;

    public Result<?> login(String account, String password) {
        Authentication authenticate;
        // 使用SpringSecurity进行用户查询校验
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account,password);
            authenticate = authenticationManager.authenticate(authenticationToken);
            if(Objects.isNull(authenticate)){
                return Result.error("1000","用户名或密码错误");
            }
        }catch (Exception e){
            return Result.error("1000","用户名或密码错误");
        }
        //生成token
        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();
        User user = securityUser.getUser();
        String token = JwtUtil.sign(user);
        user.setToken(token);
        // TODO 将token放入redis
        return Result.success(user);
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
        queryWrapper.eq("id",1);
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
        userInfo.setEnvId(envId);
        userInfo.setEnvName(EnvName);
        return Result.success(userInfo);
    }

    @Override
    public void export(HttpServletResponse response){
        // 查询用户列表数据
        Instant begin = Instant.now();
        List<UserExport> userList = userMapper.userExport();
        Instant end = Instant.now();
        Duration duration = Duration.between(begin,end);
        System.out.println("查询数据耗时：" + duration.toMillis());
        try {
            HSSFWorkbook hssfWorkbook =  ExcelUtils.createExcel(userList,"sheetName", UserExport.class);
            ExcelExportUtils.resReturn(response,hssfWorkbook);
        } catch (IllegalAccessException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
