/**
 * author     : dujun
 * date       : 2021/12/14 15:11
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;

import java.util.ArrayList;

// 接口前置后置
@Data
//@TableName(autoResultMap = true)
public class ApiExec {

    // 描述
    private String name;

    //Action(动作类型)
    private ArrayList<String> action;

    //数据库配置
    private String  dbConfig;

    // params(sql)
    private  String params;

}
