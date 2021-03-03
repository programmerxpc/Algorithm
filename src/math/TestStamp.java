package math;

/**
 * @ClassName TestStamp
 * @Description 某人有8 角的邮票5 张，1 元的邮票4 张，1 元8 角的邮票6 张，用这些邮票中的一张或若干张可以得到多少种不同的邮资？
 * 输入描述:
 * 无
 * 输出描述:
 * 输出一行，表示题目所求。
 * @Author XiaoPengCheng
 * @Date 2021-3-3 21:31
 * @Version 1.0
 */
public class TestStamp {

    private static int[] money = new int[189]; //邮票金额（单位：角），最多为188

    public static void main(String[] args) {
        System.out.println(getResult());
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  获取邮资
     *@Date  2021-3-3 21:34
     *@Param  []
     *@return  int
     */
    private static int getResult() {
        int count = 0;
        for (int i = 0;i <= 5;i++) {  //5张8角
            for (int j = 0;j <= 4;j++) {  //4张1元
                for (int k = 0;k <= 6;k++)  //6张1元8角
                    money[i*8+j*10+k*18] = 1;  //值为1表示该类方法可以
            }
        }
        for (int i = 0;i < money.length;i++) {
            if (money[i] == 1)
                count++;
        }
        return count - 1; //去除i，j，k都为0的情况
    }


}
