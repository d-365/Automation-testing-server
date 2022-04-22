package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Menu;
import com.dujun.springboot.entity.RespPageEntity;
import com.dujun.springboot.entity.Role;
import com.dujun.springboot.mapper.MenuMapper;
import com.dujun.springboot.mapper.RoleMapper;
import com.dujun.springboot.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-03-14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public RespPageEntity roleList(Role role,Integer page,Integer size) {
        // 默认从0开始
        if (page != null && size != null) {
            page = (page-1)*size;
        }
        String roleName = role.getRoleName();
        List<Role> roles = roleMapper.ROLE_LIST(roleName,page,size);
        return new RespPageEntity(roles, (long) roles.size());
    }

    @Override
    public Result<?> addRole(Role role) {
        Integer roleId = role.getId();
        if(roleId != null){
            Stream<String> stringStream = role.getPermissionUrl().stream().filter(Objects::nonNull);
            List<String> filterList = stringStream.collect(Collectors.toList());
            role.setPermissionUrl(filterList);
            roleMapper.updateById(role);
        }else {
            roleMapper.insert(role);
        }
        return Result.success();
    }

    @Override
    public Result<Role> roleInfo(Integer roleId) {
        Role role = roleMapper.selectById(roleId);
        return Result.success(role);
    }

    @Override
    public Result<?> delRole(Integer roleId) {
        if (roleId != null){
            try {
                roleMapper.delete(new QueryWrapper<Role>().eq("id",roleId));
            }catch (RuntimeException e){
                return Result.error("请先删除该角色下所有账户");
            }
        }
        return Result.success();
    }

    @Override
    public Result<List<Role>> roles() {
        return Result.success(roleMapper.selectList(null));
    }

    @Override
    public Result<List<Menu>> UserRole(Integer uId){
        Role userRole = roleMapper.userRole(uId);
        List<Menu> menus = new ArrayList<>();
        for (String s : userRole.getPermissionUrl()) {
            Menu menu = menuMapper.selectOne(new QueryWrapper<Menu>().eq("url",s));
            if (menu!=null){
                menus.add(menu);
            }
        }

        List<Menu> parent = new ArrayList<>();
        List<Menu> finalMenu = new ArrayList<>();

        for (Menu menu : menus) {
            if (menu.getParentId() == null){
                parent.add(menu);
                finalMenu.add(menu);
            }else {
                for (Menu parentMenu : parent) {
                    if (Objects.equals(parentMenu.getId(), menu.getParentId())){
                        parentMenu.getChildren().add(menu);
                        parent.add(menu);
                        break;
                    }
                }
            }
        }
        return Result.success(finalMenu);

    }


}
