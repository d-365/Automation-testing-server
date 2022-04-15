package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.entity.WebPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2022-03-28
 */
public interface WebPageService extends IService<WebPage> {

    /**
     *
     * @return WebPage
     */
    Result<List<WebPage>> PAGE_LIST();

    Result<?> pageUpdate(WebPage Page);

    Result<?> pageDel(Integer pageId);

    // 页面元素级联选择器
    Result<?> webPageElement();

    // 更新用例步骤
    Result<?> upCaseStep(List<WebCaseStep> caseSteps);


}
