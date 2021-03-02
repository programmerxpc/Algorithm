package math;

import java.util.Scanner;

/**
 * @ClassName TestFactorialN
 * @Description 输入一个整数n，输出n的阶乘（每组测试用例可能包含多组数据，请注意处理）
 * 输入描述:
 * 一个整数n(1<=n<=20)
 * 输出描述:
 * n的阶乘
 * 示例1
 * 输入
 * 3
 * 输出
 * 6
 * @Author XiaoPengCheng
 * @Date 2021-3-2 20:07
 * @Version 1.0
 */
public class TestFactorialN {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(factorial(n));

    }

    /**
     *@Author  XiaoPengCheng
     *@Description  计算n的阶乘
     *@Date  2021-3-2 20:55
     *@Param  [n]
     *@return  long
     */
    private static long factorial(int n) {
        long temp = 1L;
        for (int i = 1;i <= n;i++){
            temp =  temp * i;
        }
        return temp;
    }


}
