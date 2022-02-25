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
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CaseCategory implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父ID
     */
    private Integer parentId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * 0 文件夹 1 用例
     */
    private Integer type;

    @TableField(exist = false)
    private List<CaseCategory> children = new ArrayList<>();


}
