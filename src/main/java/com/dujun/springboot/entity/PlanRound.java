package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dujun
 * @since 2022-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlanRound implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 前置后置操作说明
     */
    private String name;

    /**
     * actionId
     */
    private Integer actionId;

    /**
     * 数据库 or 文件对象
     */
    private String actionKey;

    /**
     * 前置后置 value值
     */
    private String actionValue;

    /**
     * 0 前置  1 后置
     */
    private Integer type;

    private Integer planId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String operateData;

    private String params;



}
