/**
 * author     : dujun
 * date       : 2021/12/10 10:44
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.Data;

@Data
public class ApiDomain {

    //接口域名-名称
    private String domainName ;
    //接口域名-值
    private String domainValue;
    //接口域名-备注
    private String domainRemark;

}
