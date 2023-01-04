import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o1 - o2;
        });
        
        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        int answer = 0;
        while (true) {
            if (pq.size() < 2) {
                if (pq.poll() < K) {
                    answer = -1;
                }
                break;
            }
            
            int first = pq.poll();
            int second = pq.poll();
            if (first >= K) {
                break;
            }
            
            int num = first + (second * 2);
            pq.add(num);
            answer++;
        }
        
        
        return answer;
    }
}
