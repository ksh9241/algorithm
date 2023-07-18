import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<String> stack = new Stack<String>();
        
        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(i, i + 1);
            if ("(".equals(str)) {
                stack.add("(");
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    answer = false;
                    break;
                }
            }
        }
        
        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}
