package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author dujun
 * @since 2021-11-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    private static final long serialVersionUID=1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员登陆名称
     */
    private String account;

    /**
     * 管理员登陆密码
     */
    private String password;

    /**
     * 管理员显示昵称
     */
    private String nickName;

    /**
     * 是否锁定 0未锁定 1已锁定无法登陆
     */
    private Integer locked;

    /**
     * 登录token
     */
    @TableField(exist = false)
    private String token;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 环境ID
     */
    private Integer envId;

}
