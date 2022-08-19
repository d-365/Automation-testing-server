package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 钉钉机器人提醒配置表
 * </p>
 *
 * @author dujun
 * @since 2022-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DingRobot implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 机器人名称
     */
    private String name;

    /**
     * 机器人状态  0 启用  1 终止
     */
    private Integer status;

    /**
     * 需要@的用户列表 多个用，隔开
     */
    private String atPhone;

    /**
     * 执行时机 0 测试计划执行前  1 测试计划执行后
     */
    private Integer robotType;

    /**
     * 钉钉机器人地址
     */
    private String robotAddress;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date updateTime;

    /**
     * 0 未删除 1已删除
     */
    @JsonIgnore
    @TableLogic
    private Integer delFlag;


}
