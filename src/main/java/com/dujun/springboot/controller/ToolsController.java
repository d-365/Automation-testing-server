/*
  author     : dujun
  date       : 2021/12/21 11:45
  description: 告诉大家我是干啥的
 */

package com.dujun.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.tools.TmkApply;
import com.dujun.springboot.service.ToolsService;
import com.dujun.springboot.tools.RandomValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tools")
public class ToolsController {


    @Autowired
    private ToolsService toolsService;


    // 电销填单
    @PostMapping("/tmkApply")
    public Result<?> tmkApply(@RequestBody JSONObject tmkApplyData) {
        String phone = tmkApplyData.getString("phone");
        String city = tmkApplyData.getString("city");
        return toolsService.tmkApply(phone, city);
    }

    // 电销循环填单
    @PostMapping("/tmkApplyLoop")
    public Result<?> tmkApplyLoop(@RequestBody TmkApply tmkApplyData) {
        return toolsService.tmkApplyLoop(tmkApplyData);
    }

    @PostMapping("/tmkRandom")
    public Result<?> tmkApplyRandom(@RequestBody TmkApply tmkApplyData) {
        return toolsService.tmkApplyRandom(tmkApplyData);
    }

    /**
     * 轻易花填单
     * @param apply 填单数据--手机号--城市
     * @return Result
     */
    @PostMapping("/qyhApply")
    public Result<?> qyhApply(@RequestBody TmkApply apply) {
        String phone = apply.getPhone() != null ? apply.getPhone() : RandomValue.getTel();
        String city = apply.getCity() != null ? apply.getCity() : RandomValue.getAddress();
        return toolsService.qyhApply(phone, city);
    }

    /**
     * 轻易花填单开始
     * @param apply 填单数据--手机号--城市
     * @return Result
     */
    @PostMapping("/qyhApply/start")
    public Result<?> qyhApplyStart(@RequestBody TmkApply apply) {
        String phone = apply.getPhone() != null ? apply.getPhone() : RandomValue.getTel();
        String city = apply.getCity() != null ? apply.getCity() : RandomValue.getAddress();
        int loop = apply.getLoop();
        return toolsService.qyhApplyStart(phone, city, loop);
    }

    /**
     * 轻易花填单结束
     * @return Result
     */
    @PostMapping("/qyhApply/end")
    public Result<?> qyhApplyStart() {
        return toolsService.qyhApplyEnd();
    }

    /**
     * 验证码 查询
     * @return Result
     */
    @PostMapping("/sms/code/query")
    public Result<?> codeQuery(@RequestBody JSONObject payload){
        String phone = payload.getString("phone");
        String type = payload.getString("type");
        return toolsService.codeQuery(phone,type);
    }

    /**
     * 验证码 修改
     * @return Result
     */
    @PostMapping("/sms/code/update")
    public Result<?> codeUpdate(@RequestBody JSONObject payload){
        String phone = payload.getString("phone");
        String type = payload.getString("type");
        String code = payload.getString("smsCode");
        return toolsService.codeUpdate(phone,code,type);
    }

}
