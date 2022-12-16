/**
 * author     : dujun
 * date       : 2022/12/16 15:51
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.VO.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCode {

    // CRM后台
    private String crm;

    // 多融客后台
    private String drk;

    // 轻山后台
    private String qs;
}
