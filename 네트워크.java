import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visit = new boolean[computers.length];
        Queue<int[]> q = new LinkedList<>();
        int answer = 0;
        
        for (int i = 0; i < computers.length; i++) {
            if (visit[i]) {
                continue;
            }
            q.add(computers[i]);
            visit[i] = true;
            
            while(!q.isEmpty()) {
                int[] arr = q.poll();
                for (int j = 0; j < arr.length; j++) {
                    if (!visit[j] && arr[j] == 1) {
                        q.add(computers[j]);
                        visit[j] = true;
                    }
                }
            }
            answer++;
        }
        
        return answer;
    }
}
