/**
 * author     : dujun
 * date       : 2022/1/24 14:51
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespPageEntity {
    private List<?> data;
    private Long total;
}
