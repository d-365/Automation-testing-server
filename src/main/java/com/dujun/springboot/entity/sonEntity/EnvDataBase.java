/**
 * author     : dujun
 * date       : 2021/12/10 10:47
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.Data;

// 数据库配置
@Data
public class EnvDataBase {

    //数据库名称
    private String dbName;

    //数据库类型
    private String dbType;

    //数据库连接（JDBC_URL）
    private String jdbcUrl;

    //数据库账号名称
    private String dbUserName;

    //数据库账号密码
    private String dbPwd;

    //数据库备注
    private String dbRemark;


}
