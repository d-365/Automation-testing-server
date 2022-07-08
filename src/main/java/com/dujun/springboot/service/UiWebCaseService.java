package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.UiWebCase;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2022-04-11
 */
public interface UiWebCaseService extends IService<UiWebCase> {

    Result<?> caseList(Integer caseType);

    // 更新用例
    Result<?> update(UiWebCase uiWebCase);

    // 删除用例
    Result<?> delWebCase( UiWebCase webCase) throws Exception;

    // 复制用例
    Result<?> deepCopy(UiWebCase WebCase);

    // 获取用例步骤
    Result<?> steps(Integer caseId);

    // 删除用例步骤
    Result<?> delCaseStep(Integer stepId);

    // DebugCase
    Result<?> debugCase(Integer caseId,String payload, HttpServletRequest request);

    // seleniumServer下载
    void seleniumServerDownload(HttpServletResponse response);

}
