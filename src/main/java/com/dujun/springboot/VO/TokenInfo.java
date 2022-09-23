/**
 * author     : dujun
 * date       : 2022/9/7 10:52
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    private  Integer userId;
    private  String account;
}
