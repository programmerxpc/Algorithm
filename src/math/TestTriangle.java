package math;

import java.util.Scanner;

/**
 * @ClassName TestTriangle
 * @Description 三角形的边：
 * 给定三个已知长度的边，确定是否能够构成一个三角形，这是一个简单的几何问题。
 * 这要求两边之和大于第三边。实际上，并不需要检验所有三种可能，只需要计算最短的两个边长之和是否大于最大那个就可以了。
 * 这次的问题就是：给出三个正整数，计算最小的数加上次小的数与最大的数之差。
 * 输入：每一行包括三个数据a, b, c，并且都是正整数，均小于10000。
 * 输出：对于输入的每一行，在单独一行内输出结果s。s=min(a,b,c)+mid(a,b,c)-max(a,b,c)。上式中，min为最小值，mid为中间值，max为最大值。
 * @Author XiaoPengCheng
 * @Date 2021-1-28 19:35
 * @Version 1.0
 */
public class TestTriangle {

    public static void main(String[] args) {
        int[] n = new int[3];
        Scanner sc = new Scanner(System.in);
        String nums = sc.nextLine();
        String[] numString = nums.split(" ");
        for (int i = 0;i<numString.length;i++){
            n[i] = Integer.valueOf(numString[i]);
        }
        n = selectSort(n);
        int result = calculate(n);
        System.out.println(result);
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  计算最小的数加上次小的数与最大的数之差
     *@Date  2021-1-28 19:49
     *@Param  [n]
     *@return  int
     */
    private static int calculate(int[] n){
        return n[0]+n[1]-n[2];
    }

    /**
     *@Author  XiaoPengCheng
     *@Description  选择排序（由小到大）
     *@Date  2021-1-28 19:46
     *@Param  [n]
     *@return  int[]
     */
    private static int[] selectSort(int[] n){
        int temp;
        int minIndex;
        for (int i = 0;i < n.length;i++) {
            minIndex = i;
            for (int j = i+1;j<n.length;j++) {
                if (n[j] < n[minIndex])
                    minIndex = j;
            }
            temp = n[i];
            n[i] = n[minIndex];
            n[minIndex] = temp;
        }
        return n;
    }

}
