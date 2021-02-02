package math;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @ClassName TestFloatAdd
 * @Description KY79 浮点数加法
 * 题目描述
 * 求2个浮点数相加的和 题目中输入输出中出现浮点数都有如下的形式： P1P2...Pi.Q1Q2...Qj 对于整数部分，P1P2...Pi是一个非负整数 对于小数部分，Qj不等于0
 * 输入描述:
 * 对于每组案例，每组测试数据占2行，分别是两个加数。
 * 输出描述:
 * 每组案例是n行，每组测试数据有一行输出是相应的和。
 * 输出保证一定是一个小数部分不为0的浮点数
 * 示例1
 * 输入
 * 0.111111111111111111111111111111
 * 0.111111111111111111111111111111
 * 输出
 * 0.222222222222222222222222222222
 * @Author XiaoPengCheng
 * @Date 2021-2-2 14:13
 * @Version 1.0
 */
public class TestFloatAdd {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        System.out.println(add(a,b));
    }

    private static BigDecimal add(String a, String b){
        return new BigDecimal(a).add(new BigDecimal(b));
    }

}
