package com.gsh.springbootquick.algorithm.algorithm;

import java.util.*;

/**
 * 尽量少删除括号，使括号能对应，并返回所有可能得答案
 */
public class 删除无效的括号 {

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("()(()()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")("));
    }

    // 删除无效的括号
    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);
        boolean flag = false;

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (isValid(cur)) {
                res.add(cur);
                flag = true;
            }
            if (flag) { // 删除一位达到合法时，永远变成true无需再进行删除，只需要找出所有答案
                continue;
            }

            for (int i = 0; i < cur.length(); i++) {
                // 只删除括号，非括号跳过
                if (cur.charAt(i) != '(' && cur.charAt(i) != ')') continue;
                String next = cur.substring(0, i) + cur.substring(i + 1);
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        return res;
    }

    private static boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}
