/**
 * author     : dujun
 * date       : 2021/12/2 16:58
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.VO;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.entity.RspAsserts;
import com.dujun.springboot.entity.RspExtract;
import com.dujun.springboot.entity.sonEntity.ApiConsole;
import lombok.Data;
import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
// 单接口debug接口响应信息
public class DebugApi {

    // 接口响应内容（JSON）
    private JSONObject rspBodyJson;

    // responseHeader
    private ArrayList<HashMap<String,String>> rspHeaders;

    // 文本类型返回值
    private String textBody;

    //返回状态码
    private String rspStatusCode;

    //响应时间
    private long rspTime;

    //响应体大小
    private int rspBodySize;

    //响应断言
    private ArrayList<RspAsserts> rspAsserts;

    //参数提取
    private  ArrayList<RspExtract> rspExtract;

    //控制台消息
    private  ArrayList<ApiConsole> log;

}
