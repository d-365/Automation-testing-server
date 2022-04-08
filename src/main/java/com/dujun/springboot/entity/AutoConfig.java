package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AutoConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 配置项值
     */
    private String value;

    /**
     * 说明
     */
    private String remark;

    /**
     * 0  启用  1 禁用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    /**
     * 0 未删除  1 已删除
     */
    private Integer delFlag;


}
