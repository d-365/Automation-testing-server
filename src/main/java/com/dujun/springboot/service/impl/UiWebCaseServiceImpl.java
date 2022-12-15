package com.dujun.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dujun.springboot.VO.Result;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.common.appium.AppUtils;
import com.dujun.springboot.common.appium.ExecAppCase;
import com.dujun.springboot.common.selenium.executeCases;
import com.dujun.springboot.entity.UiWebCase;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.mapper.UiWebCaseMapper;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import com.dujun.springboot.service.UiWebCaseService;
import com.dujun.springboot.utils.UserHttpAgentUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testng.collections.Lists;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dujun
 * @since 2022-04-11
 */
@Service
public class UiWebCaseServiceImpl extends ServiceImpl<UiWebCaseMapper, UiWebCase> implements UiWebCaseService {

    @Resource
    private UiWebCaseMapper webCaseMapper;

    @Resource
    private WebCaseStepMapper webCaseStepMapper;

    @Resource
    private AppUtils appUtils;


    @Override
    public Result<?> caseList(Integer caseType) {
        LambdaQueryWrapper<UiWebCase> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UiWebCase::getCaseType,caseType);
        queryWrapper.eq(UiWebCase::getDelFlag, 0);
        queryWrapper.orderByDesc(UiWebCase::getCreateTime);
        // 查询所有的webCase
        List<UiWebCase> AllWebCases = webCaseMapper.selectList(queryWrapper);
        // 查找出所有一级菜单
        List<UiWebCase> parentWebCase = AllWebCases.stream().filter(item->item.getParentId()==0).collect(Collectors.toList());
        for (UiWebCase parentCase : parentWebCase) {
            parentCase.setChildren(getChildren(parentCase.getId(),AllWebCases));
        }
        return Result.success(parentWebCase);
    }

    @Override
    public Result<?> update(UiWebCase uiWebCase) {
        if (uiWebCase.getId() == null){
            webCaseMapper.insert(uiWebCase);
        }else {
            webCaseMapper.updateById(uiWebCase);
        }
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> delWebCase(UiWebCase webCase) throws Exception{
        List<UiWebCase> webCases = new ArrayList<UiWebCase>(){{
            add(webCase);
        }};
        // 查询对应的 case
        delCaseDeep(webCases);
        return Result.success();
    }

    @Override
    public Result<?> deepCopy(UiWebCase webCase) {
        webCase.setId(null);
        webCaseMapper.insert(webCase);
        deepCopyHelp(webCase.getChildren(),webCase.getId());
        return Result.success();
    }

    // 获取用例步骤列表
    @Override
    public Result<?> steps(Integer caseId, Integer current, Integer size) {
        IPage<WebCaseStep> page = new Page<>(current, size);
        QueryWrapper<WebCaseStep> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("case_id", caseId);
        queryWrapper.orderByAsc("sort");
        IPage<WebCaseStep> webCaseSteps = webCaseStepMapper.selectPage(page, queryWrapper);
        return Result.success(webCaseSteps);
    }

    /**
     * 递归复制
     */
    private void deepCopyHelp(List<UiWebCase> webCasesList,Integer parentId){
        if (webCasesList.size() != 0){
            for (UiWebCase webCase : webCasesList) {
                webCase.setId(null);
                webCase.setParentId(parentId);
                webCaseMapper.insert(webCase);
                deepCopyHelp(webCase.getChildren(),webCase.getId());
            }
        }

    }

    /**
     * 递归删除
     */
    private void delCaseDeep(List<UiWebCase> webCase){
        for (UiWebCase MyWebCase : webCase) {
            if (MyWebCase.getChildren() !=null){
                if (MyWebCase.getChildren().size()!=0){
                    delCaseDeep(MyWebCase.getChildren());
                }
            }
            webCaseMapper.deleteById(MyWebCase);
        }
    }

    /**
     * 递归遍历获取子元素
     * @param id 父类ID
     * @param allWebCases 要遍历所有元素
     * @return 子元素列表
     */
    private List<UiWebCase> getChildren(Integer id, List<UiWebCase> allWebCases) {
        List<UiWebCase> childrenWebCases = Lists.newArrayList();
        // 遍历所有节点，将父菜单id与传过来的id比较
        for (UiWebCase allWebCase : allWebCases) {
            if (Objects.equals(id, allWebCase.getParentId())){
                childrenWebCases.add(allWebCase);
            }
        }
        
        // 遍历子集
        for (UiWebCase childrenWebCase : childrenWebCases) {
            childrenWebCase.setChildren(getChildren(childrenWebCase.getId(),allWebCases));
        }

        // 递归退出条件
        if (childrenWebCases.size() == 0) {
            return null;
        }

        return childrenWebCases;
    }

    @Override
    public Result<?> delCaseStep(Integer stepId) {
        webCaseStepMapper.deleteById(stepId);
        return Result.success();
    }

    /**
     * 调试用例
     * @param caseId 用例ID
     * @return Result
     */
    @Override
    public Result<?> debugCase(Integer caseId,String payload, HttpServletRequest request) {
        List<UIConsole> consoles = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        if (payload!=null){
            JSONObject jsonObject = JSON.parseObject(payload);
            String type = jsonObject.getString("type");
            // 执行Web用例
            if (type.equals("1")){
                Future<List<UIConsole>> future = executor.submit(new executeCases(caseId,payload,request));
                try {
                    consoles = future.get();
                } catch (Exception  e) {
                    e.printStackTrace();
                    return Result.error("用例调试失败，请检查后重试");
                }
            }else if (type.equals("2")) {
                try {
                    AppiumDriver driver;
                    // 获取对应的获取DesiredCapabilities配置信息
                    String phoneId = jsonObject.getString("phone");
                    String appConId = jsonObject.getString("app");
                    String deBugType = jsonObject.getString("debugType");
                    DesiredCapabilities desiredCapabilities = appUtils.getDesiredCapabilities(Integer.valueOf(appConId), Integer.valueOf(phoneId));
                    // 服务端调试
                    if (Objects.equals(deBugType, "0")) {
                        // 查询开启AppiumServer
                        log.debug("-------Appium服务端调试--------");
                        boolean appiumStatus = appUtils.AppiumStart();
                        if (appiumStatus) {
                            //  获取AppiumDriver
                            driver = appUtils.getDriver("0.0.0.0", desiredCapabilities);
                        } else {
                            return Result.error("服务端AppiumServer启动失败");
                        }
                    } else {
                        // 客户端调试
                        // 获取执行机IP地址
//                        String userAddress = UserHttpAgentUtils.getUserRealIP(request);
                        String clientIp = jsonObject.getString("clientIp");
                        log.debug("clientIp"+clientIp);
                        log.debug("-------Appium客户端调试--------");
                        driver = appUtils.getDriver(clientIp, desiredCapabilities);
                    }
                    if (driver != null) {
                        // 执行APP用例
                        log.debug("-----------------开始执行APP用例------------------");
                        Future<HashMap<String, Object>> future = executor.submit(new ExecAppCase(caseId, driver));
                        HashMap<String, Object> result = future.get();
                        log.debug("----------------------------App用例执行完毕----------------------------");
                        driver.quit();
                        return Result.success(result);
                    } else {
                        return Result.error("Appium连接Server失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.error("Appium调试用例失败，请检查后重试");
                }
            }
        }
        return Result.success(consoles);
    }

    // seleniumServer下载
    @Override
    public void seleniumServerDownload(HttpServletResponse response) {

    }

}
