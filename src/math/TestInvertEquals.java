package math;

/**
 * @ClassName  TestInvertEquals
 * @Description
 * 设N是一个四位数，它的9倍恰好是其反序数（例如：1234 的反序数是4321），求N的值。
 * 输入描述:
 * 无
 * 输出描述:
 * 每行一个数，表示满足题目要求的数。
 * @Author XiaoPengCheng
 * @Date 2021-3-5 15:22
 * @Version 1.0
 */
public class TestInvertEquals {

    public static void main(String[] args) {
        getResult();
    }

    private static void getResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1000;i < 10000;i++) {
            sb.append(i);
            if(i*9 == Integer.valueOf(sb.reverse().toString())){
                System.out.println(i);
                sb.delete(0, sb.length());
            }
            sb.delete(0, sb.length());
        }
    }


}
