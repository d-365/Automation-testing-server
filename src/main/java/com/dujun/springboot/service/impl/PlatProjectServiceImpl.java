package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PlatProject;
import com.dujun.springboot.entity.PrtDomain;
import com.dujun.springboot.mapper.PlatProjectMapper;
import com.dujun.springboot.mapper.PrtDomainMapper;
import com.dujun.springboot.service.PlatProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.relational.core.conversion.RelationalEntityUpdateWriter;
import org.springframework.stereotype.Service;
import org.testng.collections.Lists;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 平台项目表 服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
@Service
public class PlatProjectServiceImpl extends ServiceImpl<PlatProjectMapper, PlatProject> implements PlatProjectService {

    @Resource
    private PlatProjectMapper projectMapper;
    @Resource
    private PrtDomainMapper domainMapper;

    @Override
    public Result<?> updatePrt(PlatProject project) {
        if (project.getPrtName() == null || "".equals(project.getPrtName())){
            return Result.error("项目名不能为空");
        }
        if (project.getId() == null){
            projectMapper.insert(project);
        }else {
            projectMapper.updateById(project);
        }
        return Result.success();
    }

    @Override
    public Result<IPage<PlatProject>> projectList(String name, Integer size, Integer current) {
        IPage<PlatProject> elementPage = new Page<>(current, size);
        LambdaQueryWrapper<PlatProject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PlatProject::getDelFlag,0).orderByDesc(PlatProject::getCreateTime);
        if (name!=null){
            wrapper.likeRight(PlatProject::getPrtName,name);
        }
        IPage<PlatProject> result = projectMapper.selectPage(elementPage,wrapper);
        for (PlatProject project : result.getRecords()) {
            LambdaQueryWrapper<PrtDomain> domainQuery = new LambdaQueryWrapper<>();
            domainQuery.eq(PrtDomain::getPrtId,project.getId()).eq(PrtDomain::getDelFlag,0);
            List<PrtDomain> domain = domainMapper.selectList(domainQuery);
            project.setDomains(domain);
        }
        return Result.success(result);
    }

    @Override
    public Result<?> delPrt(Integer id) {
        if (id !=null){
            projectMapper.deleteById(id);
        }
        return Result.success();
    }

    @Override
    public Result<?> delDomain(Integer id) {
        domainMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<List<PlatProject>> prtList() {
        LambdaQueryWrapper<PlatProject> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlatProject::getDelFlag,0);
        return Result.success(projectMapper.selectList(queryWrapper));
    }

    public Result<?> updateDomain(List<PrtDomain> domains) {
        List<Integer> envIds = new ArrayList<>();
        for (PrtDomain domain : domains) {
            envIds.add(domain.getEnvId());
        }
        if (envIds.size() != new HashSet<>(envIds).size()){
            return Result.error("环境信息不能重复！");
        }

        for (PrtDomain domain : domains) {
            Result<?> result = domainHelp(domain);
            if (result.getCode().equals("1")){
                return result;
            }
        }
        return Result.success();
    }

    public Result<?> domainHelp(PrtDomain domain){
        if (domain.getPrtId()==null){
            return Result.error("项目ID不能为空");
        }
        if (domain.getDomain()==null || domain.getDomain().equals("")){
            return Result.error("域名信息不能为空");
        }
        if (domain.getEnvId()==null){
            return Result.error("环境信息不能为空");
        }
        if (domain.getId()==null){
            domainMapper.insert(domain);
        }else {
            domainMapper.updateById(domain);
        }
        return Result.success();
    }


}
