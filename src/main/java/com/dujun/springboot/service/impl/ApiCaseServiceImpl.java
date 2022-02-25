package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.ApiCommon;
import com.dujun.springboot.entity.ApiCase;
import com.dujun.springboot.entity.ApiInfo;
import com.dujun.springboot.entity.CaseApiRelation;
import com.dujun.springboot.entity.CaseCategory;
import com.dujun.springboot.mapper.ApiCaseMapper;
import com.dujun.springboot.mapper.ApiInfoMapper;
import com.dujun.springboot.mapper.CaseApiRelationMapper;
import com.dujun.springboot.service.ApiCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2021-12-24
 */
@Service
@Slf4j
public class ApiCaseServiceImpl extends ServiceImpl<ApiCaseMapper, ApiCase> implements ApiCaseService {

    @Resource
    private ApiCaseMapper apiCaseMapper;

    @Resource
    private ApiInfoMapper apiInfoMapper;

    @Resource
    private CaseApiRelationMapper caseApiRelationMapper;

    // 用例详情
    public Result<ApiCase> info(int categoryId){
        ApiCase caseInfo = apiCaseMapper.selectOne(new QueryWrapper<ApiCase>().eq("category_id",categoryId));
//        if(caseInfo.getParams()==null){
//            ArrayList<HashMap> arrayList = new ArrayList<>();
//            caseInfo.setParams(arrayList);
//        }

        return Result.success(caseInfo);
    }

    // 将分类ID转换为用例ID返回前端
    public Result<ArrayList<ApiInfo>> relation(ArrayList<Integer> categoryList){
        ArrayList<ApiInfo> apiInfoList = (ArrayList<ApiInfo>) apiInfoMapper.selectList(new QueryWrapper<ApiInfo>().in("api_suite_id",categoryList));
        return Result.success(apiInfoList);
    }

    // 将分类ID转换为用例ID返回前端
    public Result<ArrayList<ApiInfo>> apiInfoList(ArrayList<Integer> apiIdList){
        ArrayList<ApiInfo> apiInfoList = new ArrayList<>();
        for (int i = 0 ;i<apiIdList.size();i++) {
            ApiInfo apiInfo = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("id",apiIdList.get(i)));
            if(apiInfo!=null){
                apiInfoList.add(apiInfo);
            }
        }
        return Result.success(apiInfoList);
    }


    // 添加修改用例详情
    public Result update_caseInfo(ApiCase caseInfo){
        int id = caseInfo.getId();

        ApiCase apiDetail = apiCaseMapper.selectOne(new QueryWrapper<ApiCase>().eq("id",id));
        if(apiDetail !=null){
            apiCaseMapper.updateById(caseInfo);
        }else {
            apiCaseMapper.insert(caseInfo);
        }
        return Result.success();
    }

    // 用例和接口进行关联
    public Result apiCase_relation(ArrayList<CaseApiRelation> CaseApiRelation){
        for (CaseApiRelation apiCase : CaseApiRelation) {
            Long id = apiCase.getId();
            if (id == null){
                System.out.println("打印下"+apiCase);
                caseApiRelationMapper.insert(apiCase);
            }else {
                caseApiRelationMapper.updateById(apiCase);
            }
        }
        return Result.success();
    }

    // 用例对应的接口列表
    public Result caseApiDetail(int caseId){
        List<CaseApiRelation> caseApi = caseApiRelationMapper.selectList(new QueryWrapper<CaseApiRelation>().eq("case_id",caseId));
        return Result.success(caseApi);
    }

    //删除用例接口关联信息
    public Result deleteCaseApi(int id){
        caseApiRelationMapper.deleteById(id);
        return Result.success();
    }

    // 用例调试
    public Result debugCase(ApiCase apiCase){
        ArrayList<ApiInfo> step = apiCase.getStep();
        for (ApiInfo apiInfo : step) {
            ApiCommon.apiDebug(apiInfo);
        }
        Map<String, String> globalParams =  ApiCommon.getGlobalParams();
        log.info(String.valueOf(globalParams));
        return Result.success(apiCase);
    }

}
