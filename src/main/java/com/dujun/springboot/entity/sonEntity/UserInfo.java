/**
 * author     : dujun
 * date       : 2022/1/7 15:16
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    // 环境ID
    private Integer envId;
    // 环境名称
    private String envName;
}
