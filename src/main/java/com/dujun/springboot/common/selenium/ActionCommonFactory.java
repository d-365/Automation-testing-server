/*
 * author     : dujun
 * date       : 2022/5/6 11:46
 * description: 执行通用的Action操作
 */

package com.dujun.springboot.common.selenium;

import com.dujun.springboot.VO.UIConsole;
import com.dujun.springboot.common.actionEnum;
import com.dujun.springboot.entity.DbConfig;
import com.dujun.springboot.utils.MysqlTools;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.util.HashMap;

@Component
public class ActionCommonFactory {

    static String execMsg;
    static UIConsole uiConsole;

    public  UIConsole execPython(Object actionKey ,String actionValue) {
        uiConsole = new UIConsole();
        try {

        } catch (Exception e) {

        }finally {
            uiConsole.setMsg(execMsg);
        }
        return uiConsole;
    }

    public  UIConsole querySql(Object actionKey ,String actionValue) {
        uiConsole = new UIConsole();
        if (actionValue == null || actionKey == null){
            execMsg = "actionValue 或 actionKey不完整,请检查后重试";
            uiConsole.setCode(1);
        }else {
            MysqlTools mysqlTools = null;
            DbConfig db = (DbConfig) actionKey;
            try {
                // 解析数据库对象
                String jdbcUrl = db.getJdbcUrl();
                String account = db.getAccount();
                String pwd = db.getPwd();
                mysqlTools = new MysqlTools(jdbcUrl,account,pwd);
                // 执行SQL
                ResultSet resultSet = mysqlTools.executeQuery(actionValue);
                if (resultSet!=null){
                    if (resultSet.next()){
                        execMsg = String.valueOf(resultSet.getString(1));
                    }
                }
                uiConsole.setCode(0);
            } catch (Exception e) {
                e.printStackTrace();
                execMsg = "数据库连接失败，或sql执行失败"+e.toString();
                uiConsole.setCode(1);
            }finally {
                if (mysqlTools!=null){
                    mysqlTools.close();
                }
                uiConsole.setMsg(execMsg);
            }
        }

        return uiConsole;
    }

    public  UIConsole updateSql(Object actionKey ,String actionValue) {
        uiConsole = new UIConsole();
        try {

        } catch (Exception e) {

        }finally {
            uiConsole.setMsg(execMsg);
        }
        return uiConsole;
    }

    public  UIConsole defaultAction(String actionKey ,String actionValue) {
        uiConsole = new UIConsole();
        execMsg = "动作执行失败,未找到匹配的actionKey--请检查后重试";
        uiConsole.setCode(1);
        uiConsole.setMsg(execMsg);
        return uiConsole;
    }


    /**
     * 执行通用的Action
     * @param actionType 操作类型
     * @param actionKey  数据库 or File
     * @param actionValue action参数
     * @return 控制台信息
     */
    public UIConsole execAction(actionEnum actionType, Object actionKey , String actionValue){
        HashMap<actionEnum, actionCommonInterface> myActionMap = new HashMap<>();
        myActionMap.put(actionEnum.EXECPYTHON, this::execPython);
        myActionMap.put(actionEnum.QUERYSQL, this::querySql);
        myActionMap.put(actionEnum.UPDATESQL, this::updateSql);
        actionCommonInterface functionalInterface = myActionMap.get(actionType);
        return functionalInterface.apply(actionKey,actionValue);
    }

}


@FunctionalInterface
interface actionCommonInterface{

    UIConsole apply(Object actionKey , String actionValue);
}