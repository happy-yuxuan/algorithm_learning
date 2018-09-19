package com.victor.LeetCode;
import java.util.Stack;

/**
 * leetcode NO.20 括号匹配
 *
 * 通过"stack"来完成括号匹配:
 * 遇到左括号就进栈， 若看到右括号就跟栈顶元素(出栈)进行比较。 若不匹配则失败
 * 遇到右括号时栈可能为空，栈空也失败
 */

public class NO_20 {

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        boolean whether = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    whether = false;
                } else {
                    char topChar = stack.pop();
                    if (c == ')' && topChar != '(') {
                        whether = false;
                    }
                    if (c == ']' && topChar != '[') {
                        whether = false;
                    }
                    if (c == '}' && topChar != '{') {
                        whether = false;
                    }
                }
            }
        }
        if (!stack.isEmpty()) whether = false;
        return whether;
    }

}
