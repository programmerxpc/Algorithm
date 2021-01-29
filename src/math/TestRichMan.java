package math;

/**
 * @ClassName TestRichMan
 * @Description 百万富翁问题：
 * 一个百万富翁遇到一个陌生人，谈了一个换钱的计划。该计划如下：我每天给你10 万元，你第一天给我1 分钱，第二天2 分钱，
 * 第三天4 分钱…
 * 这样交换 30 天后，百万富翁交出了多少钱？陌生人交出了多少钱？（注意一个是万元，一个是分）
 * 输入描述:
 * 该题没有输入
 * 输出描述:
 * 输出两个整数，分别代表百万富翁交出的钱和陌生人交出的钱，富翁交出的钱以万元作单位，陌生人交出的钱以分作单位。
 * @Author XiaoPengCheng
 * @Date 2021-1-29 12:24
 * @Version 1.0
 */
public class TestRichMan {

    public static void main(String[] args) {
        System.out.println(richManPay()+" "+strangerPay());
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  陌生人交出的钱
     *@Date  2021-1-29 13:20
     *@Param  []
     *@return  int  分
     */
    private static int strangerPay(){
        int money = 0;
        for (int i = 1;i <= 30;i++){
            money += Math.pow(2,i-1);
        }
        return money;
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  富翁交出的钱
     *@Date  2021-1-29 13:20
     *@Param  []
     *@return  int  万元
     */
    private static int richManPay(){
        return 10*30;
    }

}
