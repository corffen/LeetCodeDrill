package com.gordon.leetcode.stack;

import java.util.ArrayDeque;

public class StackSolution {
    private boolean isLeft(Character c) {
        return c == '[' || c == '{' || c == '(';
    }

    private boolean isMatch(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
    }

    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (isLeft(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty() || (!isMatch(stack.poll(), s.charAt(i)))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
