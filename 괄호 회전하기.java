import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;

        int loop = 0;
        while (s.length() > loop) {
            String text = s.substring(0,1);
            s = s.substring(1) + text;
            loop++;
            if (check(s)) {
                answer++;
            }
        }

        return answer;
    }

    boolean check (String s) {
        Stack<String> stack = new Stack<>();
        boolean result = true;
        for (int i = 0; i < s.length(); i++) {
            String text = s.substring(i, i + 1);
            if ("{".equals(text) || "[".equals(text) || "(".equals(text)) {
                stack.push(text);
            }

            if ( (")".equals(text) || "}".equals(text) || "]".equals(text))
                    && stack.isEmpty()
            ) {
                result = false;
                break;
            }
            if (")".equals(text)) {
                String eq = stack.pop();
                if (!"(".equals(eq)) {
                    result = false;
                    break;
                }
            }

            if ("}".equals(text)) {
                String eq = stack.pop();
                if (!"{".equals(eq)) {
                    result = false;
                    break;
                }
            }

            if ("]".equals(text)) {
                String eq = stack.pop();
                if (!"[".equals(eq)) {
                    result = false;
                    break;
                }
            }
        }

        if (stack.size() > 0) {
            result = false;
        }
        return result;
    }
}
