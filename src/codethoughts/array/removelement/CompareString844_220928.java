package codethoughts.array.removelement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName CompareString844_220928
 * @Description
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * @Author XiaoPengCheng
 * @Date 2022-9-28 16:31
 * @Version 1.0
 */
public class CompareString844_220928 {

    public static void main(String[] args) {
        System.out.println(backspaceCompare("#ab", "#b#"));
    }

    public static boolean backspaceCompare(String s, String t) {
        String newS = backspace(s.toCharArray());
        String newT = backspace(t.toCharArray());
        return newS.equals(newT);
    }

    private static String backspace(char[] charArr) {
        int slowIndex = 0;
        int fastIndex;
        for (fastIndex = 0; fastIndex < charArr.length; fastIndex++) {
            if (charArr[fastIndex] != '#') {
                charArr[slowIndex++] = charArr[fastIndex];
            } else { // charArr[fastIndex] = '#'
                if (slowIndex != 0) {
                    slowIndex--;
                }
            }
        }
        return new String(charArr, 0, slowIndex);
    }

}
