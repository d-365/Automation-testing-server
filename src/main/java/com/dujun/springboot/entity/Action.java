package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
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
 * @since 2022-04-06
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
public class Action implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 动作名称
     */
    private String name;

    /**
     * 0  WEB自动化  1 APP自动化  2 接口自动化
     */
    private Integer type;

    /**
     * 动作类型
     */
    private String actionKey;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父类ID
     */
    private Integer parentId;

    private Date createTime;

    private Date updateTime;

    /**
     * 0  启用  1 已删除
     */
    private Integer delFlag;

    @TableField(exist = false)
    private List<Action> children = new ArrayList<>();


}
