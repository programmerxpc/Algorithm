package codethoughts.array.minsubarraylen;

import java.util.*;

/**
 * @ClassName MinCoverSubString
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-10-9 17:04
 * @Version 1.0
 */
public class MinCoverSubString76_221009 {

    //s = "ADOBECODEBANC", t = "ABC" "BANC"
    //s = "a", t = "a" "a"
    //s = "a", t = "aa" ""
    public static void main(String[] args) {
        MinCoverSubString76_221009 m = new MinCoverSubString76_221009();
        System.out.println(m.minWindow("ACBDOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        int left = 0;
        int right;
        HashMap<Character, Integer> need = new HashMap<>(); // t中每个字符数量
        HashMap<Character, Integer> window = new HashMap<>(); // 记录need中的字符出现次数
        int count = 0; // window中满足need条件的字符个数
        int minIndex = -1;
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < charT.length; i++) { // 初始化need
            need.put(charT[i], need.getOrDefault(charT[i], 0) + 1);
        }

        for (right = 0; right < charS.length; right++) {
            if (need.get(charS[right]) != null) {
                window.put(charS[right], window.getOrDefault(charS[right], 0) + 1); //将元素加入窗口
                if (window.get(charS[right]).equals(need.get(charS[right]))) {
                    count++;
                }
            }
            while (count == need.size()) {
                if (right - left + 1 < minLen) {
                    minIndex = left;
                    minLen = right - left + 1;
                }
                if (need.get(charS[left]) != null) {
                    if (window.get(charS[left]).equals(need.get(charS[left]))) {
                        count--;
                    }
                    window.put(charS[left], window.get(charS[left]) - 1);
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minIndex, minIndex + minLen);
    }
   
}
