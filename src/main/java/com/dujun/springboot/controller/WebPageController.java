package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.common.selenium.MyConditions;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.entity.WebPage;
import com.dujun.springboot.service.impl.WebPageServiceImpl;
import org.hibernate.boot.jaxb.hbm.spi.PluralAttributeInfoIdBagAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;
import org.testng.collections.Lists;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/webPage")
public class WebPageController {
    @Autowired
    private WebPageServiceImpl webPageService;

    /*
     * web自动化 模块页面列表
     */
    @GetMapping("/pageList")
    public Result<List<WebPage>> pageList(){
        return webPageService.PAGE_LIST();
    }

    @PostMapping("/update")
    public Result<?> pageUpdate(@RequestBody WebPage Page){
        return webPageService.pageUpdate(Page);
    }

    @DeleteMapping("/delete/{pageId}")
    public Result<?> delPage(@PathVariable("pageId") Integer pageId ){
        return webPageService.pageDel(pageId);
    }

    // 页面元素级联选择
    @PostMapping("/webPageElement")
    public Result<?> webPageElement(){
        return webPageService.webPageElement();
    }

    // 更新用例步骤
    @PostMapping("/caseStep/update")
    public Result<?> upCaseStep(@RequestBody List<WebCaseStep> caseSteps){
        return webPageService.upCaseStep(caseSteps);
    }

    // 获取元素定位
    @PostMapping("/conditionsWay")
    public Result<?> conditionsWay(){
        List<HashMap<String,String>> myConditions = Lists.newArrayList();

        for (MyConditions value : MyConditions.values()) {
            HashMap<String,String>  condition = new HashMap<>();
            condition.put("name",value.toString());
            condition.put("value",value.getDescribe());
            myConditions.add(condition);
        }

        return Result.success(myConditions);
    }

}

