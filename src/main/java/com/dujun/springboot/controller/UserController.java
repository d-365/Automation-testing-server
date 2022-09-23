package com.dujun.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.VO.TokenInfo;
import com.dujun.springboot.base.BaseController;
import com.dujun.springboot.entity.Menu;
import com.dujun.springboot.entity.RespPageEntity;
import com.dujun.springboot.entity.Role;
import com.dujun.springboot.entity.User;
import com.dujun.springboot.mapper.UserMapper;
import com.dujun.springboot.service.impl.MenuServiceImpl;
import com.dujun.springboot.service.impl.RoleServiceImpl;
import com.dujun.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
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
public class UserController extends BaseController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private MenuServiceImpl menuService;

    @Resource
    UserMapper userMapper;

    @GetMapping("/export")
    public void export(HttpServletResponse response){
        userService.export(response);
    }

    @GetMapping("/download")
    public byte[] download(HttpServletRequest request) throws IOException, InterruptedException {
        TokenInfo tokenInfo = tokenInfo();
        //当前下载百分比
        // 下载次数
        int downloadCount = 0;
        BigDecimal bigDecimal;
        // 文件
        File file = new File("C:\\Users\\dujun\\Downloads\\vueTemp.xls");
        BigDecimal totalSize = new BigDecimal(file.length());
        byte[] barrel = new byte[1024];
        InputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (fileInputStream.read(barrel)!=-1 ){
            outputStream.write(barrel);
            downloadCount++;
            bigDecimal = new BigDecimal(downloadCount*1024).divide(totalSize,2,BigDecimal.ROUND_DOWN);
            // 计算下载的进度
            String progress = bigDecimal.multiply(new BigDecimal(100)).toString();
            //TODO 将获取到的下载进度放入redis(key 用户ID value 下载进度)
            System.out.println("第"+downloadCount+"次下载");
            int data = totalSize.intValue() - (downloadCount*1024);
            System.out.println("剩余"+data+"未下载");
            Thread.sleep(3000);
        }
        outputStream.flush();
        outputStream.close();
        return outputStream.toByteArray();
    }

    @GetMapping("/progress")
    public Result<String> progress(HttpServletRequest request) {
        //TODO 从Redis中获取对应用户ID所对应的下载进度
        return Result.success("操作成功");
    }

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

    /**
     * 用户列表
     * @param account 账号
     * @param roleId 密码
     * @return Result
     */
    @GetMapping(value = "/userList")
    @PreAuthorize("hasAuthority('/user/userList')")
    public Result<List<User>> userList(String account,Integer roleId){
        TokenInfo tokenInfo = tokenInfo();
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

    @GetMapping("/sql")
    public Result<?> sqlTest(String account){
        User user = userMapper.sqlTest(account);
        return Result.success(user);
    }

}

