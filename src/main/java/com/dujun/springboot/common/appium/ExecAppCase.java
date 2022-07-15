/*
 * author     : dujun
 * date       : 2022/6/30 9:58
 * description: 执行APP自动化用例
 */

package com.dujun.springboot.common.appium;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.VO.AssertConsole;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import com.dujun.springboot.utils.BeanContext;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.testng.collections.Lists;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

@Log4j2
public class ExecAppCase implements Callable<HashMap<String,Object>> {

    private final Integer caseId;
    private final AppiumDriver driver;
    WebCaseStepMapper webCaseStepMapper = BeanContext.getApplicationContext().getBean(WebCaseStepMapper.class);
    AppUtils appUtils = BeanContext.getApplicationContext().getBean(AppUtils.class);

    public ExecAppCase(Integer caseId,AppiumDriver driver){
        this.caseId = caseId;
        this.driver = driver;
    }

    @Override
    public HashMap<String,Object> call() {
        // 用例执行结果
        List<UIConsole> consoleMsg = Lists.newArrayList();
        // 用例断言结果
        List<AssertConsole> assertConsoles = Lists.newArrayList();
        // 用例结果封装
        HashMap<String,Object> result = new HashMap<>();
        try {
            // 查询执行APP用例
            LambdaQueryWrapper<WebCaseStep> stepQueryWrapper = new LambdaQueryWrapper<>();
            stepQueryWrapper.eq(WebCaseStep::getCaseId,caseId);
            stepQueryWrapper.eq(WebCaseStep::getStatus,0);
            stepQueryWrapper.orderByAsc(WebCaseStep::getSort);
            List<WebCaseStep> webCaseSteps = webCaseStepMapper.selectList(stepQueryWrapper);

            for (WebCaseStep webCaseStep : webCaseSteps) {
                // 执行用例步骤
                log.debug(String.format("-----------------执行APP用例步骤%s------------------",webCaseStep.getId()));
                UIConsole console = appUtils.execAppCaseStep(driver,webCaseStep);
                log.debug(String.format("------------用例执行结果%s-------------------",console));
                consoleMsg.add(console);
                // 执行步骤断言操作
                if (webCaseStep.getAssertType()!=null && !Objects.equals(webCaseStep.getAssertType(), "")){
                    AssertConsole assertConsole = appUtils.execStepAssert(driver,webCaseStep);
                    assertConsoles.add(assertConsole);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            consoleMsg.add(new UIConsole(1,"系统异常"));
            return result;
        }finally {
            // 封装用例结果
            result.put("result",consoleMsg);
            result.put("assert",assertConsoles);
        }
        return result;
    }


}
