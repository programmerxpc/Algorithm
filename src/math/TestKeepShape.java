package math;

import java.util.Scanner;

/**
 * 守形数是这样一种整数，它的平方的低位部分等于它本身。 比如25的平方是625，低位部分是25，因此25是一个守形数。 编一个程序，判断N是否为守形数。
 *
 * 输入包括1个整数N，2<=N<100。
 *
 * 可能有多组测试数据，对于每组数据，
 * 输出"Yes!”表示N是守形数。
 * 输出"No!”表示N不是守形数。
 * @author XiaoPengCheng
 * @create 2021-01-24 18:03
 */
public class TestKeepShape {

    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int a = sc.nextInt();
        isKeepShape(a);
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  判断是否为守行数
     *@Date  2021-1-24 20:01
     *@Param  [a]  整数
     *@return  void
     */
    private static void isKeepShape(int a){
        if ((a < 2) || (a > 99)){
            System.out.println("No!");
            return;
        }
        if ((a == a*a%10) || (a == a*a%100)){
            System.out.println("Yes!");
            return;
        }
        System.out.println("No!");
    }

}
