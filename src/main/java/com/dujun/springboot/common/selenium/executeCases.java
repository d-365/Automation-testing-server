/*
 * author     : dujun
 * date       : 2022/4/19 14:53
 * description: 用例调式-线程
 */

package com.dujun.springboot.common.selenium;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import com.dujun.springboot.utils.BeanContext;
import com.dujun.springboot.utils.UserHttpAgentUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.testng.collections.Lists;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class executeCases implements Callable<List<UIConsole>> {

    // 用例Id
    Integer caseId;
    // 用户请求
    HttpServletRequest request;
    String payload;

    WebCaseStepMapper webCaseStepMapper = BeanContext.getApplicationContext().getBean(WebCaseStepMapper.class);

    SeleniumUtils seleniumUtils = BeanContext.getApplicationContext().getBean(SeleniumUtils.class);

    public executeCases(Integer caseId,String payload, HttpServletRequest request) {
        this.caseId = caseId;
        this.request =request;
        this.payload = payload;
    }

    @Override
    public List<UIConsole> call() {
        List<UIConsole> consoleMsg = Lists.newArrayList();
        WebDriver driver = null;
        try {
            // 获取用例IP地址
            String userAddress = UserHttpAgentUtils.getUserRealIP(request);
            String port = "4444";
            String browserName = null;
            String browserVersion;
            // 获取用户请求浏览器信息
            JSONObject jsonObject = JSON.parseObject(payload);
            browserName = jsonObject.getString("device");
            if (browserName==null || Objects.equals(browserName, "")){
                String userBrowser = UserHttpAgentUtils.geUserBrowser(request);
                if (userBrowser!=null){
                    String[] browser = userBrowser.split("/");
                    browserName = browser[0];
                    browserVersion = browser[1];
                }
            }
            // 1: 执行机 开启seleniumGrid服务

            // 2: 连接远程seleniumServer
            String remoteUrl = "http://"+userAddress+":"+port;
            driver = MySelenium.getRemoteDriver(remoteUrl,browserName);

            // 3: 根据订单ID 获取用例信息
            LambdaQueryWrapper<WebCaseStep> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(WebCaseStep::getCaseId,caseId);
            lambdaQueryWrapper.eq(WebCaseStep::getStatus,0);
            List<WebCaseStep> webCaseSteps = webCaseStepMapper.selectList(lambdaQueryWrapper);
            // 4: 遍历执行用例信息
            for (WebCaseStep CaseStep : webCaseSteps) {
                UIConsole console = seleniumUtils.execWebCase(driver,CaseStep);
                consoleMsg.add(console);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return consoleMsg;
        }finally {
            // 关闭driver
            MySelenium.quite(driver);
        }
        return consoleMsg;
    }

}
