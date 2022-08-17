/*
 * author     : dujun
 * date       : 2022/6/21 14:43
 * description: Api进件
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

public class IntoOrder {

    /**
     * 生成测试数据
     *
     * @return "加密后数据"
     */
    public String payload() {
        String secretKey = "7221wAUVpg1Cd5wx31742983";
        String phone = "13123931553";
        int sex = 1;
        int age = 27;
        String realName = "郑新";
        String payload = String.format("age={$1%s}&phone={$2%s}&realName={$3%s}&sex={$4%s}", age, phone, realName, sex);
        return null;
    }

    /**
     * 执行进件接口
     */
    @Test
    public void apply() {
        String url = "http://testqyh.qyihua.com/api/intoOrder";
        JSONObject orderData = new JSONObject() {{
            put("phone", "18397858213");
            put("sex", 0);
            put("age", 0);
            put("realName", "dujun");
            put("loanMoney", "5");
            put("loanTime", 1);
            put("loanGoal", 0); // 资金用途
            put("province", "浙江省");
            put("city", "杭州市");
            put("education", 4);
            put("occupation", 2); // 职业身份
            put("occupationData", JSON.toJSONString(new HashMap<String, Object>() {{
                put("year_income", 299999);
                put("is_license", 0);
                put("operating_years", 11);
            }}));
            put("socialSecurity", 2);
            put("accumulationFund", 2);
            put("isHasHouse", 1);
            put("houseData", JSON.toJSONString(new HashMap<String, Object>() {{
                put("house_type", 0);
                put("house_price", 500000);
            }}));
            put("isHasCar", 1);
            put("carData", JSON.toJSONString(new HashMap<String, Object>() {{
                put("car_price", 99999);
            }}));
            put("creditLimit", 3000);
            put("wld", 4999);
            put("zmf", 700);
            put("creditRecord", 3);
            put("insurance", 1);
            put("insuranceData", JSON.toJSONString(new HashMap<String, Integer>() {{
                put("insurance_company", 1);
                put("insurance_price", 49999);
            }}));
        }};
        JSONObject payload = new JSONObject() {{
            put("action", "");
            put("timestamp", new Date().getTime());
            put("mobile", "");
            put("name", "");
            put("idcard", "");
            put("ipaddress", "");
            put("city", "");
            put("sign", "");
        }};
    }

    @Test
    public void temp() {
        System.out.println();
    }

}
