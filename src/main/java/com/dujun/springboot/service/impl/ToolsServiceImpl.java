/*
  author     : dujun
  date       : 2021/12/21 12:04
  description: 告诉大家我是干啥的
 */

package com.dujun.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.dujun.springboot.Api.qyh.Qyh;
import com.dujun.springboot.Api.tmk.Tmk;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.VO.tools.SmsCode;
import com.dujun.springboot.data.ApiOrderData;
import com.dujun.springboot.entity.sonEntity.tmkApply;
import com.dujun.springboot.entity.tools.TmkApply;
import com.dujun.springboot.mapper.ToolsMapper;
import com.dujun.springboot.service.ToolsService;
import com.dujun.springboot.tools.RandomValue;
import com.dujun.springboot.utils.MysqlTools;
import lombok.extern.slf4j.Slf4j;
import org.quartz.impl.matchers.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/*
工具类
 */

@DS("qsTest")
@Service
@Slf4j
public class ToolsServiceImpl implements ToolsService {

    @Resource
    private ToolsMapper toolsMapper;

    volatile private boolean flag;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static MysqlTools mysqlConnection() {
        return new MysqlTools("jdbc:mysql://118.31.184.240:3306", "root", "3wHNY2Bq");
    }

    /**
     * 删除用户订单 + 注册信息
     */
    public static void deleteUserOrder(String phone) {
        String delOrder = String.format("DELETE FROM jgq.think_loan WHERE phone = %s", phone);
        String delUser = String.format("DELETE FROM jgq.think_user WHERE phone = %s",phone);
        MysqlTools mysqlTools = ToolsServiceImpl.mysqlConnection();
        mysqlTools.execute(delOrder);
        mysqlTools.execute(delUser);
        mysqlTools.close();
    }

    /**
     * 电销填单
     *
     * @param phone 手机号
     * @param city  城市
     */
    @DS("qsTest")
    public Result<?> tmkApply(String phone, String city) {
        MysqlTools mysqlTools = ToolsServiceImpl.mysqlConnection();
        if (phone == null || Objects.equals(phone, "")) {
            phone = RandomValue.getTel();
        }
        if (city == null || Objects.equals(city, "")) {
            city = RandomValue.getAddress();
        }
        ToolsServiceImpl.deleteUserOrder(phone);
        //用户填单
        HashMap<String, Object> applyData = ApiOrderData.tmk_data(phone, city);
        try {
            JSONObject result = new Tmk().apply(applyData);
            String msg = result.getString("msg");
            if (!msg.contains("系统异常")) {
                //  获取填单ID
                String loanId = toolsMapper.id_byPhone(phone);
                tmkApply tmkApply = new tmkApply();
                tmkApply.setLoanId(loanId);
                tmkApply.setPayload(applyData);
                mysqlTools.close();
                return Result.success(tmkApply);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("电销开放平台系统异常");
        }
        return Result.error("订单新增失败");
    }

    //电销填单（循环）
    public Result<?> tmkApplyLoop(TmkApply tmkApplyData) {
        MysqlTools mysqlTools = ToolsServiceImpl.mysqlConnection();
        String phone = tmkApplyData.getPhone();
        String city = tmkApplyData.getCity();
        int loop = tmkApplyData.getLoop();
        int status = tmkApplyData.getStatus();
        Long step = tmkApplyData.getStep();
        ArrayList<String> orderList = new ArrayList<>();
        if (status == 0) {
            for (int i = 0; i < loop; i++) {
                if (phone == null ){
                    phone = RandomValue.getTel();
                }
                if(city == null){
                    city = RandomValue.getAddress();
                }
                ToolsServiceImpl.deleteUserOrder(phone);
                //用户填单
                HashMap<String, Object> applyData = ApiOrderData.tmk_data(phone, city);
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
    public Result<?> tmkApplyRandom(TmkApply tmkApplyData) {
        MysqlTools mysqlTools = ToolsServiceImpl.mysqlConnection();
        int loop = tmkApplyData.getLoop();
        int status = tmkApplyData.getStatus();
        Long step = tmkApplyData.getStep();
        ArrayList<String> orderList = new ArrayList<>();
        if (status == 0) {
            for (int i = 0; i < loop; i++) {
                String phone = RandomValue.getTel();
                String city = RandomValue.getAddress();

                ToolsServiceImpl.deleteUserOrder(phone);
                //用户填单
                HashMap<String, Object> applyData = ApiOrderData.tmk_data(phone, city);
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
    public Result<?> qyhApply(String phone, String city) {
        //填单数据
        HashMap<String, Object> payload = ApiOrderData.qyh_applyData(city);
        //用户填单
        JSONObject result = new Qyh(phone).fillForm(JSON.toJSONString(payload));
        log.info(String.valueOf(result));
        String msg = result.getString("message");
        if (!msg.contains("系统异常")) {
            //  获取填单ID
            String loanId = toolsMapper.qyh_idByPhone(phone);
            tmkApply tmkApply = new tmkApply(loanId, payload);
            return Result.success(tmkApply);
        }
        return Result.error("订单新增失败");
    }

    @Override
    public Result<?> qyhApplyStart(String phone, String city, int loop) {
        flag = true;
        //填单数据
        HashMap<String, Object> payload = ApiOrderData.qyh_applyData(city);
        // 订单数据
        List<String> orderList = new ArrayList<>();
        for (int i = 0; i < loop; i++) {
            System.out.println(i);
            if (flag) {
                try {
                    //用户填单
                    phone = RandomValue.getTel();
                    JSONObject result = new Qyh(phone).fillForm(JSON.toJSONString(payload));
                    String msg = result.getString("message");
                    if (!msg.contains("系统异常") && (i == 0 || i == loop - 1)) {
                        //  获取填单ID
                        String loanId = toolsMapper.qyh_idByPhone(phone);
                        orderList.add(loanId);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            } else {
                String loanId = toolsMapper.qyh_idByPhone(phone);
                orderList.add(loanId);
                break;
            }
        }
        return Result.success(orderList);
    }

    @Override
    public Result<?> qyhApplyEnd() {
        flag = false;
        return Result.success();
    }

    @Override
    public Result<?> codeQuery(String phone, String type) {
        if (phone ==null || phone.equals("")){
            return Result.error("手机号不能为空");
        }
        if (type == null || type.equals("")){
            return Result.error("type不能为空");
        }
        ValueOperations<String, String> forValue = redisTemplate.opsForValue();
        // 查询新平台后台验证码
        if (type.equals( "0")){
            String CRM = codeReplace(forValue.get(String.format("crm_%1$s", phone)));
            String QS = codeReplace(forValue.get(String.format("admin_%1$s", phone)));;
            String DRK  = codeReplace(forValue.get(String.format("drk_%1$s", phone)));
            SmsCode smsCode = new SmsCode(CRM,QS,DRK);
            if (CRM == null && QS == null && DRK == null){
                return Result.error("先获取验证码或重置后查看");
            }
            return Result.success(smsCode);
        }
        return Result.success();
    }

    @Override
    public Result<?> codeUpdate(String phone, String code, String type) {
        if (phone ==null || phone.equals("")){
            return Result.error("手机号不能为空");
        }
        if (type == null || type.equals("")){
            return Result.error("type不能为空");
        }
        if (code == null || code.equals("")){
            return Result.error("验证码不能为空");
        }
        ValueOperations<String, String> forValue = redisTemplate.opsForValue();
        // 轻易花后台项目
        if (type.equals("0")){
            String crm_key = String.format("crm_%1$s", phone);
            String drk_key = String.format("drk_%1$s", phone);
            String qs_key = String.format("admin_%1$s", phone);
            String CRM = forValue.get(crm_key);
            String DRK = forValue.get(drk_key);
            String QS = forValue.get(qs_key);
            if (CRM == null){
                forValue.set(crm_key,code);
            }else {
                forValue.getAndSet(crm_key,code);
            }
            if (DRK == null){
                forValue.set(drk_key,code);
            }else {
                forValue.getAndSet(drk_key,code);
            }
            if (QS == null){
                forValue.set(qs_key,code);
            }else {
                forValue.getAndSet(qs_key,code);
            }
        }
        return Result.success();
    }

    /**
     * 祛除 验证码中的双引号
     * @param code String
     * @return 处理后验证码
     */
    public String codeReplace(String code){
        if (code !=null){
            return code.replace("\"","");
        }
        return null;
    }

}

