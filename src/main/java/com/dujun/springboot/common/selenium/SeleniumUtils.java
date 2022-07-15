/*
 * author     : dujun
 * date       : 2022/4/18 11:06
 * description: selenium执行工具类
 */

package com.dujun.springboot.common.selenium;

import com.dujun.springboot.VO.AssertConsole;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.common.actionEnum;
import com.dujun.springboot.entity.PageElement;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.mapper.PageElementMapper;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Component
@Service
public class SeleniumUtils {

    @Resource
    private WebCaseStepMapper webCaseStepMapper;

    @Resource
    private PageElementMapper pageElementMapper;

    @Autowired
    private ActionFactory actionFactory;

    @Autowired
    private ActionCommonFactory actionCommonFactory;

    // 执行用例步骤
    public UIConsole execWebCase(WebDriver driver,WebCaseStep caseStep){

        UIConsole uiConsole;
        actionEnum actionKey;
        try {
            actionKey = actionEnum.valueOf(webCaseStepMapper.getActionKey(caseStep.getActionId()).trim().toUpperCase());
        }catch (Exception e){
            actionKey = actionEnum.DEFAULT;
        }
        uiConsole = actionFactory.execAction(actionKey,driver,caseStep);
        return uiConsole;
    }

    // 获取元素对象
    public WebElement getElement(WebDriver driver,Integer elementId){
        WebElement element = null;
        PageElement pageElement = pageElementMapper.selectById(elementId);
        String locationWay = pageElement.getLocationWay();
        String conditions = pageElement.getConditions();
        String locationValue = pageElement.getLocationValue();
        switch (locationWay){
            case "id":
                if (conditions == null || Objects.equals(conditions, "")){
                    element = MySelenium.ById(driver,locationValue);
                }else {
                    element = MySelenium.findElement(driver,conditions, By.id(locationValue));
                }
                break;
            case "xpath":
                if (conditions == null || Objects.equals(conditions, "")){
                    element = MySelenium.ByXpath(driver,locationValue);
                } else {
                    element = MySelenium.findElement(driver,conditions, By.xpath(locationValue));
                }
                break;
            default:
                return null;
        }
        return element;
    }

    // 执行断言操作
    public AssertConsole execAssert(WebDriver driver, WebCaseStep caseStep){
        WebAssertType assertType = WebAssertType.valueOf(caseStep.getAssertType());
        String assertValue = caseStep.getAssertValue().trim();
        String msg = "";
        boolean result =false;
        // 初始化断言控制台
        AssertConsole assertConsole = new AssertConsole();
        assertConsole.setAssertType(caseStep.getAssertType());
        assertConsole.setExpectValue(assertValue);

        if (Objects.equals(assertValue, "")){
            msg = "断言值为空,请检查后重试";
            assertConsole.setMsg(msg);
            assertConsole.setResult(false);
            assertConsole.setStepId(caseStep.getId());
            return assertConsole;
        }

        switch (assertType){
            case URLIS:
                String driverUrl = MySelenium.getCurrentUrl(driver);
                if (Objects.equals(driverUrl, assertValue)){
                    result = true;
                }
                assertConsole.setRealityValue(driverUrl);
                break;
            case TITLEIS:
                String driverTitle = MySelenium.getTitle(driver);
                if (Objects.equals(driverTitle, assertValue)){
                    result = true;
                }
                assertConsole.setRealityValue(driverTitle);
                break;
            default:
                msg = "未找到对应的断言方式";
                break;
        }
        assertConsole.setStepId(caseStep.getId());
        assertConsole.setResult(result);
        assertConsole.setMsg(msg);
        return assertConsole;
    }

    // 执行通用的Action操作
    public UIConsole runAction(String actionType ,Object actionKey, String actionValue){
        UIConsole uiConsole;
        actionEnum AcType;
        try {
            AcType = actionEnum.valueOf(actionType.trim().toUpperCase());
        }catch (Exception e){
            AcType = actionEnum.DEFAULT;
        }
        uiConsole = actionCommonFactory.execAction(AcType,actionKey,actionValue);
        return  uiConsole;
    }


}

