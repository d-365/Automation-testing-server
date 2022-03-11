package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2021-11-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //用户登录
    @PostMapping(value = "/login")
    public Result<?> login(@RequestBody User user){
        if(user == null || Objects.equals(user.getAccount(), "") || Objects.equals(user.getPassword(),"")){
            return Result.error("用户名或密码不能为空");
        }
         return userService.login(user.getAccount(),user.getPassword());
    }

    //新增用户
    @PostMapping(value = "/save")
    public Result<?> save(@RequestBody User user){
        return userService.saveUser(user);
    }

    //用户列表
    @GetMapping(value = "/userList")
    public Result<List<User>> userList(String account){
        return userService.userList(account);
    }

    //更新用户
    @PutMapping(value = "/updateUser")
    public Integer updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    //删除用户
    @DeleteMapping(value = "/deleteUser/{id}")
    public Integer deleteUser(@PathVariable("id") int id){
        return userService.deleteUser(id);
    }


    @PostMapping("/loginInfo")
    public Result<?> userInfo(@RequestBody int userId){
        return userService.userInfo(userId);
    }


}

