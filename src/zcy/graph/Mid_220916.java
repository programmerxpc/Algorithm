package zcy.graph;

import java.util.*;

/**
 * @ClassName Mid_220916
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-16 15:22
 * @Version 1.0
 */
public class Mid_220916 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(" ");
        int[] array = Arrays.asList(strArr).stream().mapToInt(Integer::parseInt).toArray();
//        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }

        int mid = getMid(list);
        System.out.println(mid);
    }

    public static int getMid(List<Integer> arr) {
        PriorityQueue<Integer> bigQ = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> smallQ = new PriorityQueue<>(((o1, o2) -> o1 - o2));
        bigQ.add(arr.get(0));
        PriorityQueue<Integer> moreNumQ = bigQ;
        PriorityQueue<Integer> lessNumQ = smallQ;
        for (int i = 1; i < arr.size(); i++) {
            int cur = arr.get(i);
//            System.out.println(cur);
            if (cur <= bigQ.peek()) {
                bigQ.add(cur);
                moreNumQ = bigQ;
                lessNumQ = smallQ;
            } else {
                smallQ.add(cur);
                moreNumQ = smallQ;
                lessNumQ = bigQ;
            }
            if ((moreNumQ.size() - lessNumQ.size()) == 2) {
                lessNumQ.add(moreNumQ.poll());
            }
        }
        if (arr.size() % 2 == 1) {
            return moreNumQ.peek();
        } else {
            return (bigQ.peek() + smallQ.peek()) / 2;
        }
    }
}
