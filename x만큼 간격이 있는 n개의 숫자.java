import java.util.*;
class Solution {
    public long[] solution(int x, int n) {
        List<Long> list = new ArrayList<>();
    
        long num = 0;
        for (int i = 0; i < n; i++) {
            num += (long) x;
            list.add(num);
        }
        
        long[] answer = new long[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
