package codethoughts.array.sortedsquares;

import java.util.Arrays;

/**
 * @ClassName sortedSquares977_220929
 * @Description
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * @Author XiaoPengCheng
 * @Date 2022-9-29 10:31
 * @Version 1.0
 */
public class sortedSquares977_220929 {

    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        int[] ints = sortedSquares(nums);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;

        int start = 0;
        int end = nums.length - 1;
        int[] res = new int[nums.length];
        int i = nums.length - 1;

        while (start <= end) {
            if (nums[start] * nums[start] >= nums[end] * nums[end]) {
                res[i--] = nums[start] * nums[start];
                start++;
            } else {
                res[i--] = nums[end] * nums[end];
                end--;
            }
        }
        return res;
    }

}
