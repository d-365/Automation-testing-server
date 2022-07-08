/*
 * author     : dujun
 * date       : 2022/6/30 9:58
 * description: 执行APP自动化用例
 */

package com.dujun.springboot.common.appium;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import com.dujun.springboot.utils.BeanContext;
import io.appium.java_client.AppiumDriver;
import lombok.extern.log4j.Log4j2;
import org.testng.collections.Lists;
import java.util.List;
import java.util.concurrent.Callable;

@Log4j2
public class ExecAppCase implements Callable<List<UIConsole>> {

    private final Integer caseId;
    private final AppiumDriver driver;
    WebCaseStepMapper webCaseStepMapper = BeanContext.getApplicationContext().getBean(WebCaseStepMapper.class);
    AppUtils appUtils = BeanContext.getApplicationContext().getBean(AppUtils.class);

    public ExecAppCase(Integer caseId,AppiumDriver driver){
        this.caseId = caseId;
        this.driver = driver;
    }

    @Override
    public List<UIConsole> call() {
        List<UIConsole> consoleMsg = Lists.newArrayList();
        try {
            // 查询执行APP用例
            LambdaQueryWrapper<WebCaseStep> stepQueryWrapper = new LambdaQueryWrapper<>();
            stepQueryWrapper.eq(WebCaseStep::getCaseId,caseId);
            stepQueryWrapper.eq(WebCaseStep::getStatus,0);
            stepQueryWrapper.orderByAsc(WebCaseStep::getSort);
            List<WebCaseStep> webCaseSteps = webCaseStepMapper.selectList(stepQueryWrapper);
            // 执行用例步骤
            for (WebCaseStep webCaseStep : webCaseSteps) {
                log.debug(String.format("-----------------执行APP用例步骤%s------------------",webCaseStep.getId()));
                UIConsole console = appUtils.execAppCaseStep(driver,webCaseStep);
                log.debug(String.format("------------用例执行结果%s-------------------",console));
                consoleMsg.add(console);
            }
        }catch (Exception e){
            e.printStackTrace();
            consoleMsg.add(new UIConsole(1,"系统异常"));
            return consoleMsg;
        }
        return consoleMsg;
    }


}
