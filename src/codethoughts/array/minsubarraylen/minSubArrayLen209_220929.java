package codethoughts.array.minsubarraylen;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName minSubArrayLen209_220929
 * @Description
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 * @Author XiaoPengCheng
 * @Date 2022-9-29 14:53
 * @Version 1.0
 */
public class minSubArrayLen209_220929 {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1,1,1,1}; //{2,3,1,2,4,3},7   {1,4,4},1  {1,1,1,1,1,1,1,1}, 11
        System.out.println(minSubArrayLen2(11, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int end;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for (end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= target) { // 达到L右移的条件
                 res = Math.min(res, end - start + 1);
                 sum -= nums[start++]; // start右移
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
//        Integer[] numsI = Arrays.stream(nums).boxed().toArray(Integer[]::new);
//        LinkedList<Integer> numList = Stream.of(numsI).collect(Collectors.toCollection(LinkedList::new));
//        LinkedList<Integer> windowList = new LinkedList<>();
//        int sum = 0;
//        int subArrayLen;
//        int res = Integer.MAX_VALUE;
//
//        while (!numList.isEmpty()) {
//            while (sum < target) { // R右移，直到满足窗口状态
//                Integer i = numList.pollFirst();
//                windowList.addLast(i);
//                sum += i;
//            }
//            //退出循环时记录当前长度最小情况
//            subArrayLen = windowList.size();
//            res = Math.min(res, subArrayLen);
//            while (sum >= target) { // L右移
//                if (!windowList.isEmpty()) {
//                    Integer integer = windowList.pollFirst();
//                    sum -= integer;
//                }
//            }
//        }
//        return res;
    }

    public static int minSubArrayLen2(int target, int[] nums) {
        Integer[] numsI = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        LinkedList<Integer> numsList = Stream.of(numsI).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Integer> windowList = new LinkedList<>();
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (!numsList.isEmpty()) {
            int tmp = numsList.pollFirst();
            windowList.addLast(tmp);
            sum += tmp;
            while (sum >= target) { // 窗口已经形成，达到 left 右移的条件
                res = Math.min(res, windowList.size());
                if (!windowList.isEmpty()) {
                    sum -= windowList.pollFirst();
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
