package codethoughts.array.minsubarraylen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName TotalFruit904_220930
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-30 20:25
 * @Version 1.0
 */
public class TotalFruit904_220930 {

    public static void main(String[] args) {
        TotalFruit904_220930 t = new TotalFruit904_220930();
        /**
         [1,2,1] 3      [0,1,2,2] 3     [1,2,3,2,2] 4       [3,3,3,1,2,1,1,2,3,3,4] 5
         */
        int[] fruits= {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(t.totalFruit(fruits));
    }

    public int totalFruit(int[] fruits) {
        int left = 0;
        int right;
        int[] f = new int[fruits.length]; // 维护每一种元素出现次数
        int category = 0; // 窗口内元素种类
        int res = -1;

        for (right = 0; right < fruits.length; right++) {
            f[fruits[right]]++;
            if (f[fruits[right]] == 1) {
                category++;
            }
            while (category > 2) {
                f[fruits[left]]--;
                if (f[fruits[left]] == 0) { // 该元素是重复元素中最后一个
                    category--;
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public int totalFruitNo(int[] fruits) {
        Integer[] integers = Arrays.stream(fruits).boxed().toArray(Integer[]::new);
        LinkedList<Integer> fruitsList = Stream.of(integers).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Integer> window = new LinkedList<>();
        LinkedList<Integer> windowDistinct;
        Integer temp;
        int res = -1;
        while (!fruitsList.isEmpty()) {
            temp = fruitsList.pollFirst();
            window.addLast(temp);
            windowDistinct = window.stream().distinct().collect(Collectors.toCollection(LinkedList::new));
            while (windowDistinct.size() > 2) { // 当种类大于2时，满足 L 右移，此时窗口多一种类
//                res = Math.max(res, window.size() - 1);
                if (!window.isEmpty()) {
                    window.pollFirst();
                    windowDistinct = window.stream().distinct().collect(Collectors.toCollection(LinkedList::new));
                }
            }
            res = Math.max(res, window.size());
        }
        return res;
    }

}
