package com.dujun.springboot.mapper;

import com.dujun.springboot.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dujun
 * @since 2022-03-14
 */
public interface RoleMapper extends BaseMapper<Role> {

    /*
      角色列表
     */
    List<Role> ROLE_LIST(String roleName,Integer page,Integer size);

    Role userRole(Integer uId);

}
