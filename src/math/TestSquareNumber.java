package math;

/**
 * @ClassName TestSquareNumber
 * @Description  打印所有不超过n（n<256）的，其平方具有对称性质的数。如11*11=121。
 * 输入描述:
 * 无
 * 输出描述:
 * 每行一个数，表示对称平方数。
 * @Author XiaoPengCheng
 * @Date 2021-3-3 15:21
 * @Version 1.0
 */
public class TestSquareNumber {

    public static void main(String[] args) {
        printNumber();
    }

    private static void printNumber() {
        for (int i = 1;i < 256;i++) {
            StringBuilder sb = new StringBuilder(String.valueOf(i*i));
            if (sb.toString().equals(sb.reverse().toString()))
                System.out.println(i);
        }
    }


}
