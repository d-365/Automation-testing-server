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
import com.dujun.springboot.entity.statics.LineChat;
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
        apiAutoStatics.setCaseCount(staticMapper.ApiCaseCount("case_category"));
        apiAutoStatics.setNewCaseCount(staticMapper.newApiCaseCount("case_category",dateTools.currentDay(-7)));
        apiAutoStatics.setPlanCount(staticMapper.planCount(0));
        apiAutoStatics.setNewPlanCount(staticMapper.newPlanCount(dateTools.currentDay(-7),0));
        apiAutoStatics.setClockCount(staticMapper.clockCount(0));
        apiAutoStatics.setClockExecCount(staticMapper.clockExecCount(0));
        // Api计划执行成功率
        StaticPlan rate = staticMapper.planRate(0);
        if (rate.getPlanCount() != 0){
            DecimalFormat df = new DecimalFormat("0.00");
            double failed = rate.getFailed() / rate.getPlanCount();
            double success = 1 - failed;
            failed = Double.parseDouble(df.format(failed*100));
            success = Double.parseDouble(df.format(success*100));
            apiAutoStatics.setSuccessRatio(success);
            apiAutoStatics.setFailedRatio(failed);
        }

        // 获取Web自动化执行数据
        ApiAutoStatics webStatics = new ApiAutoStatics();
        // 用例数
        webStatics.setCaseCount(staticMapper.caseCount("ui_web_case",1));
        // 本周新增用例数
        webStatics.setNewCaseCount(staticMapper.newCaseCount("ui_web_case",dateTools.currentDay(-7),1));
        // 测试计划总数
        webStatics.setPlanCount(staticMapper.planCount(1));
        // 本周新增测试计划数
        webStatics.setNewPlanCount(staticMapper.newPlanCount(dateTools.currentDay(-7),1));
        // 定时计划总数
        webStatics.setClockCount(staticMapper.clockCount(1));
        // 定时计划执行总数
        webStatics.setClockExecCount(staticMapper.clockExecCount(1));

        // 计算Web计划执行成功率数据
        StaticPlan webRate = staticMapper.planRate(1);
        if (webRate.getPlanCount() != 0){
            DecimalFormat df = new DecimalFormat("0.00");
            double failed = webRate.getFailed() / webRate.getPlanCount();
            double success = 1 - failed;
            failed = Double.parseDouble(df.format(failed*100));
            success = Double.parseDouble(df.format(success*100));
            webStatics.setSuccessRatio(success);
            webStatics.setFailedRatio(failed);
        }

        // 数据封装返回
        HomeStatics homeStatics = new HomeStatics();
        homeStatics.setApiAutoStatics(apiAutoStatics);
        homeStatics.setWebAutoStatics(webStatics);
        homeStatics.setAppAutoStatics(appStatic());
        return Result.success(homeStatics);
    }

    /**
     * App首页用例数据统计
     * @return ApiAutoStatics
     */
    public ApiAutoStatics appStatic(){
        // 获取APP自动化执行数据
        ApiAutoStatics appStatics = new ApiAutoStatics();
        // 页面元素数
        appStatics.setApiCount(staticMapper.pageElement(2));
        // 本周新增页面数
        appStatics.setNewApiCount(staticMapper.newPageElement(2,dateTools.currentDay(-7)));
        // 用例数
        appStatics.setCaseCount(staticMapper.caseCount("ui_web_case",2));
        // 本周新增用例数
        appStatics.setNewCaseCount(staticMapper.newCaseCount("ui_web_case",dateTools.currentDay(-7),2));
        // 测试计划总数
        appStatics.setPlanCount(staticMapper.planCount(2));
        // 本周新增测试计划数
        appStatics.setNewPlanCount(staticMapper.newPlanCount(dateTools.currentDay(-7),2));
        // 定时计划总数
        appStatics.setClockCount(staticMapper.clockCount(2));
        // 定时计划执行总数
        appStatics.setClockExecCount(staticMapper.clockExecCount(2));

        // 计算Web计划执行成功率数据
        StaticPlan appRate = staticMapper.planRate(2);
        if (appRate.getPlanCount() != 0){
            DecimalFormat df = new DecimalFormat("0.00");
            double failed = appRate.getFailed() / appRate.getPlanCount();
            double success = 1 - failed;
            failed = Double.parseDouble(df.format(failed*100));
            success = Double.parseDouble(df.format(success*100));
            appStatics.setSuccessRatio(success);
            appStatics.setFailedRatio(failed);
        }

        return appStatics;
    }

    @Override
    public Result<?> line() {
        LineChat lineChat  = new LineChat();
        // Api 折线图
        HashMap<String,DayRunDetail> ApiData = new HashMap<>();
        for (int i = 0; i> -7;i--){
            String today = dateTools.currentDay(i);
            Integer success = staticMapper.planDetailSuccess(today,0);
            if (success ==null ){
                success = 0;
            }
            Integer failed = staticMapper.planDetailFailed(today,0);
            if (failed ==null){
                failed = 0;
            }
            int count = success + failed;
            DayRunDetail dayRunDetail = new DayRunDetail(count,success,failed);
            ApiData.put(today,dayRunDetail);
        }

        // WEB 折线图
        HashMap<String,DayRunDetail> WEBData = new HashMap<>();
        for (int i = 0; i> -7;i--){
            String today = dateTools.currentDay(i);
            Integer success = staticMapper.planDetailSuccess(today,1);
            if (success ==null ){
                success = 0;
            }
            Integer failed = staticMapper.planDetailFailed(today,1);
            if (failed ==null){
                failed = 0;
            }
            int count = success + failed;
            DayRunDetail dayRunDetail = new DayRunDetail(count,success,failed);
            WEBData.put(today,dayRunDetail);
        }

        lineChat.setApiLine(ApiData);
        lineChat.setWebLine(WEBData);
        return Result.success(lineChat);
    }

}
