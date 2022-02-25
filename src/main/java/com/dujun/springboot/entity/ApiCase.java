package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.HashMap;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.aspectj.weaver.patterns.HasMemberTypePattern;

/**
 * <p>
 * 
 * </p>
 *
 * @author dujun
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class ApiCase implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 外键分类Id
     */
    private Long categoryId;

    /**
     * 用例等级
     */
    private String caseGrade;

    /**
     * 维护人
     */
    private String casePerson;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * 用例状态
     */
    private String caseType;

    /**
     * 用例参数
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<HashMap> params;

    /**
     * 迭代次数
     */
    private Integer loopCount;

    /**
     * 失败重试
     */
    private Integer failContinue;


    // 用例ID列表
    @TableField(exist = false)
    private ArrayList<ApiInfo> step;

    // 用例名称
    @TableField(exist = false)
    private String categoryName;

    // 用例执行结果
    @TableField(exist = false)
    private Boolean result;

}
