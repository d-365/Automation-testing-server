package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author dujun
 * @since 2022-03-14
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class Role implements Serializable  {

    private static final long serialVersionUID=1L;

    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private List<String> permissionUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标志  0 未删除  1 删除
     */
    private Integer delFlag;


}
