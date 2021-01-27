package math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName TestNumDigit
 * @Description 对于给定的正整数 n，计算其十进制形式下所有位置数字之和，并计算其平方的各位数字之和。
 * 每行输入数据包括一个正整数n(0<n<40000)
 * 对于每个输入数据，计算其各位数字之和，以及其平方值的数字之和，输出在一行中，之间用一个空格分隔，但行末不要有空格。
 * @Author XiaoPengCheng
 * @Date 2021-1-26 22:19
 * @Version 1.0
 */
public class TestNumDigit {

    private static List<Integer> digitList = new ArrayList<>();
    private static List<Integer> squareList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(sumDigit(n)+" "+sumDigitSquare(n));
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  计算各位数字之和
     *@Date  2021-1-27 12:08
     *@Param  [n]
     *@return  int
     */
    private static int sumDigit(int n){
        int sum = 0;
        if (n<=0 || n>=40000)
            return -1;
        while (n!=0){
            digitList.add(n%10);
            n = n/10;
        }
        for (int digit : digitList){
            sum += digit;
        }
        return sum;
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  计算其平方的数字之和
     *@Date  2021-1-27 12:10
     *@Param  [n]
     *@return  int
     */
    private static int sumDigitSquare(int n){
        int sum = 0;
        if (n<=0 || n>=40000)
            return -1;
        n = n * n;
        while (n!=0){
            squareList.add(n%10);
            n = n/10;
        }
        for (int square : squareList){
            sum += square;
        }
        return sum;
    }

}
