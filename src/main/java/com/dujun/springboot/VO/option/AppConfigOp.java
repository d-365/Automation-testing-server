/*
 * author     : dujun
 * date       : 2022/6/28 17:34
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.VO.option;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppConfigOp {
    private Integer id;
    private String name;
    private Integer status = 1;

}
