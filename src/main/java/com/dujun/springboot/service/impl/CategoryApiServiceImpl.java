package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.ApiInfo;
import com.dujun.springboot.entity.CategoryApi;
import com.dujun.springboot.mapper.ApiInfoMapper;
import com.dujun.springboot.mapper.CategoryApiMapper;
import com.dujun.springboot.service.CategoryApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 接口分类列表 服务实现类
 * </p>
 *
 * @author dujun
 * @since 2021-11-25
 */
@Service
public class CategoryApiServiceImpl extends ServiceImpl<CategoryApiMapper, CategoryApi> implements CategoryApiService {

    @Resource
    private CategoryApiMapper categoryApiMapper;
    @Resource
    private ApiInfoMapper apiInfoMapper;

    // 接口分类列表
    public Result<List<CategoryApi>> List(){
        //存储从数据库中获取的数据
        List<CategoryApi> categoryApiList = categoryApiMapper.selectList(null);
        //声明父类容器
        List<CategoryApi> parentsList = new ArrayList<>();
        //声明返回集合
        List<CategoryApi> resultList = new ArrayList<>();
        for(CategoryApi category : categoryApiList){
            if (category.getParentId() == 0){
                parentsList.add(category);
                resultList.add(category);
            }else {
                //对于不是第一梯队的数据进行遍历
                for(CategoryApi parent: parentsList){
                    if(parent.getId() ==  category.getParentId().intValue() ){
                        parent.getChildren().add(category);
                        parentsList.add(category);
                        break;
                    }
                }
            }
        }
        return Result.success(resultList);
    }

    //新增分类
    public Result add(CategoryApi categoryApi){
        categoryApiMapper.insert(categoryApi);
        //分类表
        int categoryType = categoryApi.getSourceType();
        //如果是接口就在接口详情插入一条数据
        if(categoryType ==1 ){
            ApiInfo apiInfo = new ApiInfo();
            Long suitId = categoryApi.getId();
            apiInfo.setApiSuiteId(suitId);
            apiInfoMapper.insert(apiInfo);
        }
        return Result.success(categoryApi);
    }

    //删除分类
    public  Result delete(int id){

        apiInfoMapper.deleteBy_api_suite_id(id);
        //查找改分类下是否存在关联接口
        CategoryApi categoryApi = categoryApiMapper.selectOne(new QueryWrapper<CategoryApi>().eq("parent_id",id));
        while (categoryApi!=null){
            // 分类对应的ID
            Long apiId = categoryApi.getId();
            //删除接口
            List<ApiInfo>  api = apiInfoMapper.selectList(new QueryWrapper<ApiInfo>().eq("api_suite_id",apiId));
            if(api!=null){
                for (ApiInfo apiInfo : api) {
                    apiInfoMapper.deleteById(apiInfo.getId());
                }
            }
            //删除分类
            categoryApi = categoryApiMapper.selectOne(new QueryWrapper<CategoryApi>().eq("parent_id",apiId));
            categoryApiMapper.deleteById(apiId);

        }
        categoryApiMapper.deleteById(id);
        return Result.success();
    }

    //修改分类名称
    public  Result update(CategoryApi categoryApi){
        categoryApiMapper.updateById(categoryApi);
        return Result.success();
    }

    //分类详情
    public Result<CategoryApi> suitDetail(int suitId){
        CategoryApi suit = categoryApiMapper.selectOne(new QueryWrapper<CategoryApi>().eq("id",suitId));
        return Result.success(suit);
    }

}
