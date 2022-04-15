/*
 * author     : dujun
 * date       : 2022/4/14 17:36
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.entity.sonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebPageElement {

    private Integer id;
    private String name;
    private Integer parentId;
    private List<WebPageElement> children = new ArrayList<>();

}
