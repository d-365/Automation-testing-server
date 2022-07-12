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
 * APP-执行机
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MobilePhone implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机名称
     */
    private String name;

    /**
     * 平台信息
     */
    private String platForm;

    /**
     * 平台版本
     */
    private String platVersion;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 电量信息
     */
    private String level;

    /**
     * 删除标志
     */
    private Integer delFlag;

    private Date createTime;

    private Date updateTime;

    /**
     * 手机状态--  0 上线  1 下线  2 使用中
     */
    private Integer status;

}
