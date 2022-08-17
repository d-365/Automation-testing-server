/**
 * author     : dujun
 * date       : 2021/12/21 17:49
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tmkApply {
    private String loanId;
    private HashMap<String, Object> payload;
}
