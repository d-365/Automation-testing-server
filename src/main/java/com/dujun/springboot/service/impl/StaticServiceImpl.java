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
        ApiAutoStatics apiAutoStatics = new ApiAutoStatics();
        apiAutoStatics.setApiCount(staticMapper.apiCount());
        apiAutoStatics.setNewApiCount(staticMapper.newApiCount(dateTools.currentDay(-7)));
        apiAutoStatics.setCaseCount(staticMapper.caseCount());
        apiAutoStatics.setNewCaseCount(staticMapper.newCaseCount(dateTools.currentDay(-7)));
        apiAutoStatics.setPlanCount(staticMapper.planCount());
        apiAutoStatics.setNewPlanCount(staticMapper.newPlanCount(dateTools.currentDay(-7)));
        apiAutoStatics.setClockCount(staticMapper.clockCount());
        apiAutoStatics.setClockExecCount(staticMapper.clockExecCount());

        StaticPlan rate = staticMapper.planRate();
        if (rate.getPlanCount() != 0){
            DecimalFormat df = new DecimalFormat("#.##");
            double success = rate.getSuccess() / rate.getPlanCount();
            success = Double.parseDouble(df.format(success));
            double failed = rate.getFailed() / rate.getPlanCount();
            failed = Double.parseDouble(df.format(failed));
            apiAutoStatics.setSuccessRatio(success*100);
            apiAutoStatics.setFailedRatio(failed*100);
        }

        HomeStatics homeStatics = new HomeStatics();
        homeStatics.setApiAutoStatics(apiAutoStatics);
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
