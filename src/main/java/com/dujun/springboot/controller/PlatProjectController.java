package com.dujun.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.entity.PlatProject;
import com.dujun.springboot.entity.PrtDomain;
import com.dujun.springboot.service.impl.PlatProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * 平台项目表 前端控制器
 * </p>
 *
 * @author dujun
 * @since 2022-05-09
 */
@RestController
@RequestMapping("/prt")
public class PlatProjectController {

    @Autowired
    private PlatProjectServiceImpl projectService;

    @PostMapping("/update")
    public Result<?> updatePrt(@RequestBody PlatProject project){
        return projectService.updatePrt(project);
    }

    @GetMapping("/list")
    public Result<IPage<PlatProject>> projectList(String name , @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "1")Integer current){
        return projectService.projectList(name,size,current);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delPrt (@PathVariable("id") Integer id){
        return projectService.delPrt(id);
    }

    @PostMapping("/domain/update")
    public Result<?> updateDomain(@RequestBody List<PrtDomain> domains){
        return projectService.updateDomain(domains);
    }

    @DeleteMapping("/domain/del/{id}")
    public Result<?> delDomain(@PathVariable("id") Integer id){
        return projectService.delDomain(id);
    }

    @GetMapping("/option")
    public Result<List<PlatProject>> prtList(){
        return projectService.prtList();
    }

}

