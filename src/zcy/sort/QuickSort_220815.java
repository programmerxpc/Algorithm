package zcy.sort;

import java.util.Arrays;

/**
 * @ClassName QuickSort_220815
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-8-15 16:08
 * @Version 1.0
 */
public class QuickSort_220815 {

    public static void main(String[] args) {
        int[] arr = {3,6,2,5,7,5};
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // arr[L...R]排好序
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1); // <区
            quickSort(arr, p[1] + 1, R); // >区
        }
    }

    // 处理arr[L...R]的函数
    // 返回等于区域（左边界，右边界），所以返回一个长度为2的数组
    public static int[] partition(int[] arr, int L, int R) {
        int less = L - 1; // <区右边界
        int more = R; // >区左边界
        while (L < more) { // L表示当前数的位置
            if (arr[L] < arr[R]) { // 当前数 < 划分值
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) { // 当前数 > 划分值
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[] {less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
