package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dujun.springboot.VO.AssertConsole;
import com.dujun.springboot.VO.UIConsole;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 计划执行结果详情表
 * </p>
 *
 * @author dujun
 * @since 2022-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class PlanResultDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer planResultId;

    private Integer caseId;

    private Integer caseNumber;

    private Long apiId;

    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ApiInfo apiInfo;

    private Boolean result;

    private String createTime;

    @TableField(typeHandler= FastjsonTypeHandler.class)
    private List<UIConsole> resultConsole;

    private String caseName;

    @TableField(exist = false)
    private List<WebCaseStep> caseSteps;

    /**
     * web断言结果
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private List<AssertConsole> assertResult;


}
