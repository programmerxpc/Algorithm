package math;

import java.util.Scanner;

/**
 * @ClassName TestNumberSum
 * @Description KY84 数字求和：
 * 题目描述
 * 给定一个正整数a，以及另外的5个正整数，问题是：这5个整数中，小于a的整数的和是多少？
 * 输入描述:
 * 输入一行，只包括6个小于100的正整数，其中第一个正整数就是a。
 * 输出描述:
 * 可能有多组测试数据，对于每组数据，
 * 输出一行，给出一个正整数，是5个数中小于a的数的和。
 * 示例1
 * 输入
 * 10 1 2 3 4 11
 * 输出
 * 10
 * @Author XiaoPengCheng
 * @Date 2021-1-31 15:11
 * @Version 1.0
 */
public class TestNumberSum {

    private static int[] numArr = new int[6];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] numStrArr = sc.nextLine().split(" ");
        for (int i = 0;i < numStrArr.length;i++){
            numArr[i] = Integer.parseInt(numStrArr[i]);
        }

        System.out.println(sumLessThanA(numArr));
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  数组中小于a的整数和
     *@Date  2021-1-31 16:08
     *@Param  [arr]
     *@return  int
     */
    private static int sumLessThanA(int[] arr){
        int sum = 0;
        int a = arr[0];
        for (int i = 0;i < arr.length;i++){
            if (arr[i] < a)
                sum += arr[i];
        }
        return sum;
    }

}
