package zcy.recur;

import java.util.Stack;

/**
 * @ClassName ReverseStack_220918
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-18 18:26
 * @Version 1.0
 */
public class ReverseStack_220918 {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        reverse(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

}
