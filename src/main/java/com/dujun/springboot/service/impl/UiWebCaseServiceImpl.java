package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.UiWebCase;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.mapper.UiWebCaseMapper;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import com.dujun.springboot.service.UiWebCaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.collections.Lists;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-04-11
 */
@Service
public class UiWebCaseServiceImpl extends ServiceImpl<UiWebCaseMapper, UiWebCase> implements UiWebCaseService {

    @Resource
    private UiWebCaseMapper webCaseMapper;

    @Resource
    private WebCaseStepMapper webCaseStepMapper;

    @Override
    public Result<?> caseList() {
        QueryWrapper<UiWebCase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag",0);
        // 查询所有的webCase
        List<UiWebCase> AllWebCases = webCaseMapper.selectList(queryWrapper);
        // 查找出所有一级菜单
        List<UiWebCase> parentWebCase = AllWebCases.stream().filter(item->item.getParentId()==0).collect(Collectors.toList());
        for (UiWebCase parentCase : parentWebCase) {
            parentCase.setChildren(getChildren(parentCase.getId(),AllWebCases));
        }

        return Result.success(parentWebCase);
    }

    @Override
    public Result<?> update(UiWebCase uiWebCase) {
        if (uiWebCase.getId() == null){
            webCaseMapper.insert(uiWebCase);
        }else {
            webCaseMapper.updateById(uiWebCase);
        }
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> delWebCase(UiWebCase webCase) throws Exception{
        List<UiWebCase> webCases = new ArrayList<UiWebCase>(){{
            add(webCase);
        }};
        // 查询对应的 case
        delCaseDeep(webCases);
        return Result.success();
    }

    @Override
    public Result<?> deepCopy(UiWebCase webCase) {
        webCase.setId(null);
        webCaseMapper.insert(webCase);
        deepCopyHelp(webCase.getChildren(),webCase.getId());
        return Result.success();
    }

    // 获取用例步骤列表
    @Override
    public Result<?> steps(Integer caseId) {
        QueryWrapper<WebCaseStep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("case_id",caseId);
        queryWrapper.orderByAsc("sort");
        List<WebCaseStep> webCaseSteps = webCaseStepMapper.selectList(queryWrapper);
        return Result.success(webCaseSteps);
    }

    /**
     * 递归复制
     */
    private void deepCopyHelp(List<UiWebCase> webCasesList,Integer parentId){
        if (webCasesList.size() != 0){
            for (UiWebCase webCase : webCasesList) {
                webCase.setId(null);
                webCase.setParentId(parentId);
                webCaseMapper.insert(webCase);
                deepCopyHelp(webCase.getChildren(),webCase.getId());
            }
        }

    }

    /**
     * 递归删除
     */
    private void delCaseDeep(List<UiWebCase> webCase){
        for (UiWebCase MyWebCase : webCase) {
            if (MyWebCase.getChildren() !=null){
                if (MyWebCase.getChildren().size()!=0){
                    delCaseDeep(MyWebCase.getChildren());
                }
            }
            webCaseMapper.deleteById(MyWebCase);
        }
    }

    /**
     * 递归遍历获取子元素
     * @param id 父类ID
     * @param allWebCases 要遍历所有元素
     * @return 子元素列表
     */
    private List<UiWebCase> getChildren(Integer id, List<UiWebCase> allWebCases) {
        List<UiWebCase> childrenWebCases = Lists.newArrayList();
        // 遍历所有节点，将父菜单id与传过来的id比较
        for (UiWebCase allWebCase : allWebCases) {
            if (Objects.equals(id, allWebCase.getParentId())){
                childrenWebCases.add(allWebCase);
            }
        }
        
        // 遍历子集
        for (UiWebCase childrenWebCase : childrenWebCases) {
            childrenWebCase.setChildren(getChildren(childrenWebCase.getId(),allWebCases));
        }

        // 递归退出条件
        if (childrenWebCases.size() == 0) {
            return null;
        }

        return childrenWebCases;
    }

    @Override
    public Result<?> delCaseStep(Integer stepId) {
        webCaseStepMapper.deleteById(stepId);
        return Result.success();
    }

}
