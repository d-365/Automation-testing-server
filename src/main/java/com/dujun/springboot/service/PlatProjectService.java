package com.dujun.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PlatProject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 平台项目表 服务类
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
public interface PlatProjectService extends IService<PlatProject> {

    Result<?> updatePrt(PlatProject project);

    Result<IPage<PlatProject>> projectList(String name, Integer size, Integer current);

    Result<?> delPrt(Integer id);

    Result<?> delDomain(Integer id);

    Result<List<PlatProject>> prtList();
}
