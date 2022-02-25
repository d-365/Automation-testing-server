/**
 * author     : dujun
 * date       : 2022/1/24 14:51
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity;

import lombok.Data;

import java.util.List;

@Data
public class RespPageEntity {
    private List<?> data;
    private Long total;
}
