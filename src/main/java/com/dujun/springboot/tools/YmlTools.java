/**
 * author     : dujun
 * date       : 2022/1/17 16:47
 * description: 操作 yml配置文件
 */

package com.dujun.springboot.tools;

import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class YmlTools {

    Map properties;

    public YmlTools(){

    }

    public YmlTools(String filePath){
        InputStream inputStream = null;
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        try {
            inputStream = classPathResource.getInputStream();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        // 调基础工具类的方法
        Yaml yaml = new Yaml();
        properties = yaml.loadAs(inputStream, Map.class);
    }

    public  <T> T getValueByKey(String key, T defaultValue) {
        String separator = ".";
        String[] separatorKeys = null;
        if (key.contains(separator)) {
            // 取下面配置项的情况, user.path.keys 这种
            separatorKeys = key.split("\\.");
        } else {
            // 直接取一个配置项的情况, user
            Object res = properties.get(key);
            return res == null ? defaultValue :(T) res;
        }
        // 下面肯定是取多个的情况
        String finalValue = null;
        Object tempObject = properties;
        for (int i = 0; i < separatorKeys.length; i++) {
            //如果是user[0].path这种情况,则按list处理
            String innerKey = separatorKeys[i];

            Map<String, Object> mapTempObj = (Map) tempObject;
            Object object = mapTempObj.get(innerKey);
            // 如果没有对应的配置项,则返回设置的默认值
            if (object == null) {
                return defaultValue;
            }
            Object targetObj = object;
            // 一次获取结束,继续获取后面的
            tempObject = targetObj;
            if (i == separatorKeys.length - 1) {
                //循环结束
                return (T) targetObj;
            }

        }
        return null;
    }


    public static void main(String[] args) {
        YmlTools ymlTools = new YmlTools("globalConfig.yml");
        Object s = ymlTools.getValueByKey("redis.test.qyh","");

    }


}
