/*
 * author     : dujun
 * date       : 2022/7/28 14:50
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.temp;

import java.util.HashMap;
import java.util.Map;

public class Temp {
    protected String address = "dujun";
    private String age;
}

class son {

    public static void main(String[] args) {
//        int[] nums = new int[]{0,3,3,4,5};
//        int target = 6;
//        System.out.println(Arrays.toString(twoSum(nums, target)));

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        System.out.println(addTwoNumbers(l1, l2));

    }

    public static int[] twoSum(int[] nums, int target) {
        int[] resultIndex = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                if (nums[i] + nums[i1] == target) {
                    resultIndex[0] = i;
                    resultIndex[1] = i1;
                    return resultIndex;
                }
            }
        }
        return resultIndex;
    }

    ;

    public static int[] twoSum2(int[] nums, int target) {
        int[] resultIndex = new int[2];
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hashtable.containsKey(target - nums[i])) {
                resultIndex[0] = i;
                resultIndex[1] = hashtable.get(target - nums[i]);
                return resultIndex;
            }
            hashtable.put(nums[i], i);
        }
        return resultIndex;
    }

    ;

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
         * 2 4 3
         * 5 6 4
         */
        ListNode result1 = l1;
        int lengthL1 = 0;
        int lengthL2 = 0;
        while (result1.next != null) {
            result1 = result1.next;
            lengthL1++;
        }

        return null;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}