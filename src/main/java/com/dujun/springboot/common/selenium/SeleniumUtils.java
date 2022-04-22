/*
 * author     : dujun
 * date       : 2022/4/18 11:06
 * description: selenium执行工具类
 */

package com.dujun.springboot.common.selenium;

import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.entity.PageElement;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.mapper.PageElementMapper;
import com.dujun.springboot.mapper.WebCaseStepMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class SeleniumUtils {

    @Resource
    WebCaseStepMapper webCaseStepMapper;
    @Resource
    PageElementMapper pageElementMapper;

    // 执行用例信息
    public UIConsole execWebCase(WebDriver driver,WebCaseStep caseStep){
        String execMsg = null;
        UIConsole uiConsole = new UIConsole();
        action actionKey = action.valueOf(webCaseStepMapper.getActionKey(caseStep.getActionId()).trim().toUpperCase());
        switch (actionKey){
            case OPENURL:
                try {
                    if (Objects.equals(caseStep.getActionValue(), "")|| caseStep.getActionValue()==null){
                        execMsg= String.format("步骤ID%s,actionValue不能为空",caseStep.getId());
                        uiConsole.setCode(1);
                    }else {
                        MySelenium.get(driver,caseStep.getActionValue());
                        execMsg = String.format("步骤ID%s,执行成功---打开网址: %s",caseStep.getId(),caseStep.getActionValue());
                        uiConsole.setCode(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    execMsg = String.format("步骤ID%s:---用例执行失败",caseStep.getId());
                }
                uiConsole.setMsg(execMsg);
                break;
            case CLICK:
                try {
                    if (caseStep.getElementId() == null){
                        execMsg= String.format("步骤ID%s,ElementId 不能为空",caseStep.getId());
                        uiConsole.setCode(1);
                    }else {
                        WebElement element = getElement(driver,caseStep.getElementId());
                        if (element==null){
                            execMsg= String.format("步骤ID%s,无法定位到对应element",caseStep.getId());
                        }else {
                            MySelenium.click(element);
                            execMsg = String.format("步骤ID%s,执行成功---点击元素",caseStep.getId());
                            uiConsole.setCode(0);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    execMsg = String.format("步骤ID%s:---用例执行失败",caseStep.getId());
                }
                uiConsole.setMsg(execMsg);
                break;
            case INPUT:
                try {
                    if (caseStep.getElementId() == null){
                        execMsg= String.format("步骤ID%s,ElementId 不能为空",caseStep.getId());
                        uiConsole.setCode(1);
                    }else if (Objects.equals(caseStep.getActionValue(), "")|| caseStep.getActionValue()==null){
                        execMsg= String.format("步骤ID%s,actionValue不能为空",caseStep.getId());
                        uiConsole.setCode(1);
                    }else {
                        WebElement element = getElement(driver,caseStep.getElementId());
                        if (element==null){
                            execMsg= String.format("步骤ID%s,无法定位到对应element",caseStep.getId());
                        }else {
                            MySelenium.sendKeys(element,caseStep.getActionValue());
                            execMsg = String.format("步骤ID%s,执行成功--输入元素为:%s",caseStep.getId(),caseStep.getActionValue());
                            uiConsole.setCode(0);
                        }
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    execMsg = String.format("步骤ID%s:---用例执行失败",caseStep.getId());
                }
                uiConsole.setMsg(execMsg);
                break;
            case SLEEP:
                try {
                    Thread.sleep(Long.parseLong(caseStep.getActionValue()));
                    execMsg = String.format("步骤ID%s,执行成功----driver沉睡%s ms",caseStep.getId(),caseStep.getActionValue());
                    uiConsole.setCode(0);
                    uiConsole.setMsg(execMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case execPython:
                break;
            default :
                execMsg= String.format("步骤ID%s,未找到匹配的actionKey--请检查后重试",caseStep.getId());
                uiConsole.setCode(1);
                uiConsole.setMsg(execMsg);
        }
        return uiConsole;
    }

    // 获取元素对象
    public WebElement getElement(WebDriver driver,Integer elementId){
        WebElement element = null;
        PageElement pageElement = pageElementMapper.selectById(elementId);
        String locationWay = pageElement.getLocationWay();
        String locationValue = pageElement.getLocationValue();
        switch (locationWay){
            case "id":
                element = MySelenium.ById(driver,locationValue);
                break;
            case "xpath":
                element = MySelenium.ByXpath(driver,locationValue);
                break;
            default:
                return null;
        }
        return element;
    }


}

// Action枚举
enum action{
    OPENURL,
    CLICK,
    SLEEP,
    INPUT,
    switchFrame,
    excJs,
    mouseAction,
    keyboardOption,
    selectOption,
    execPython,
}

