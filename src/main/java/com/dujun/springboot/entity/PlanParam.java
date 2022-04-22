package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dujun.springboot.common.selenium.SeleniumUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dujun
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class PlanParam  implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 计划ID
     */
    private Integer planId;

    /**
     * 接口ID集合
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<Integer> apiIds;

    /**
     * 用例ID集合
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<Integer> caseIds;


}
