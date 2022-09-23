/**
 * author     : dujun
 * date       : 2022/9/6 9:53
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.VO.exportVo;

import com.dujun.springboot.config.annotation.ExportAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserExport {

    @ExportAnnotation("账号")
    private String account;

    @ExportAnnotation("密码")
    private String password;

    @ExportAnnotation("用户昵称")
    private String nickName;

    @ExportAnnotation("环境ID")
    private Integer envId;

}
