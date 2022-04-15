package com.dujun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PageElement;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.entity.WebPage;
import com.dujun.springboot.entity.sonEntity.WebPageElement;
import com.dujun.springboot.mapper.PageElementMapper;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import com.dujun.springboot.mapper.WebPageMapper;
import com.dujun.springboot.service.WebPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.testng.collections.Lists;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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

    @Resource
    private PageElementMapper pageElementMapper;

    @Resource
    private WebCaseStepMapper caseStepMapper;


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

    @Override
    public Result<?> webPageElement() {
        // 1 递归获取所有的web-page数据--层级结构
        List<WebPage> webPageRaw = webPageMapper.selectList(new QueryWrapper<WebPage>().eq("del_flag", 0));
        List<WebPageElement> webPages = new ArrayList<>();
        // 数据转换
        for (WebPage webPage : webPageRaw) {
            WebPageElement webPageElement = new WebPageElement();
            webPageElement.setId(webPage.getId());
            webPageElement.setParentId(webPage.getParentId());
            webPageElement.setName(webPage.getName());
            webPages.add(webPageElement);
        }
        // 数据封装
        List<WebPageElement> topWebPages =  webPages.stream().filter(item->item.getParentId() ==0).collect(Collectors.toList());
        for (WebPageElement topWebPage : topWebPages) {
            topWebPage.setChildren(webPageElementDeep(topWebPage.getId(),webPages));
        }
        return Result.success(topWebPages);
    }

    // 更新用例步骤
    @Override
    public Result<?> upCaseStep(List<WebCaseStep> caseSteps) {
        for (WebCaseStep caseStep : caseSteps) {
            if (Objects.equals(caseStep.getStepDescribe(), "")){return Result.error("操作描述不能为空");}
            try {
                if (caseStep.getId() == null){
                    caseStepMapper.insert(caseStep);
                }else {
                    caseStepMapper.updateById(caseStep);
                }
            }catch (Exception e){
                e.printStackTrace();
                return Result.error("更新失败");
            }

        }
        return Result.success();
    }


    private  List<WebPageElement> webPageElementDeep(Integer Id, List<WebPageElement> allWebPages){
        List<WebPageElement> childrenPage = Lists.newArrayList();
        for (WebPageElement WebPage : allWebPages) {
            if (Objects.equals(WebPage.getParentId(), Id)){
                // 查找对应对应childrenID 对应的元素数据
                List<PageElement> pageElements = pageElementMapper.selectList(new QueryWrapper<PageElement>().eq("page_id",WebPage.getId()));

                if (pageElements.size()!=0){
                    List <WebPageElement> elements = Lists.newArrayList();
                    for (PageElement pageElement : pageElements) {
                        WebPageElement webPageElement = new WebPageElement();
                        webPageElement.setId(pageElement.getId());
                        webPageElement.setName(pageElement.getElementName());
                        elements.add(webPageElement);
                    }
                    WebPage.setChildren(elements);
                }
                childrenPage.add(WebPage);
            }
        }

        return childrenPage;
    }

}
