/*
 * author     : dujun
 * date       : 2023/5/9 15:59
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import com.dujun.springboot.Api.qyh.Qyh;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class AutoTest {

    @BeforeClass
    @DataProvider(name = "myDataProvider")
    public Object[][] dataProvider() {
        Qyh qyh = new Qyh("17310045947");
        return new Object[][]{
                {qyh},
        };
    }


//    @Test(invocationCount =1 ,threadPoolSize = 2,dataProvider = "myDataProvider")
//    public void test_21(Qyh qyhProvider) throws InterruptedException {
//        HashMap<String, Object> payload = ApiOrderData.qyh_applyData("北京市");
//        qyhProvider.fillForm(JSON.toJSONString(payload));
//    }

    @Test(invocationCount = 5000, threadPoolSize = 1, dataProvider = "myDataProvider")
    public void test_22(Qyh qyhProvider) throws InterruptedException {
        new Qyh().newOrder();
    }

    @Test(invocationCount = 5000, threadPoolSize = 1, dataProvider = "myDataProvider")
    public void test_23(Qyh qyhProvider) throws InterruptedException {
        new Qyh().myOrders();
    }

    @Test(invocationCount = 5000, threadPoolSize = 1, dataProvider = "myDataProvider")
    public void test_24(Qyh qyhProvider) throws InterruptedException {
        new Qyh().orderRetainDays();
    }

    @Test(invocationCount = 5000, threadPoolSize = 1, dataProvider = "myDataProvider")
    public void test_25(Qyh qyhProvider) throws InterruptedException {
        new Qyh().cityList();
    }

}
