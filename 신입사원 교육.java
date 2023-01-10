import java.util.*;
class Solution {
    public int solution(int[] ability, int number) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        Arrays.sort(ability);
        
        for (int i : ability) {
            pq.add(i);
        }
        for (int i = 0; i < number; i++) {
            int totalNumber = pq.poll() + pq.poll();
            pq.add(totalNumber);
            pq.add(totalNumber);
        }
        
        for (int i : pq) {
            answer += i;
        }
        
        return answer;
    }
}
