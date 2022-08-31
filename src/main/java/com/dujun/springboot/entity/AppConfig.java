package com.dujun.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * APP 启动配置表
 * </p>
 *
 * @author dujun
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class AppConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * App名称
     */
    private String name;

    /**
     * App包名
     */
    private String appPackage;

    /**
     * App启动页
     */
    private String appActivity;

    /**
     * 启动前是否重置APP    0 不重置  1 重置
     */
    private Boolean noReset;

    /**
     * 自动化执行引擎  appium uiaotomator1  uiautomator2
     */
    private String automationName;

    /**
     * 其他启动配置信息
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private List<JSONObject> others = new ArrayList<>();

    /**
     * 删除标志 0 未删除 1 已删除
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
