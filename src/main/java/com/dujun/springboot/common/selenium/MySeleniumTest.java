package com.dujun.springboot.common.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MySeleniumTest {
//    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = MySelenium.getDriver("chrome");
//        driver.get("https://juejin.cn/backend");
//        String data = (String) MySelenium.executeScript(driver,"console.log('hello world')");
//        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
//        for (LogEntry logEntry : logEntries) {
//            System.out.println(logEntry.getLevel() + logEntry.getMessage());
//        }
//
//    }

    public static void main(String[] args) {
        String path = "D:\\workFile\\djpython\\WebUI_CRM\\common\\pyLogin.py";
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python "+path + " 7");
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}