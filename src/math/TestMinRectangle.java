package math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName TestMinRectangle
 * @Description KY150 最小长方形
 * 题目描述
 *     给定一系列2维平面点的坐标(x, y)，其中x和y均为整数，要求用一个最小的长方形框将所有点框在内。长方形框的边分别平行于x和y坐标轴，点落在边上也算是被框在内。
 * 输入描述:
 *     测试输入包含若干测试用例，每个测试用例由一系列坐标组成，每对坐标占一行，其中|x|和|y|小于1e18；一对0 坐标标志着一个测试用例的结束。注意(0, 0)不作为任何一个测试用例里面的点。一个没有点的测试用例标志着整个输入的结束。
 * 输出描述:
 *     对每个测试用例，在1行内输出2对整数，其间用一个空格隔开。第1对整数是长方形框左下角的坐标，第2对整数是长方形框右上角的坐标。
 * 示例1
 * 输入
 * 12 56
 * 23 56
 * 13 10
 * 0 0
 * 12 34
 * 0 0
 * 0 0
 * 输出
 * 12 10 23 56
 * 12 34 12 34
 * @Author XiaoPengCheng
 * @Date 2021-1-29 20:15
 * @Version 1.0
 */
public class TestMinRectangle {

    private static String[] nums = new String[2];
    private static List<Integer> xList = new ArrayList<>();
    private static List<Integer> yList = new ArrayList<>();
    private static int[] xArray = null;
    private static int[] yArray = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            nums = sc.nextLine().split(" ");
            if (Integer.parseInt(nums[0])==0 && Integer.parseInt(nums[1])==0)
                break;
            xList.add(Integer.parseInt(nums[0]));
            yList.add(Integer.parseInt(nums[1]));
        }

        xArray = new int[xList.size()];
        yArray = new int[yList.size()];
        for (int i = 0;i < xList.size();i++){
            xArray[i] = xList.get(i);
        }
        for (int i = 0;i < yList.size();i++){
            yArray[i] = yList.get(i);
        }

        xArray = selectSort(xArray);
        yArray = selectSort(yArray);
        System.out.println(xArray[0]+" "+yArray[0]+" "+xArray[xArray.length-1]+" "+yArray[yArray.length-1]);

    }

    /**
     *@Author  XiaoPengCheng
     *@Description  选择排序（由小到大）
     *@Date  2021-1-29 21:01
     *@Param  [n]
     *@return  int[]
     */
    private static int[] selectSort(int[] n){
        int temp;
        int minIndex;
        for (int i = 0;i < n.length;i++){
            minIndex = i;
            for (int j = i+1;j < n.length;j++)
                if (n[j] < n[minIndex])
                    minIndex = j;
            temp = n[i];
            n[i] = n[minIndex];
            n[minIndex] = temp;
        }
        return n;
    }

}
