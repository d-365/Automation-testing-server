/**
 * author     : dujun
 * date       : 2023/8/14 16:40
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestNgListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("测试开始" + iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("测试执行成功" + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("测试执行失败");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("测试跳过");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("onStart--全部用例测试开始之前" + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("onFinish--全部测试完全执行完毕" + Arrays.toString(iTestContext.getAllTestMethods()));
    }
}
