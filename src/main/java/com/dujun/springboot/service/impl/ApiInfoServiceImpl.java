package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.ApiCommon;
import com.dujun.springboot.entity.ApiInfo;
import com.dujun.springboot.entity.CategoryApi;
import com.dujun.springboot.entity.PlanRound;
import com.dujun.springboot.mapper.ApiInfoMapper;
import com.dujun.springboot.mapper.CategoryApiMapper;
import com.dujun.springboot.mapper.PlanRoundMapper;
import com.dujun.springboot.service.ApiInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 接口详情 服务实现类
 * </p>
 *
 * @author dujun
 * @since 2021-11-29
 */
@Service
public class ApiInfoServiceImpl extends ServiceImpl<ApiInfoMapper, ApiInfo> implements ApiInfoService {

    @Resource
    private ApiInfoMapper apiInfoMapper;

    @Resource
    private CategoryApiMapper categoryApiMapper;

    @Resource
    private PlanRoundMapper planRoundMapper;

    //接口详情(分类ID)
    public Result<ApiInfo> detail(int suiteId){
        ApiInfo apiInfo = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("api_suite_id",suiteId));
        // 增加默认值（reqAssert/reqExtract）
        ArrayList arrayList = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        arrayList.add(map);
        if(apiInfo.getReqAssert() == null){
            apiInfo.setReqAssert(arrayList);
        }else if (apiInfo.getReqExtract() ==null){
            apiInfo.setReqExtract(arrayList);
        }
        ApiCommon.setUpTearDownDispose(apiInfo);
        return Result.success(apiInfo);
    }

    //接口详情（接口ID）
    public Result<ApiInfo> info(int id){
        ApiInfo apiInfo = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("id",id));
        ApiCommon.setUpTearDownDispose(apiInfo);
        return Result.success(apiInfo);
    }

    // 保存接口信息
    public Result<?> saveApi(ApiInfo apiInfo){

        Long apiID = apiInfo.getApiSuiteId();

        // 处理前置信息
        List<PlanRound> setUp = apiInfo.getBeforeExec();
        if (setUp.size()>0){
            List<Integer> setUpIds = new ArrayList<>();
            for (PlanRound planRound : setUp) {
                if (planRound != null){
                    planRound.setType(0);
                    setUpIds.add(updatePlanRound(planRound));
                }
            }
            apiInfo.setSetUpIds(setUpIds);
        }

        // 处理后置信息
        List<PlanRound> tearDown = apiInfo.getAfterExec();
        if (tearDown.size()>0){
            List<Integer> tearDownIds = new ArrayList<>();
            for (PlanRound planRound : tearDown) {
                if (planRound!=null){
                    planRound.setType(1);
                    tearDownIds.add(updatePlanRound(planRound));
                }
            }
            apiInfo.setTearDownIds(tearDownIds);
        }

        ApiInfo result = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("api_suite_id",apiID));
        if(result == null){
            //增加
            apiInfoMapper.insert(apiInfo);
        }else {
            //修改接口详情
            apiInfoMapper.updateById(apiInfo);
            // 修改分类数据
            CategoryApi categoryApi = new CategoryApi();
            Long suitId = apiInfo.getApiSuiteId();
            String apiName = apiInfo.getName();
            String apiMethod = apiInfo.getMethod();
            categoryApi.setId(suitId);
            categoryApi.setName(apiName);
            categoryApi.setMethod(apiMethod);
            categoryApiMapper.updateById(categoryApi);
        }

        return Result.success();
    }

    //删除接口详情
    public  Result<?> delete(int id){
        apiInfoMapper.delete(new QueryWrapper<ApiInfo>().eq("api_suite_id",id));
        return Result.success();
    }

    //执行单个接口（debug）
    @Override
    public Result<?> debug(Integer envId,ApiInfo apiInfo) {
       return ApiCommon.apiDebug(envId,apiInfo);
    }

    public Integer updatePlanRound(PlanRound planRound){
        if (planRound.getActionId()==null|| planRound.getOperateData()==null){
            return null;
        }
        if (planRound.getId()==null){
            planRoundMapper.insert(planRound);
        }else {
            planRoundMapper.updateById(planRound);
        }
        return planRound.getId();
     }

}
