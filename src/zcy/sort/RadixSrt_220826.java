package zcy.sort;

import java.util.Arrays;

/**
 * @ClassName RadixSrt_220826
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-8-26 20:34
 * @Version 1.0
 */
public class RadixSrt_220826 {

    public static void main(String[] args) {
        int[] arr = new int[] {8, 3, 1, 9, 9, 20};
        System.out.println(Arrays.toString(arr)); // [8, 3, 1, 9, 9, 20]
        radixSort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 3, 8, 9, 9, 20]
    }

    public static void radixSort(int[] arr) {
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    // 最大的数有多少位
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    // arr[begin...end]排序
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { // 有多少次位就进出桶多少次
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1]; // 将count数组处理成最小前缀和数组
            }
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            for(i = L, j = 0; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

}
