package zcy.improvement;

import java.util.Arrays;

/**
 * @ClassName KMP_220921
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-21 19:28
 * @Version 1.0
 */
public class KMP_220921 {

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String str2 = "abcabca";
        System.out.println(getIndexOf(str, str2));
    }

    // s>=m
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int next[] = getNextArray(str2);
        System.out.println(Arrays.toString(next));
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) { // i2 == 0  str2中比对的位置已经无法往前跳了
                i1++;
            } else {
                i2 = next[i2]; // str2 往前跳
            }
        }
        // i2 越界代表已经配出整个str2，不是因为i2越界退出，代表没有匹配到str2
        return i2 == str2.length ? i1 - i2 : -1;
    }

    private static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] {-1};
        }
        int next[] = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // next数组的位置
        int cn = 0; // 拿哪个位置的字符和i-1的字符比，也代表当前使用的信息。
        while (i < next.length) {
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) { // 当前跳到cn位置的字符，和i-1位置的字符配不上
                cn = next[cn];
            } else { // cn不大于0，没法往前跳了
                next[i++] = 0;
            }
        }
        return next;
    }

}
