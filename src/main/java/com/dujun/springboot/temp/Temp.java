/*
 * author     : dujun
 * date       : 2022/7/28 14:50
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import org.checkerframework.checker.units.qual.K;
import org.junit.Test;

import java.util.*;

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
        HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>(){{
            put(5,new ArrayList<Integer>(){{add(1);}});
            put(9,new ArrayList<Integer>(){{add(2);}});
        }};
        List<Map.Entry<Integer, List<Integer>>> set = new ArrayList<>(hashMap.entrySet());
        set.sort((q,w)-> -(q.getKey().compareTo(w.getKey())));
        System.out.println(set.get(0));
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