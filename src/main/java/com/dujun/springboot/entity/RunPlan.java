package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.catalina.LifecycleState;

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
public class RunPlan implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 计划名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 环境名称
     */
    private Integer envId;

    /**
     * 重试次数
     */
    @TableField("try_count")
    private Integer tryCount;

    /**
     * 计划运行状态（0：暂停 1运行）
     */
    private String status;

    /**
     * 是否开启定时（0 关闭 1 开启）
     */
    private Integer isClock;

    /**
     * 定时任务
     */
    private String clock;

    /**
     * 是否发送邮件（0 不发送  1 发送  2 仅失败发送）
     */
    private Integer isSendEmail;

    /**
     * 邮件地址 （ 多个邮件地址用 ； 分开）
     */
    private String sendEmail;

    /**
     * 计划类型 （0 接口 1 Web  2 App）
     */
    private Integer planType;

    //计划参数
    @TableField(exist = false)
    private PlanParam planParam;

    // 定时任务执行次数
    private Integer clockExecCount;

    // UI自动化执行 浏览器类型
    private String browserType;

    private Date createTime;

    private Date updateTime;

}
