package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dujun.springboot.entity.sonEntity.PlanResultCount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dujun
 * @since 2022-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class PlanResult implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 计划ID
     */
    private Integer planId;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 0 : 执行中  1 执行成功  2 执行失败 3 执行超时
     */
    private Integer resultStatus;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 计划备注
     */
    private String remark;

    /**
     * 计划执行结果
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private PlanResultCount result;

    // 接口执行成功数量
    private Integer apiSuccessCount;

    // 接口执行成功数量
    private Integer apiFailedCount;

    // 用例执行成功数量
    private Integer caseSuccessCount;

    // 用例执行失败数量
    private Integer caseFailedCount;

    // 计划类型
    private Integer planType;

    /**
     * 测试报告地址
     */
    private String resultAddress;


}
