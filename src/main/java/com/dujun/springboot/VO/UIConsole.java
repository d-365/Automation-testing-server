/*
 * author     : dujun
 * date       : 2022/4/19 10:36
 * description: Web自动化控制台
 */

package com.dujun.springboot.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UIConsole {
    private Integer code; //状态码 0 pass  1 error
    private String msg; //消息
}
