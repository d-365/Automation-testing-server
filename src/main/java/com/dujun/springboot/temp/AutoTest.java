/*
 * author     : dujun
 * date       : 2023/5/9 15:59
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = {"A"})
public class AutoTest {


    @Factory
    public static Object[] createInstances() {
        Object[] instances = new Object[3];
        instances[0] = new AutoTest();
        instances[1] = new AutoTest();
        instances[2] = new AutoTest();
        return instances;
    }

    @DataProvider(name = "myDataProvider")
    public Object[][] dataProvider() {
        Object[][] data = new Object[1][2];
        data[0][0] = "d";
        data[0][1] = "dd";
        return data;
    }

    @Test(dataProvider = "myDataProvider", groups = {"A-2"})
    public void test_22(String data1, String data2) {
        System.out.println("B-1");
    }

    @Parameters({"age", "name"})
    @Test(groups = {"A-2"})
    public void test_3(String names, String age) {
        System.out.println("B-2");
    }

}



