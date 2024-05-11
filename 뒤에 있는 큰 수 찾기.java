import java.util.*;

class Solution {
    int[] answer;
    Stack<Integer> stack = new Stack<>();
    
    public int[] solution(int[] numbers) {
        answer = new int[numbers.length];
        
        for (int i = numbers.length - 1; i >= 0; i--) {
            check(i, numbers[i]);
        }
        
        return answer;
    }
    
    public void check (int idx, int checkNum) {
        
        if (stack.isEmpty()) {
            answer[idx] = -1;
            stack.add(checkNum);
        } else {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                
                if (stack.peek() > checkNum) {
                    answer[idx] = stack.peek();
                    stack.add(checkNum);
                    return;
                } 
                
                stack.pop();
            }
            
            answer[idx] = -1;
            stack.add(checkNum);
        }
    }
}
