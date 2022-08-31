package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author dujun
 * @since 2022-04-14
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
public class WebCaseStep implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * WEB自动化用例ID
     */
    private Integer caseId;

    // 步骤排序
    private Integer sort;

    /**
     * 用例描述
     */
    private String stepDescribe;

    /**
     * action操作ID
     */
    private Integer actionId;

    /**
     * action操作概述
     */
    private String actionSummary;

    /**
     * 元素信息
     */
    @TableField(updateStrategy= FieldStrategy.IGNORED)
    private Integer elementId;

    /**
     * 用例步骤启用 0 启用 1 禁用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String actionValue;

    /**
     * 断言类型
     */
    private String assertType;

    /**
     * 断言值
     */
    private String assertValue;


}
