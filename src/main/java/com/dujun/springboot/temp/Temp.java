/*
 * author     : dujun
 * date       : 2022/7/28 14:50
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Temp {

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

    public void fun2() {
        Collection<Integer> collection = new HashSet<Integer>();
        collection.add(3);
        System.out.println(collection.isEmpty());
    }

    public void fun1(String data) {
    }

    @Test
    public void test_fun() {
        fun2();
    }

}