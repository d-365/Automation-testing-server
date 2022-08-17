/*
 * author     : dujun
 * date       : 2022/8/17 17:43
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName(value = "ding_talk_config", autoResultMap = true)
@Data
public class DingTalkConfig {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 机器人名称
     */
    @TableId(value = "name")
    private String name;

    /**
     * 机器人状态  0 启用  1 终止
     */
    @TableId(value = "status")
    private Integer status;


    /**
     * 需要@的用户列表 多个用，隔开
     */
    @TableId(value = "at_phone")
    private String atPhone;

    /**
     * 钉钉机器人地址
     */
    @TableId(value = "robot_address")
    private String robotAddress;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 0 启用 1 已删除
     */
    private Integer delFlag = 0;
}
