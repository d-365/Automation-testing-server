/**
 * author     : dujun
 * date       : 2022/2/10 14:29
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.service.impl;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PlanResultDetail;
import com.dujun.springboot.entity.sonEntity.DayRunDetail;
import com.dujun.springboot.entity.sonEntity.StaticPlan;
import com.dujun.springboot.entity.statics.ApiAutoStatics;
import com.dujun.springboot.entity.statics.HomeStatics;
import com.dujun.springboot.mapper.StaticMapper;
import com.dujun.springboot.service.StaticService;
import com.dujun.springboot.tools.dateTools;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.HasMemberTypePattern;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class StaticServiceImpl implements StaticService {

    @Resource
    private StaticMapper staticMapper;


    @Override
    public Result<HomeStatics> home() {
        // 获取api接口自动化执行数据
        ApiAutoStatics apiAutoStatics = new ApiAutoStatics();
        apiAutoStatics.setApiCount(staticMapper.apiCount());
        apiAutoStatics.setNewApiCount(staticMapper.newApiCount(dateTools.currentDay(-7)));
        apiAutoStatics.setCaseCount(staticMapper.caseCount("case_category"));
        apiAutoStatics.setNewCaseCount(staticMapper.newCaseCount("case_category",dateTools.currentDay(-7)));
        apiAutoStatics.setPlanCount(staticMapper.planCount(0));
        apiAutoStatics.setNewPlanCount(staticMapper.newPlanCount(dateTools.currentDay(-7),0));
        apiAutoStatics.setClockCount(staticMapper.clockCount(0));
        apiAutoStatics.setClockExecCount(staticMapper.clockExecCount(0));
        // Api计划执行成功率
        StaticPlan rate = staticMapper.planRate(0);
        if (rate.getPlanCount() != 0){
            DecimalFormat df = new DecimalFormat("0.00");
            double success = rate.getSuccess() / rate.getPlanCount();
            success = Double.parseDouble(df.format(success));
            double failed = rate.getFailed() / rate.getPlanCount();
            failed = Double.parseDouble(df.format(failed));
            BigDecimal f = new BigDecimal(String.valueOf(failed));
            BigDecimal slat = new BigDecimal(String.valueOf(100));
            apiAutoStatics.setSuccessRatio(success*100);
            apiAutoStatics.setFailedRatio(f.multiply(slat).doubleValue());
        }


        // 获取Web自动化执行数据
        ApiAutoStatics webStatics = new ApiAutoStatics();
        // 用例数
        webStatics.setCaseCount(staticMapper.caseCount("ui_web_case"));
        // 本周新增用例数
        webStatics.setNewCaseCount(staticMapper.newCaseCount("ui_web_case",dateTools.currentDay(-7)));
        // 测试计划总数
        webStatics.setPlanCount(staticMapper.planCount(1));
        // 本周新增测试计划数
        webStatics.setNewPlanCount(staticMapper.newPlanCount(dateTools.currentDay(-7),1));
        // 定时计划总数
        webStatics.setClockCount(staticMapper.clockCount(1));
        // 定时计划执行总数
        webStatics.setClockExecCount(staticMapper.clockExecCount(1));

        // 计算Web计划执行成功率数据
        StaticPlan webRate = staticMapper.planRate(0);
        if (webRate.getPlanCount() != 0){
            DecimalFormat df = new DecimalFormat("0.00");
            double success = webRate.getSuccess() / webRate.getPlanCount();
            success = Double.parseDouble(df.format(success));
            double failed = webRate.getFailed() / webRate.getPlanCount();
            failed = Double.parseDouble(df.format(failed));
            BigDecimal f = new BigDecimal(String.valueOf(failed));
            BigDecimal slat = new BigDecimal(String.valueOf(100));
            apiAutoStatics.setSuccessRatio(success*100);
            apiAutoStatics.setFailedRatio(f.multiply(slat).doubleValue());
        }



        // 数据封装返回
        HomeStatics homeStatics = new HomeStatics();
        homeStatics.setApiAutoStatics(apiAutoStatics);
        homeStatics.setWebAutoStatics(webStatics);
        return Result.success(homeStatics);
    }

    @Override
    public Result<?> line() {

        HashMap<String,DayRunDetail> data = new HashMap<>();
        for (int i = 0; i> -7;i--){
            String today = dateTools.currentDay(i);
            int count = staticMapper.planDetailCount(today);
            int success = staticMapper.planDetailSuccess(today);
            int failed = staticMapper.planDetailFailed(today);
            DayRunDetail dayRunDetail = new DayRunDetail(count,success,failed);
            data.put(today,dayRunDetail);
        }
        return Result.success(data);
    }

}
