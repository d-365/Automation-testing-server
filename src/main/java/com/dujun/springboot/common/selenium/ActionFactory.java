/*
 * author     : dujun
 * date       : 2022/4/25 17:40
 * description: 用例执行动作工厂
 */

package com.dujun.springboot.common.selenium;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dujun.springboot.Api.qyh.Qyh;
import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.common.actionEnum;
import com.dujun.springboot.data.ApiOrderData;
import com.dujun.springboot.entity.WebCaseStep;
import com.dujun.springboot.tools.RandomValue;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

@Component
public class ActionFactory {

    static String execMsg;

    static UIConsole uiConsole;

    @Autowired
    SeleniumUtils seleniumUtils;

    public  UIConsole openUrl(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            if (Objects.equals(caseStep.getActionValue(), "") || caseStep.getActionValue() == null) {
                execMsg = String.format("步骤ID%s,actionValue不能为空", caseStep.getId());
                uiConsole.setCode(1);
            } else {
                MySelenium.get(driver, caseStep.getActionValue());
                execMsg = String.format("步骤ID%s,执行成功---打开网址: %s", caseStep.getId(), caseStep.getActionValue());
                uiConsole.setCode(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            uiConsole.setCode(1);
            execMsg = String.format("步骤ID%s:---用例执行失败+%s", caseStep.getId(),e.toString());
        }finally {
            uiConsole.setMsg(execMsg);
        }
        return uiConsole;
    }

    public  UIConsole click(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            if (caseStep.getElementId() == null) {
                execMsg = String.format("步骤ID%s,ElementId 不能为空", caseStep.getId());
                uiConsole.setCode(1);
            } else {
                WebElement element = seleniumUtils.getElement(driver, caseStep.getElementId());
                if (element == null) {
                    execMsg = String.format("步骤ID%s,无法定位到对应element", caseStep.getId());
                } else {
                    MySelenium.click(element);
                    execMsg = String.format("步骤ID%s,执行成功---点击元素", caseStep.getId());
                    uiConsole.setCode(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = String.format("步骤ID%s:---用例执行失败", caseStep.getId());
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    public  UIConsole input(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            if (caseStep.getElementId() == null) {
                execMsg = String.format("步骤ID%s,ElementId 不能为空", caseStep.getId());
                uiConsole.setCode(1);
            } else if (Objects.equals(caseStep.getActionValue(), "") || caseStep.getActionValue() == null) {
                execMsg = String.format("步骤ID%s,actionValue不能为空", caseStep.getId());
                uiConsole.setCode(1);
            } else {
                WebElement element = seleniumUtils.getElement(driver, caseStep.getElementId());
                if (element == null) {
                    execMsg = String.format("步骤ID%s,无法定位到对应element", caseStep.getId());
                    uiConsole.setCode(1);
                } else {
                    MySelenium.sendKeys(element, caseStep.getActionValue());
                    execMsg = String.format("步骤ID%s,执行成功--输入元素为:%s", caseStep.getId(), caseStep.getActionValue());
                    uiConsole.setCode(0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            execMsg = String.format("步骤ID%s:---用例执行失败", caseStep.getId());
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    public  UIConsole sleep(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            Thread.sleep(Long.parseLong(caseStep.getActionValue()));
            execMsg = String.format("步骤ID%s,执行成功----driver沉睡%s ms", caseStep.getId(), caseStep.getActionValue());
            uiConsole.setCode(0);
            uiConsole.setMsg(execMsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    public  UIConsole back(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            MySelenium.back(driver);
            execMsg = String.format("步骤ID%s,执行成功----网址后退到%s", caseStep.getId(), driver.getCurrentUrl());
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole forward(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            MySelenium.forward(driver);
            execMsg = String.format("步骤ID%s,执行成功----网址前进到%s", caseStep.getId(), driver.getCurrentUrl());

        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole refresh(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            MySelenium.refresh(driver);
            execMsg = String.format("步骤ID%s,执行成功----网址刷新到%s", caseStep.getId(), driver.getCurrentUrl());
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole getColor(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            WebElement element = seleniumUtils.getElement(driver, caseStep.getElementId());
            if (element == null) {
                execMsg = String.format("步骤ID%s,无法定位到对应element", caseStep.getId());
            } else {
                String color = MySelenium.getColor(element);
                execMsg = String.format("步骤ID%s,执行成功--元素颜色为:%s", caseStep.getId(), color);
                uiConsole.setCode(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole getBackGroundColor(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            WebElement element = seleniumUtils.getElement(driver, caseStep.getElementId());
            if (element == null) {
                execMsg = String.format("步骤ID%s,无法定位到对应element", caseStep.getId());
            } else {
                String color = MySelenium.getBackgroundColor(element);
                execMsg = String.format("步骤ID%s,执行成功--元素颜色为:%s", caseStep.getId(), color);
                uiConsole.setCode(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole getText(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            WebElement element = seleniumUtils.getElement(driver, caseStep.getElementId());
            if (element == null) {
                execMsg = String.format("步骤ID%s,无法定位到对应element", caseStep.getId());
            } else {
                String text = MySelenium.getText(element);
                execMsg = String.format("步骤ID%s,执行成功--元素内文本为:%s", caseStep.getId(), text);
                uiConsole.setCode(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole getCssValue(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            WebElement element = seleniumUtils.getElement(driver, caseStep.getElementId());
            if (element == null) {
                execMsg = String.format("步骤ID%s,无法定位到对应element", caseStep.getId());
            } else if (caseStep.getActionValue() != null) {
                String text = MySelenium.getCssValue(element, caseStep.getActionValue());
                execMsg = String.format("步骤ID%s,执行成功--CSS属性%s文本为:%s", caseStep.getId(), caseStep.getActionValue(), text);
                uiConsole.setCode(0);
            } else {
                execMsg = String.format("步骤ID%s,执行失败--元素、actionValue不能为空", caseStep.getId());
                uiConsole.setCode(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole clear(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            WebElement element = seleniumUtils.getElement(driver, caseStep.getElementId());
            if (element == null) {
                execMsg = String.format("步骤ID%s,无法定位到对应element", caseStep.getId());
                uiConsole.setCode(1);

            } else {
                MySelenium.clear(element);
                execMsg = String.format("步骤ID%s,执行成功--元素内文本清除成功", caseStep.getId());
                uiConsole.setCode(0);
            }
        } catch (Exception e) {
            execMsg = String.format("步骤ID%s,执行失败--元素内文本无法清除", caseStep.getId());
            uiConsole.setCode(1);
            e.printStackTrace();
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole sendAlertKeys(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            Alert alert = MySelenium.getAlert(driver);
            if (alert != null) {
                MySelenium.sendAlertKeys(alert, caseStep.getActionValue());
                execMsg = String.format("步骤ID%s,执行成功--", caseStep.getId());
                uiConsole.setCode(0);
            } else {
                execMsg = String.format("步骤ID%s,执行失败--当前DOM没有Alert弹窗", caseStep.getId());
                uiConsole.setCode(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole acceptAlert(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            Alert alert = MySelenium.getAlert(driver);
            if (alert != null) {
                MySelenium.acceptAlert(alert);
                execMsg = String.format("步骤ID%s,执行成功--成功接受Alert", caseStep.getId());
                uiConsole.setCode(0);
            } else {
                execMsg = String.format("步骤ID%s,执行失败--当前DOM没有Alert弹窗", caseStep.getId());
                uiConsole.setCode(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole dismissAlert(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            Alert alert = MySelenium.getAlert(driver);
            if (alert != null) {
                MySelenium.dismissAlert(alert);
                execMsg = String.format("步骤ID%s,执行成功--成功关闭Alert", caseStep.getId());
                uiConsole.setCode(0);
            } else {
                execMsg = String.format("步骤ID%s,执行失败--当前DOM没有Alert弹窗", caseStep.getId());
                uiConsole.setCode(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = e.toString();
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);

        return uiConsole;
    }

    public  UIConsole switchFrameByName(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            MySelenium.switchToFrame(driver, caseStep.getActionValue());
            execMsg = String.format("步骤ID%s,执行成功--成功关闭Alert", caseStep.getId());
            uiConsole.setCode(0);
        } catch (Exception e) {
            execMsg = String.format("步骤ID%s,执行失败--无法切换到对应frame", caseStep.getId());
            uiConsole.setCode(1);
            e.printStackTrace();
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    public  UIConsole switchFrameByElement(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            WebElement element = seleniumUtils.getElement(driver, caseStep.getElementId());
            if (element == null) {
                execMsg = String.format("步骤ID%s,无法定位到对应element", caseStep.getId());
                uiConsole.setCode(1);
            } else {
                MySelenium.switchToFrame(driver, element);
                execMsg = String.format("步骤ID%s,切换Frame执行成功", caseStep.getId());
                uiConsole.setCode(0);
            }
        } catch (Exception e) {
            execMsg = String.format("步骤ID%s,执行失败--无法切换到对应frame", caseStep.getId());
            uiConsole.setCode(1);
            e.printStackTrace();
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    public  UIConsole defaultAction(WebDriver driver ,WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        execMsg = String.format("步骤ID%s,未找到匹配的actionKey--请检查后重试", caseStep.getId());
        uiConsole.setCode(1);
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    public  UIConsole toDefaultContent(WebDriver driver, WebCaseStep caseStep) {
        uiConsole = new UIConsole();
        try {
            MySelenium.switchToDefaultContent(driver);
            execMsg = String.format("步骤ID%s,执行成功--切回默认的Frame", caseStep.getId());
            uiConsole.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = String.format("步骤ID%s,执行失败", caseStep.getId());
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    /**
     * 轻易花用户填单
     *
     * @param driver   AppiumDriver
     * @param caseStep 用例步骤
     */
    private UIConsole qyhApply(WebDriver driver, WebCaseStep caseStep) {
        UIConsole uiConsole = new UIConsole();
        String execMsg;
        String phone = RandomValue.getTel();
        String city;
        if (Objects.equals(caseStep.getActionValue(), "") || caseStep.getActionValue() == null) {
            city = "南阳市";
        } else {
            city = caseStep.getActionValue();
        }
        try {
            Qyh qyh = new Qyh(phone);
            HashMap<String, Object> payload = ApiOrderData.qyh_applyData(city);
            JSONObject jsonObject = qyh.fillForm(JSON.toJSONString(payload));
            Qyh.mysqlTools.close();
            execMsg = String.format("步骤ID%1$s执行成功--填单完成", caseStep.getId());
            uiConsole.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            execMsg = String.format("步骤ID%1$s执行失败-填单失败%1$S", caseStep.getId());
            uiConsole.setCode(1);
        }
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }

    public UIConsole execAction(actionEnum actionType, WebDriver driver, WebCaseStep caseStep) {
        HashMap<actionEnum, MyFunctionalInterface> myActionMap = new HashMap<>();
        myActionMap.put(actionEnum.OPENURL, this::openUrl);
        myActionMap.put(actionEnum.CLICK, this::click);
        myActionMap.put(actionEnum.INPUT, this::input);
        myActionMap.put(actionEnum.SLEEP, this::sleep);
        myActionMap.put(actionEnum.FORWARD, this::forward);
        myActionMap.put(actionEnum.BACK, this::back);
        myActionMap.put(actionEnum.CLEAR, this::clear);
        myActionMap.put(actionEnum.REFRESH,this::refresh);
        myActionMap.put(actionEnum.GETCOLOR,this::getColor);
        myActionMap.put(actionEnum.GETBACKGROUNDCOLOR,this::getBackGroundColor);
        myActionMap.put(actionEnum.GETTEXT,this::getText);
        myActionMap.put(actionEnum.CSSVALUE, this::getCssValue);
        myActionMap.put(actionEnum.SENDALERTKEYS,this::sendAlertKeys);
        myActionMap.put(actionEnum.ACCEPTALERT,this::acceptAlert);
        myActionMap.put(actionEnum.DISMISSALERT,this::dismissAlert);
        myActionMap.put(actionEnum.SWITCHFRAMEBYNAME,this::switchFrameByName);
        myActionMap.put(actionEnum.SWITCHFRAMEBYELEMENT, this::switchFrameByElement);
        myActionMap.put(actionEnum.TODEFAULTCONTENT, this::toDefaultContent);
        myActionMap.put(actionEnum.QYHAPPLY, this::qyhApply);
        myActionMap.put(actionEnum.DEFAULT, this::defaultAction);
        MyFunctionalInterface functionalInterface = myActionMap.get(actionType);
        return functionalInterface.apply(driver,caseStep);
    }

}

@FunctionalInterface
interface MyFunctionalInterface{

    UIConsole apply(WebDriver driver,WebCaseStep caseStep);
}