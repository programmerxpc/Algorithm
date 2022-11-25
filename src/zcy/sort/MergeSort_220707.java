package zcy.sort;


import java.util.Arrays;

/**
 * @ClassName MergeSort_220707
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-7-7 14:50
 * @Version 1.0
 */
public class MergeSort_220707 {

    public static void main(String[] args) {
        int[] arr = {3,2,0,1,2,10,27,7,25};
        process(arr, 0, arr.length-1);
        Arrays.toString(arr);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R)
            return;
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid+1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int help[] = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) { // 都不越界
            if (arr[p1] <= arr[p2]){
                help[i] = arr[p1];
                p1++;
                i++;
            } else {
                help[i] = arr[p2];
                p2++;
                i++;
            }
        }
        while (p1 <= M) {
            help[i] = arr[p1];
            p1++;
            i++;
        }
        while (p2 <= R) {
            help[i] = arr[p2];
            p2++;
            i++;
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

}
