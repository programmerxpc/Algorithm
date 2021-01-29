package math;

import java.util.Scanner;

/**
 * @ClassName TestMaxCommonDivisor
 * @Description 最大公约数：
 * 题目描述
 * 输入两个正整数，求其最大公约数。
 * 输入描述:
 * 测试数据有多组，每组输入两个正整数。
 * 输出描述:
 * 对于每组输入,请输出其最大公约数。
 * 示例1
 * 输入
 * 49 14
 * 输出
 * 7
 * @Author XiaoPengCheng
 * @Date 2021-1-29 17:18
 * @Version 1.0
 */
public class TestMaxCommonDivisor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(getMaxCommonDivisor(a,b));
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  计算最大公约数
     *@Date  2021-1-29 17:26
     *@Param  [a, b]
     *@return  int
     */
    private static int getMaxCommonDivisor(int a, int b){
        int temp;
        if (a < b){
            temp = a;
            a = b;
            b = temp;
        }
        int c = a%b;
        while (c!=0){
            a = b;
            b = c;
            c = a%b;
        }
        return b;
    }

}
