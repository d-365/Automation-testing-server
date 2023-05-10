package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.ApiCase;
import com.dujun.springboot.entity.CaseApiRelation;
import com.dujun.springboot.entity.CaseCategory;
import com.dujun.springboot.mapper.ApiCaseMapper;
import com.dujun.springboot.mapper.CaseApiRelationMapper;
import com.dujun.springboot.mapper.CaseCategoryMapper;
import com.dujun.springboot.service.CaseCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2021-12-22
 */
@Service
@EnableTransactionManagement
public class CaseCategoryServiceImpl extends ServiceImpl<CaseCategoryMapper, CaseCategory> implements CaseCategoryService {

    @Resource
    private CaseCategoryMapper caseCategoryMapper;

    @Resource
    private ApiCaseMapper apiCaseMapper;

    @Resource
    private CaseApiRelationMapper caseApiRelationMapper;

    // 新增修改用例分类
    public Result<?> update_category(CaseCategory caseCategory) {
        Long id = caseCategory.getId();
        ApiCase apiCase = new ApiCase();
        if (id == null) {
            caseCategoryMapper.insert(caseCategory);
            if (caseCategory.getType() == 1) {
                apiCase.setCategoryId(caseCategory.getId());
                apiCaseMapper.insert(apiCase);
            }
        } else {
            caseCategoryMapper.updateById(caseCategory);
        }
        return Result.success();
    }

    // 用例分类列表
    public Result<List<CaseCategory>> case_categoryList(){
        //查找数据库所有接口分类数据
        List<CaseCategory> caseCategoryList = caseCategoryMapper.selectList(null);
        //声明父类容器
        List<CaseCategory> parentsList = new ArrayList<>();
        //声明返回集合
        List<CaseCategory> resultList = new ArrayList<>();

        for(CaseCategory caseCategory : caseCategoryList){
            if (caseCategory.getParentId() == 0){
                parentsList.add(caseCategory);
                resultList.add(caseCategory);
            }else {
                //对于不是第一梯队的数据进行遍历
                for(CaseCategory parent: parentsList){
                    if(parent.getId() ==  caseCategory.getParentId().intValue() ){
                        parent.getChildren().add(caseCategory);
                        parentsList.add(caseCategory);
                        break;
                    }
                }
            }
        }

        return Result.success(resultList);
    }


    /**
     * 删除用例分类
     *
     * @param id 分类ID
     */
    @Transactional
    public Result<?> delete(int id) {
        // 获取用例ID
        ApiCase apiCase = apiCaseMapper.selectOne(new QueryWrapper<ApiCase>().eq("category_id", id));
        if (apiCase != null) {
            int caseId = apiCase.getId();
            // 删除用例接口关联表
            caseApiRelationMapper.delete(new QueryWrapper<CaseApiRelation>().eq("case_id", caseId));
            // 删除用例表
            apiCaseMapper.deleteById(caseId);
        }
        // 删除分类数据
        caseCategoryMapper.deleteById(id);
        return Result.success();
    }

}
