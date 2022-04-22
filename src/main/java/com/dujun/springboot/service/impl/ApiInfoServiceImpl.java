package com.dujun.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.DebugApi;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.ApiCommon;
import com.dujun.springboot.entity.ApiInfo;
import com.dujun.springboot.entity.CategoryApi;
import com.dujun.springboot.entity.RspAsserts;
import com.dujun.springboot.entity.RspExtract;
import com.dujun.springboot.entity.sonEntity.ApiExec;
import com.dujun.springboot.mapper.ApiInfoMapper;
import com.dujun.springboot.mapper.CategoryApiMapper;
import com.dujun.springboot.service.ApiInfoService;
import com.dujun.springboot.utils.request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

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

    //接口详情(分类ID)
    public Result<ApiInfo> detail(int suiteId){
        ApiInfo apiInfo = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("api_suite_id",suiteId));
        ArrayList<ApiExec> beforeExec = apiInfo.getBeforeExec();

        // 增加默认值（reqAssert/reqExtract）
        ArrayList arrayList = new ArrayList<>();
        HashMap<String,String> map = new HashMap<String, String>();
        arrayList.add(map);
        if(apiInfo.getReqAssert() == null){
            apiInfo.setReqAssert(arrayList);
        }else if (apiInfo.getReqExtract() ==null){
            apiInfo.setReqExtract(arrayList);
        }
        return Result.success(apiInfo);
    }

    //接口详情（接口ID）
    public Result<ApiInfo> info(int id){
        ApiInfo apiInfo = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("id",id));
//        ArrayList<ApiExec> beforeExec = apiInfo.getBeforeExec();
//        // 增加默认值（reqAssert/reqExtract）
//        ArrayList arrayList = new ArrayList<>();
//        HashMap<String,String> map = new HashMap<String, String>();
//        arrayList.add(map);
//        if(apiInfo.getReqAssert() == null){
//            apiInfo.setReqAssert(arrayList);
//        }else if (apiInfo.getReqExtract() ==null){
//            apiInfo.setReqExtract(arrayList);
//        }
        return Result.success(apiInfo);
    }

    // 保存接口信息
    public Result saveApi(ApiInfo apiInfo){

        Long apiID = apiInfo.getApiSuiteId();
        ApiInfo result = apiInfoMapper.selectOne(new QueryWrapper<ApiInfo>().eq("api_suite_id",apiID));
        ArrayList<ApiExec> apiExecs = apiInfo.getBeforeExec();
        for (ApiExec apiExec : apiExecs) {
            String s = apiExec.getDbConfig();
        }
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
    public  Result delete(int id){
        apiInfoMapper.delete(new QueryWrapper<ApiInfo>().eq("api_suite_id",id));
        return Result.success();
    }

    //执行单个接口（debug）
    @Override
    public Result debug(ApiInfo apiInfo) {
       return ApiCommon.apiDebug(apiInfo);
    }

}

//测试类
class test{
    public static void main(String[] args) {
    }
}