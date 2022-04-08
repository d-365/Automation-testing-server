package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.WebPage;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
