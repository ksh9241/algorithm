import java.util.*;
class Solution
{
    public int solution(String s)
    {
        
        Stack<Character> stack = new Stack<>();
        stack.add(s.charAt(0));
        
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty()) {
                // 값이 같을 경우
                if (stack.peek() == c) {
                    stack.pop();
                } else { // 값이 다를 경우
                    stack.add(c);
                }
            } else {
                stack.add(c);
            }
        }
        
        int answer = 0;
        if (stack.isEmpty()) {
            answer = 1;
        }
        

        return answer;
    }
}
