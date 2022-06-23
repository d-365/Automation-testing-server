package com.dujun.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Menu;
import com.dujun.springboot.entity.RespPageEntity;
import com.dujun.springboot.entity.Role;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.service.impl.MenuServiceImpl;
import com.dujun.springboot.service.impl.RoleServiceImpl;
import com.dujun.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
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
    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private MenuServiceImpl menuService;


    //用户登录
    @PostMapping(value = "/login")
    public Result<?> login(@RequestBody User user){
        if(user == null || Objects.equals(user.getAccount(), "") || Objects.equals(user.getPassword(),"")){
            return Result.error("用户名或密码不能为空");
        }
        System.out.println(user.getAccount());
         return userService.login(user.getAccount(),user.getPassword());
    }

    //新增用户
    @PostMapping(value = "/save")
    public Result<?> save(@RequestBody User user){
        return userService.saveUser(user);
    }

    //用户列表
    @GetMapping(value = "/userList")
    public Result<List<User>> userList(String account,Integer roleId){
        System.out.println("用户列表");
        return userService.userList(account,roleId);
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
    public Result<?> userInfo(@RequestBody JSONObject user){
        Integer userId = user.getInteger("userId");
        return userService.userInfo(userId);
    }

    /**
     * 角色列表
     * @return RoleList
     */
    @GetMapping("/roleList")
    public RespPageEntity roleList(Role role,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        return roleService.roleList(role,page,size);
    }

    @GetMapping("/roles")
    public Result<List<Role>> roles(){
        return roleService.roles();
    }


    @PostMapping("/addRole")
    public Result<?> addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @GetMapping("/roleInfo/{roleId}")
    public Result<Role> roleInfo(@PathVariable("roleId") Integer roleId){
        return roleService.roleInfo(roleId);
    }

    @DeleteMapping("/delRole/{roleId}")
    public Result<?> delRole(@PathVariable("roleId") Integer roleId){
        return roleService.delRole(roleId);
    }

    @GetMapping("/menuList")
    public Result<List<Menu>> menuList(){
        return menuService.menuList();
    }

    @GetMapping("/userRole/{uId}")
    public Result<List<Menu>> UserRole(@PathVariable("uId") Integer uId){
        return roleService.UserRole(uId);
    }


}

