package com.dujun.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.dujun.springboot.entity.sonEntity.ApiConsole;
import com.dujun.springboot.entity.sonEntity.ApiExec;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 接口详情
 * </p>
 *
 * @author dujun
 * @since 2021-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(autoResultMap = true)
public class ApiInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 标签
     */
    private String labels;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 接口分类id
     */
    private Long apiSuiteId;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 请求的协议+域名
     */
    @TableField(exist = false)
    private String domain;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 类型 0公共接口  业务流接口
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 未完成 已完成
     */
    private String status;

    /**
     * 前置处理器
     */
    @TableField(exist = false)
    private List<PlanRound> beforeExec;

    /**
     * 后置处理器
     */
    @TableField(exist = false)
    private List<PlanRound> afterExec;

    /**
     * 请求头
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<HashMap> reqHeader;

    /**
     * 请求params
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<HashMap> reqParams;

    /**
     * 请求body from-data格式
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<HashMap> reqBodyData;

    /**
     * 请求body json格式
     */
    private String reqBodyJson;

    /**
     * 请求body的类型
     */
    private String reqBodyType;

    /**
     * 请求提取参数
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<RspExtract> reqExtract;

    /**
     * 请求断言
     */
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<RspAsserts> reqAssert;

    /**
     * 调试响应对象
     */
    private String debugRsp;

    /**
     * createBy
     */
    private String createBy;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateBy
     */
    private String updateBy;

    /**
     * updateTime
     */
    private Date updateTime;

    //虚拟返回对象（key value）
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<HashMap> virtualResBody;

    //虚拟返回对象（Json）
    private String virtualResBodyJson;

    //虚拟返回对象类型
    private  String virtualBodyType;

    //请求cookies信息·
    @TableField(typeHandler= FastjsonTypeHandler.class)
    private ArrayList<HashMap> reqCookies;

    //是否启用域名
    private Boolean domainSwitch;

    // 接口运行结果
    @TableField(exist = false)
    private Boolean result;

    @TableField(exist = false)
    // 接口响应内容（JSON）
    private JSONObject rspBodyJson;

    // responseHeader
    @TableField(exist = false)
    private ArrayList<HashMap<String,String>> rspHeaders;

    // 文本类型返回值
    @TableField(exist = false)
    private String textBody;

    //返回状态码
    @TableField(exist = false)
    private String rspStatusCode;

    //响应时间
    @TableField(exist = false)
    private long rspTime;

    //响应体大小
    @TableField(exist = false)
    private int rspBodySize;

    //响应断言
    @TableField(exist = false)
    private ArrayList<RspAsserts> rspAsserts;

    //参数提取
    @TableField(exist = false)
    private  ArrayList<RspExtract> rspExtract;

    //控制台消息
    @TableField(exist = false)
    private  ArrayList<ApiConsole> log;

    @TableField(typeHandler= FastjsonTypeHandler.class)
    private List<Integer> setUpIds;

    @TableField(typeHandler= FastjsonTypeHandler.class)
    private List<Integer> tearDownIds;

}
