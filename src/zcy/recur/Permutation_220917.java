package zcy.recur;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Permutation_220917
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-17 15:11
 * @Version 1.0
 */
public class Permutation_220917 {

    public static void main(String[] args) {
        System.out.println(permutation("aac"));
    }

    public static List<String> permutation(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        process(s.toCharArray(), 0, res);
        return res;
    }

    // str[i...]范围上，所有的字符，都可以在i位置上，后续都去尝试
    // str[0...i-1]范围上，是之前做的选择
    public static void process(char[] str, int i, List<String> res) {
        if (i == str.length) {
            res.add(String.valueOf(str));
        }
        boolean visit[] = new boolean[26];
        for (int j = i; j < str.length; j++) {
            if (!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process(str, i+1, res);
                swap(str, i, j);
            }
        }
    }
    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
