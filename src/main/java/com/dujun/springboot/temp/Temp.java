/*
 * author     : dujun
 * date       : 2022/7/28 14:50
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import com.dujun.springboot.VO.exportVo.UserExport;
import com.dujun.springboot.mapper.UserMapper;
import lombok.Data;
import lombok.val;
import lombok.var;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.aspectj.weaver.patterns.HasMemberTypePattern;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.devtools.v99.storage.model.InterestGroupAccessed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.testng.collections.Lists;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class Temp {

    @Resource
    private UserMapper userMapper;

    static  String data = "";
    static Integer integer = 1;
    public static int[] fun(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{i, hashMap.get(target - nums[i])};
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {

    }

    public static Integer fun2(Integer data) {
        integer+=1;
        data++;
        if (data<5){
            fun2(data) ;
        }
        return integer;
    }


}

@Component
@Data
class Demo{
    private String name = "dujun";
    @Value("5")
    private String age;
}