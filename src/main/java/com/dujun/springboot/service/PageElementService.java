package com.dujun.springboot.service;

import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PageElement;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
public interface PageElementService extends IService<PageElement> {

    Result<?> elementList(HashMap<Object,Object> pageElement);

    Result<?> updateEle ( PageElement pageElement);

    Result<?> delEle ( Integer id);

    Result<?> updateAll(List<PageElement> elements);

}
