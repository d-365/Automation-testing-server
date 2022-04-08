/**
 * author     : dujun
 * date       : 2021/12/21 12:04
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.dujun.springboot.Api.qyh.Qyh;
import com.dujun.springboot.Api.tmk.Tmk;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.data.ApiOrderData;
import com.dujun.springboot.entity.sonEntity.tmkApply;
import com.dujun.springboot.entity.tools.TmkApply;
import com.dujun.springboot.mapper.ToolsMapper;
import com.dujun.springboot.service.ToolsService;
import com.dujun.springboot.tools.RandomValue;
import com.dujun.springboot.utils.MysqlTools;
import com.dujun.springboot.utils.request;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Callable;

/*
工具类
 */

@DS("qsTest")
@Service
@Slf4j
public class ToolsServiceImpl implements ToolsService {

    @Resource
    private ToolsMapper toolsMapper;

    public static MysqlTools mysqlConnection(){
        return new MysqlTools("jdbc:mysql://118.31.184.240:3306","root","3wHNY2Bq");
    }

    /**
     * 删除用户订单 + 注册信息
     */
    public static void deleteUserOrder(String phone){
        String delOrder = String.format("DELETE FROM jgq.think_loan WHERE phone = %s",phone);
        String delUser = String.format("DELETE FROM jgq.think_user WHERE phone = %s",phone);
        MysqlTools mysqlTools = ToolsServiceImpl.mysqlConnection();
        mysqlTools.execute(delOrder);
        mysqlTools.execute(delUser);
        mysqlTools.close();
    }

    /**
     * 电销填单
     * @param phone 手机号
     * @param city 城市
     */
    @DS("qsTest")
    public Result tmkApply(String phone, String city ){
        MysqlTools mysqlTools = ToolsServiceImpl.mysqlConnection();
        if (phone == null || Objects.equals(phone, "")){
            phone = RandomValue.getTel();
        }
        if (city == null || Objects.equals(city,"")){
            city = RandomValue.getAddress();
        }

        ToolsServiceImpl.deleteUserOrder(phone);
        //用户填单
        HashMap<Object, Object> applyData = ApiOrderData.tmk_data(phone,city);
        JSONObject result = new Tmk().apply(applyData);
        String msg = result.getString("msg");
        if(!msg.contains("系统异常")){
            //  获取填单ID
            String loanId = toolsMapper.id_byPhone(phone);
            tmkApply tmkApply = new tmkApply();
            tmkApply.setLoanId(loanId);
            tmkApply.setPayload(applyData);
            mysqlTools.close();
            request.close();
            return Result.success(tmkApply);
        }
        return Result.error("订单新增失败");
    }

    //电销填单（循环）
    public Result tmkApplyLoop(TmkApply tmkApplyData){
        MysqlTools mysqlTools = ToolsServiceImpl.mysqlConnection();
        String phone = tmkApplyData.getPhone();
        String city = tmkApplyData.getCity();
        int loop = tmkApplyData.getLoop();
        int status = tmkApplyData.getStatus();
        Long step = tmkApplyData.getStep();
        ArrayList<String> orderList = new ArrayList<>();
        if(status == 0){
            for (int i = 0; i < loop; i++) {
                if(phone == null ){
                    phone = RandomValue.getTel();
                }
                if(city == null){
                    city = RandomValue.getAddress();
                }
                ToolsServiceImpl.deleteUserOrder(phone);
                //用户填单
                HashMap<Object, Object> applyData = ApiOrderData.tmk_data(phone,city);
                JSONObject result = new Tmk().apply(applyData);
                String msg = result.getString("msg");
                if(!msg.contains("系统异常") && (i==0 || i == loop-1) ){
                    //  获取填单ID
                    String loanId = toolsMapper.id_byPhone(phone);
                    orderList.add(loanId);
                }
                if(step != 0){
                    try {
                        Thread.sleep(step);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            mysqlTools.close();
        }else {
            return Result.success("结束填单");
        }

        return Result.success(orderList);
    }

    //电销填单随机
    public Result tmkApplyRandom(TmkApply tmkApplyData){
        MysqlTools mysqlTools = ToolsServiceImpl.mysqlConnection();
        int loop = tmkApplyData.getLoop();
        int status = tmkApplyData.getStatus();
        Long step = tmkApplyData.getStep();
        ArrayList<String> orderList = new ArrayList<>();

        if(status == 0){
            for (int i = 0; i < loop; i++) {
                String  phone = RandomValue.getTel();
                String city = RandomValue.getAddress();

                ToolsServiceImpl.deleteUserOrder(phone);
                //用户填单
                HashMap<Object, Object> applyData = ApiOrderData.tmk_data(phone,city);
                JSONObject result = new Tmk().apply(applyData);
                String msg = result.getString("msg");
                if(!msg.contains("系统异常") && (i==0 || i == loop-1) ){
                    //  获取填单ID
                    String loanId = toolsMapper.id_byPhone(phone);
                    orderList.add(loanId);
                }
                if(step != 0){
                    try {
                        Thread.sleep(step);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            mysqlTools.close();
        }else {

            return Result.success("结束填单");
        }

        return Result.success(orderList);
    }

    // 轻易花填单
    public Result qyhApply(String phone, String city ){
        //填单数据
        HashMap payload = ApiOrderData.qyh_applyData(city);
        System.out.println(payload);
        System.out.println(JSON.toJSONString(payload));
        //用户填单
        JSONObject result = new Qyh(phone).fillForm(phone,JSON.toJSONString(payload));
        log.info(String.valueOf(result));
        String msg = result.getString("message");
        if(!msg.contains("系统异常")){
            //  获取填单ID
            String loanId = toolsMapper.qyh_idByPhone(phone);
            tmkApply tmkApply = new tmkApply(loanId,payload);
            request.close();
            return Result.success(tmkApply);
        }
        return Result.error("订单新增失败");
    }


}

