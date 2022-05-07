/*
 * author     : dujun
 * date       : 2022/5/6 17:49
 * description: 首页折线图实体类
 */

package com.dujun.springboot.entity.statics;

import com.dujun.springboot.entity.sonEntity.DayRunDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineChat {

    // 接口自动化折线图
    private HashMap<String, DayRunDetail> apiLine;

    // WEB自动化折线图
    private HashMap<String,DayRunDetail> webLine;

    // APP自动化折线图
    private HashMap<String,DayRunDetail> appLine;

}
