package com.code.algo.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author panjb
 */
public class StackAlgo {

    private static final Map<Character, Character> PARIS = new HashMap<>(4);

    static {
        PARIS.put('}', '{');
        PARIS.put(')', '(');
        PARIS.put(']', '[');
    }

    public static void main(String[] args) {
        System.out.println(validateBracket("{[]}"));
    }

    /**
     * 有效括号(LeetCode 20)
     * <p>用一个栈存储左括号：
     * 从左到右遍历表达式，左括号直接入栈；当遇到右括号时，比较栈顶括号与当前右括号是否配对，如果匹配，弹出栈顶，否则失败。
     * 最后判断栈是否为空，栈为空则为有效括号
     * <p>时间复杂度：O(n)，空间复杂度：O(1)
     * @param expression 表达式
     * @return 有效-true，反之-false
     */
    public static boolean validateBracket(String expression) {
        if (expression == null) {
            return true;
        }
        Deque<Character> stack = new LinkedList<>();
        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);
            if (PARIS.containsKey(c)) {
                if (stack.isEmpty() || !PARIS.get(c).equals(stack.peek())) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
            i++;
        }
        return stack.isEmpty();
    }

}
