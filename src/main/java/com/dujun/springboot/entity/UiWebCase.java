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
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UiWebCase implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id")
    private Integer id;

    /**
     * 用例名称
     */
    private String name;

    private Integer parentId;

    /**
     * 0  文件夹  1 用例
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private List<UiWebCase> children = new ArrayList<>();

    /**
     * 1 WEB  2 App
     */
    private Integer caseType;

    private Integer delFlag;

}
