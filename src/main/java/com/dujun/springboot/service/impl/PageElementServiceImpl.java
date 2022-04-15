package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PageElement;
import com.dujun.springboot.mapper.PageElementMapper;
import com.dujun.springboot.service.PageElementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
@Service
public class PageElementServiceImpl extends ServiceImpl<PageElementMapper, PageElement> implements PageElementService {

    @Resource
    private PageElementMapper pageElementMapper;

    @Override
    public Result<?> elementList(HashMap<Object,Object> pageElement) {
        // 解析参数
        Integer pageId = (Integer) pageElement.get("pageId");
        // current
        long current = Long.parseLong(pageElement.getOrDefault("current",1).toString());
        long size = Long.parseLong(pageElement.getOrDefault("size",10).toString());
        IPage<PageElement> elementPage = new Page<>(current, size);
        QueryWrapper<PageElement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("page_id",pageId);
        IPage<PageElement> pageElements = pageElementMapper.selectPage(elementPage,queryWrapper);
        return Result.success(pageElements);
    }

    public void update_help(PageElement pageElement){
        if (pageElement.getId() == null){
            pageElementMapper.insert(pageElement);
        }else {
            pageElementMapper.updateById(pageElement);
        }
    }

    @Override
    public Result<?> updateEle(PageElement pageElement) {
        if (Objects.equals(pageElement.getLocationValue(), "")){
            return Result.error("locationValue不能为空");
        }
        update_help(pageElement);
        return Result.success();
    }

    @Override
    public Result<?> delEle(Integer id) {
        pageElementMapper.deleteById(id);
        return Result.success();
    }

    /**
     * 遍历更新所有的element元素
     * @param elements elements
     */
    @Override
    public Result<?> updateAll(List<PageElement> elements) {
        for (PageElement element : elements) {
            if (Objects.equals(element.getLocationValue(), "")){
                return Result.error("locationValue不能为空");
            }
            update_help(element);
        }
        return Result.success();
    }


}
