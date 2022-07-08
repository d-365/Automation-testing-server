/*
 * author     : dujun
 * date       : 2022/6/27 11:17
 * description: 轻易花填单压测
 */

package com.dujun.springboot.Api.test;

import com.dujun.springboot.Api.qyh.Qyh;
import com.dujun.springboot.tools.RandomValue;
import com.dujun.springboot.utils.MysqlTools;
import com.mysql.cj.protocol.x.CompressionSplittedOutputStream;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class qyh_mock {

    @Test
    public void test_apply() {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 1; i++) {
            es.submit(new RunnableQyh());
        }
        es.shutdown();
        while (true) {
            if (es.isTerminated()) {
                Qyh.mysqlTools.close();
                System.out.println("结束了！");
                break;
            }
        }
    }

    @Test
    public void crm_init() {
        // 新建公司+ 公司主账户
        MysqlTools mysqlTools = new MysqlTools();
        for (int i =0;i<1;i++){
            data_init(mysqlTools);
        }
        // 更新账户余额
        String sql = "UPDATE qyh.crm_company_account SET total_money = 5000 , recharge_money = 2500 ,free_money = 2500;";
        mysqlTools.execute(sql);
        mysqlTools.close();
    }

    public void data_init(MysqlTools mysqlTools){
        // 新建公司+ 公司主账户
        String phone = RandomValue.getTel();
        Integer randomInteger = RandomValue.getInteger(1, 10);
        String companySql = String.format("INSERT INTO `qyh`.`crm_company` (`company_name`, `company_number`, `brand_name`, `creator_id`, `creator_name`) VALUES ('%1$s', '%2$s', '%1$s', '83', 'd');", phone, randomInteger);
        String companyQuerySql = String.format("SELECT id FROM qyh.crm_company WHERE company_name = %s LIMIT 1;", phone);
        try {
            mysqlTools.execute(companySql);
            ResultSet resultSet = mysqlTools.executeQuery(companyQuerySql);
            if (resultSet.next()) {
                String companyId = resultSet.getString("id");
                // 创建公司账户
                String drkUserSql = String.format("INSERT INTO `qyh`.`crm_drk_user`(`company_id`, `account_type`, `account`, `name`, `password`, `phone`, `role`, `creator_id`, `creator_name`, `status`, `client_ip`, `remark`, `openid`, `is_thread_remind`, `is_balance_remind`, `balance_remind_money`)VALUES('%1$s', '1', '%2$s', 'interface', '889ce4c35f61a2d05b58cea33ff1f0ad', '%2$s', '1', '83', 'd', '1', '1942171814', NULL, NULL, '0', '0', '0');", companyId,phone);
                mysqlTools.execute(drkUserSql);
                String queryUid = String.format("SELECT id FROM qyh.crm_drk_user WHERE company_id = %s;",companyId);
                ResultSet resultSets = mysqlTools.executeQuery(queryUid);
                if (resultSets.next()){
                    String uid = resultSets.getString("id");
                    String userSql = String.format("INSERT INTO `qyh`.`crm_company_account` (`company_id`, `account_id`, `total_money`, `recharge_money`, `free_money`, `status`, `today_consume`, `day_budget`, `today_distributed`, `total_distributed`, `creator_id`) VALUES ('%1$s', '%2$s', '5000', '2500', '2500', '1', '0', NULL, '0', '44', '1113');",companyId,uid);
                    mysqlTools.execute(userSql);
                    // 创建广告获取广告ID
                    String adName = (companyId + "--" + RandomValue.getInteger(1,99999));
                    String city =  "北京市";
                    String advertisingSql = String.format("INSERT INTO `qyh`.`crm_advertising` (`system_id`, `system_name`, `company_id`, `company_name`, `advertising_name`, `deduct_type`, `plan`, `put_city`, `is_open`, `open_permissions`, `user_status`, `loan_money_min`, `loan_money_max`, `sex`, `occupation`, `education`, `income_min`, `income_max`, `income_type`, `work_age_min`, `work_age_max`, `min_age`, `max_age`, `provident_fund`, `social_security`, `is_car`, `is_house`, `credit_record`, `credit_money`, `credit_money_min`, `credit_money_max`, `wld`, `wld_min`, `wld_max`, `zmf_min`, `zmf_max`, `insurance`, `license`, `today_take_order`, `total_take_order`, `take_order_type`, `order_limit`, `day_order_limit`, `night_order_limit`, `order_time_start`, `order_time_end`, `hour_time_start`, `hour_time_end`, `requirement`, `no_requirement`, `budget_config`, `cpc_price`, `bid_price`, `suggested_price`, `drk_suggested_price`, `remark`) VALUES ('83', 'd', '%1$s', '%2$s', '%3$s', '2', 'B', '%4$s', '1', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '50', '50', '50', NULL, NULL);",companyId,phone,adName,city);
                    mysqlTools.execute(advertisingSql);
                    String adIdSql = String.format("SELECT id FROM qyh.crm_advertising WHERE advertising_name = '%s';",adName);
                    try {
                        ResultSet adResult =  mysqlTools.executeQuery(adIdSql);
                        if (adResult.next()){
                            String adId = adResult.getString("id");
                            //创建员工账户
                            String insertSonAccount = String.format("INSERT INTO `qyh`.`crm_drk_user` (`company_id`, `account_type`, `account`, `name`, `password`, `phone`, `role`, `creator_id`, `creator_name`, `status`, `client_ip`, `remark`, `openid`, `is_thread_remind`, `is_balance_remind`, `balance_remind_money`) VALUES (%1$s, '2', '%2$s', '%2$s', '05257b278941a55249719b7dfeac7bb1', NULL, '2', NULL, NULL, '1', '2104903452', NULL, NULL, '0', '0', '0');",companyId,adName);
                            mysqlTools.execute(insertSonAccount);
                            // 查询员工账户Id
                            String querySonId = String.format("SELECT id from qyh.crm_drk_user WHERE account = '%2$s' AND company_id = '%1$s';",companyId,adName);
                            ResultSet sonResult = mysqlTools.executeQuery(querySonId);
                            if (sonResult.next()){
                                String sonAccountId = sonResult.getString("id");
                                //初始化员工账户数据
                                String sonAccountInit = String.format("INSERT INTO `qyh`.`crm_company_account` (`company_id`, `account_id`, `total_money`, `recharge_money`, `free_money`, `status`, `today_consume`, `day_budget`, `today_distributed`, `total_distributed`, `creator_id`) VALUES (%1$s, %2$s, '0', '0', '0', '1', '0', NULL, '0', '0', '1113');",companyId,sonAccountId);
                                mysqlTools.execute(sonAccountInit);
                                // 广告和子账户关联
                                String relevanceSql = String.format("INSERT INTO `qyh`.`crm_advertising_distribution` (`user_id`, `advertising_id`, `open_permissions`) VALUES (%1$s, %2$s, '1');",sonAccountId,adId);
                                mysqlTools.execute(relevanceSql);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crm_addAdvertising(MysqlTools mysqlTools){
        // 公司ID
        Integer companyId = 203;
        String companyName = "dujun_GS";
        // 创建广告获取广告ID
        String adName = (companyId + "--" + RandomValue.getInteger(1,99999));
        String city =  "北京市";
        String advertisingSql = String.format("INSERT INTO `qyh`.`crm_advertising` (`system_id`, `system_name`, `company_id`, `company_name`, `advertising_name`, `deduct_type`, `plan`, `put_city`, `is_open`, `open_permissions`, `user_status`, `loan_money_min`, `loan_money_max`, `sex`, `occupation`, `education`, `income_min`, `income_max`, `income_type`, `work_age_min`, `work_age_max`, `min_age`, `max_age`, `provident_fund`, `social_security`, `is_car`, `is_house`, `credit_record`, `credit_money`, `credit_money_min`, `credit_money_max`, `wld`, `wld_min`, `wld_max`, `zmf_min`, `zmf_max`, `insurance`, `license`, `today_take_order`, `total_take_order`, `take_order_type`, `order_limit`, `day_order_limit`, `night_order_limit`, `order_time_start`, `order_time_end`, `hour_time_start`, `hour_time_end`, `requirement`, `no_requirement`, `budget_config`, `cpc_price`, `bid_price`, `suggested_price`, `drk_suggested_price`, `remark`) VALUES ('83', 'd', '%1$s', '%2$s', '%3$s', '2', 'B', '%4$s', '1', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '50', '50', '50', NULL, NULL);",companyId,companyName,adName,city);
        mysqlTools.execute(advertisingSql);
        String adIdSql = String.format("SELECT id FROM qyh.crm_advertising WHERE advertising_name = '%s';",adName);
        try {
            ResultSet adResult =  mysqlTools.executeQuery(adIdSql);
            if (adResult.next()){
                String adId = adResult.getString("id");
                //创建员工账户
                String insertSonAccount = String.format("INSERT INTO `qyh`.`crm_drk_user` (`company_id`, `account_type`, `account`, `name`, `password`, `phone`, `role`, `creator_id`, `creator_name`, `status`, `client_ip`, `remark`, `openid`, `is_thread_remind`, `is_balance_remind`, `balance_remind_money`) VALUES (%1$s, '2', '%2$s', '%2$s', '05257b278941a55249719b7dfeac7bb1', NULL, '2', NULL, NULL, '1', '2104903452', NULL, NULL, '0', '0', '0');",companyId,adName);
                mysqlTools.execute(insertSonAccount);
                // 查询员工账户Id
                String querySonId = String.format("SELECT id from qyh.crm_drk_user WHERE account = '%2$s' AND company_id = '%1$s';",companyId,adName);
                ResultSet sonResult = mysqlTools.executeQuery(querySonId);
                if (sonResult.next()){
                    String sonAccountId = sonResult.getString("id");
                    //初始化员工账户数据
                    String sonAccountInit = String.format("INSERT INTO `qyh`.`crm_company_account` (`company_id`, `account_id`, `total_money`, `recharge_money`, `free_money`, `status`, `today_consume`, `day_budget`, `today_distributed`, `total_distributed`, `creator_id`) VALUES (%1$s, %2$s, '0', '0', '0', '1', '0', NULL, '0', '0', '1113');",companyId,sonAccountId);
                    mysqlTools.execute(sonAccountInit);
                    // 广告和子账户关联
                    String relevanceSql = String.format("INSERT INTO `qyh`.`crm_advertising_distribution` (`user_id`, `advertising_id`, `open_permissions`) VALUES (%1$s, %2$s, '1');",sonAccountId,adId);
                    mysqlTools.execute(relevanceSql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
