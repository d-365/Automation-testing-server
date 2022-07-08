package com.dujun.springboot.controller;


import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PageElement;
import com.dujun.springboot.service.impl.PageElementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/element")
public class PageElementController {

    @Autowired
    private PageElementServiceImpl pageElementService;

    @PostMapping("/list")
    public Result<?> elementList(@RequestBody HashMap<Object,Object> pageElement){
        return pageElementService.elementList(pageElement);
    }

    @PostMapping("/update")
    public Result<?> updateEle (@RequestBody PageElement pageElement){
        return pageElementService.updateEle(pageElement);
    }

    @DeleteMapping("/delEle/{id}")
    public Result<?> delEle (@PathVariable("id") Integer id){
        return pageElementService.delEle(id);
    }

    @PostMapping("/updateAll")
    public Result<?> updateAll(@RequestBody List<PageElement> elementList){
        return pageElementService.updateAll(elementList);
    }

}

