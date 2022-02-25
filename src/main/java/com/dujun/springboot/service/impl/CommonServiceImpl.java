/*
  author     : dujun
  date       : 2021/12/13 17:54
  description: 通用的service方法
 */

package com.dujun.springboot.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.RunPlan;
import com.dujun.springboot.entity.sonEntity.EnvDataBase;
import com.dujun.springboot.mapper.RunPlanMapper;
import com.dujun.springboot.service.CommonService;
import com.dujun.springboot.utils.MysqlTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class CommonServiceImpl  implements CommonService {

    @Resource
    private RunPlanMapper runPlanMapper;

    //数据库连接测试
    public Result<?> dbDebug(EnvDataBase envDataBase){
        String jdbcUrl = envDataBase.getJdbcUrl();
        String dbName = envDataBase.getDbName();
        String userName = envDataBase.getDbUserName();
        String password = envDataBase.getDbPwd();
        MysqlTools mysqlTools = new MysqlTools(jdbcUrl,userName,password);
        if(mysqlTools.result){
            return Result.success("连接成功");
        }else {
            return Result.error(mysqlTools.msg);
        }

    }

    // 获取需要执行的定时任务
    public List<RunPlan> getPlanList(){
        QueryWrapper<RunPlan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status","1");
        queryWrapper.eq("is_clock","1");
        List<RunPlan> planList = runPlanMapper.selectList(queryWrapper);
        System.out.println(planList);
        return planList;
    }

}
