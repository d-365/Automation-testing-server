package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据库配置表
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DbConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 数据库地址
     */
    private String jdbcUrl;

    /**
     * 用户名
     */
    private String account;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据库类型 0 mysql  1 redis
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 0 启用 1 已删除
     */
    private Integer delFlag;


}
