package math;

import java.util.Scanner;

/**
 * @ClassName TestSpecialMutiply
 * @Description 写个算法，对2个小于1000000000的输入，求结果。 特殊乘法举例：123 * 45 = 1*4 +1*5 +2*4 +2*5 +3*4+3*5
 * 输入描述:
 * 两个小于1000000000的数
 * 输出描述:
 * 输入可能有多组数据，对于每一组数据，输出Input中的两个数按照题目要求的方法进行运算后得到的结果。
 * 示例1
 * 输入
 * 123 45
 * 输出
 * 54
 * @Author XiaoPengCheng
 * @Date 2021-3-1 21:58
 * @Version 1.0
 */
public class TestSpecialMutiply {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] nums1 = sc.nextLine().split("");
        String[] nums2 = sc.nextLine().split("");
        System.out.println(calculate(nums1, nums2));
    }

    private static int calculate(String[] nums1, String[] nums2) {
        int result = 0;
        for (int i = 0;i < nums1.length;i++){
            for (int j = 0;j < nums2.length;j++){
                result += Integer.parseInt(nums1[i]) * Integer.parseInt(nums2[j]);
            }
        }
        return result;
    }

}
