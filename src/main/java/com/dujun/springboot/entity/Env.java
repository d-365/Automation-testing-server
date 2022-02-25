package com.dujun.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dujun.springboot.entity.sonEntity.ApiDomain;
import com.dujun.springboot.entity.sonEntity.EnvDataBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.BlobTypeHandler;

/**
 * <p>
 * 环境配置
 * </p>
 *
 * @author dujun
 * @since 2021-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class Env implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 环境ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 环境名称
     */
    private String envName;

    /**
     * 接口域名
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
//    @TableField(typeHandler = BlobTypeHandler.class)
    private ArrayList<ApiDomain> apiDomain;

    /**
     * 数据库配置
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
//    @TableField(typeHandler = BlobTypeHandler.class)
    private ArrayList<EnvDataBase> envDataBase;


}
