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
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

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

//    @TableField(exist = false)
//    private Set<GrantedAuthority> authorities;

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

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
//    }
//
//    @Override
//    public String getUsername() {
//        return account;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
