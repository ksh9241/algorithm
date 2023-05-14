import java.util.*;
class Solution {
    int[] grid = new int[1_000_001];
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;
        for (int i = 0; i < enemy.length; i++) {
            pq.add(enemy[i]);
            if (n - enemy[i] < 0) {
                if (k > 0) {
                    n += pq.poll();
                    k--;
                } else {
                    break;
                }
            }

            n -= enemy[i];
            answer++;
        }
        return answer;
    }
}
