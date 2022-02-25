package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dujun
 * @since 2021-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class CaseApiRelation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 接口ID
     */
    private Long apiId;

    /**
     * 用例ID
     */
    private Integer caseId;

    /**
     * 状态
     */
    private boolean status;

    // 用例详情
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ApiInfo apiInfo;

    /**
     * 排序
     */
    private int number;

    /**
     *type 用例接口类型  0 引用   1 复制
     */
    private int type;


    public CaseApiRelation(int caseId,Long apiId) {
        this.apiId = apiId;
        this.caseId = caseId;
    }

}
