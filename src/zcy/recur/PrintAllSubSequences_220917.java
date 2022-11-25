package zcy.recur;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PrintAllSubSequences
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-17 10:48
 * @Version 1.0
 */
public class PrintAllSubSequences_220917 {

    public static void main(String[] args) {

    }

    public static List<String> f(String str) {
        char[] chars = str.toCharArray();
        List<String> list = new ArrayList<>();
        process(chars, 0, list, "");
        return list;
    }

    // 当前来到i位置，要和不要，走两条路
    // res 之前的选择所形成的的列表
    public static void process(char[] str, int i, List<String> res, String path) {
        if (i == str.length) {
            res.add(path);
            return;
        }
        String yes = path + String.valueOf(str[i]);
        process(str, i+1, res, yes); // 要当前字符的路
        String no = path;
        process(str, i+1, res, no);
    }

}
