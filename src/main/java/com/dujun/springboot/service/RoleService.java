package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.Menu;
import com.dujun.springboot.entity.RespPageEntity;
import com.dujun.springboot.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2022-03-14
 */
public interface RoleService extends IService<Role> {

    RespPageEntity roleList(Role role,Integer page,Integer size);

    Result<?> addRole(Role role);

    Result<Role> roleInfo(Integer roleId);

    Result<?> delRole(Integer roleId);

    Result<List<Role>> roles();

    Result<List<Menu>> UserRole(Integer uId);

}
