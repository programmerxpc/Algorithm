package zcy.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName sortArrayDistanceLessK
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-8-17 15:45
 * @Version 1.0
 */
public class sortArrayDistanceLessK_220817 {

    public static void main(String[] args) {
        int[] arr = new int[] {8, 3, 1, 9, 9, 20};
        System.out.println(Arrays.toString(arr)); // [8, 3, 1, 9, 9, 20]
        sortArrayDistanceLessK(arr, 2);
        System.out.println(Arrays.toString(arr)); // [1, 3, 8, 9, 9, 20]
    }

    public static void sortArrayDistanceLessK(int[] arr, int k) {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

}
