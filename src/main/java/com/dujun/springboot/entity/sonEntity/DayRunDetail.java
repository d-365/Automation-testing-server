/**
 * author     : dujun
 * date       : 2022/2/16 16:45
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DayRunDetail {

    private int count;
    private int successCount;
    private int failedCount;

}
