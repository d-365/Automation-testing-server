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
public class PageElement implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 元素名称
     */
    private String elementName;

    /**
     * 元素类型
     */
    private String elementType;

    /**
     * 定位方式
     */
    private String locationWay;

    /**
     * 定位值
     */
    private String locationValue;

    /**
     * 备注
     */
    private String remark;

    private Date createTime;

    private Date updateTime;

    private Integer pageId;


}
