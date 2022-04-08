package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.WebPage;
import com.dujun.springboot.mapper.WebPageMapper;
import com.dujun.springboot.service.WebPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

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
 * @since 2022-03-28
 */
@Service
public class WebPageServiceImpl extends ServiceImpl<WebPageMapper, WebPage> implements WebPageService {
    @Resource
    private WebPageMapper webPageMapper;

    @Override
    public Result<List<WebPage>> PAGE_LIST() {
        List<WebPage> pageList = webPageMapper.selectList(new QueryWrapper<WebPage>().eq("del_flag",0));
        List<WebPage> parentPage = pageList.stream().filter(item->item.getParentId() == 0).collect(Collectors.toList());
        for (WebPage page : pageList) {
            if (page.getParentId() != 0){
                for (WebPage parent : parentPage) {
                    if (Objects.equals(parent.getId(), page.getParentId())){
                        parent.getChildren().add(page);
                    }
                }
            }
        }
        return Result.success(parentPage);
    }

    @Override
    public Result<?> pageUpdate(WebPage page) {
        // 字符校验
        if (page.getName().length() >10){
            return Result.error("名称需在1~10个字符");
        }else if (Objects.equals(page.getName(), "") || page.getName() == null){
            return Result.error("名称不能为空");
        }
        // 更新删除操作
        if (page.getId()==null){
            webPageMapper.insert(page);
            for (WebPage child : page.getChildren()) {
                if (child.getParentId()==null){
                    child.setParentId(page.getId());
                }
                webPageMapper.insert(child);
            }
        }else {
            webPageMapper.updateById(page);
            for (WebPage child : page.getChildren()) {
                webPageMapper.updateById(child);
            }
        }
        return Result.success();
    }

    @Override
    public Result<?> pageDel(Integer pageId) {
        webPageMapper.deleteById(pageId);
        return Result.success();
    }
}
