import java.util.*;

class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    Queue<int[]> q = new LinkedList<>();
    
    public int solution(int[][] maps) {
        int answer = Integer.MAX_VALUE;
        boolean[][] visit = new boolean[maps.length][maps[0].length];
        
        q.add(new int[]{0,0,1});
        visit[0][0] = true;
        
        while (!q.isEmpty()) {
            int[] arr = q.poll();

            int x = arr[0];
            int y = arr[1];
            int depth = arr[2];
            
            if (x == maps.length - 1 && y == maps[0].length - 1) {
                answer = Math.min(answer, depth);
                break;
            }
            
            check(x, y, depth, visit, maps);
        }
        
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        
        return answer;
    }
    
    public void check(int x, int y, int depth, boolean[][] visit, int[][]maps) {
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];
            
            if (nxtX >= 0 && nxtY >= 0 && nxtX < visit.length && nxtY < visit[0].length && !visit[nxtX][nxtY] && maps[nxtX][nxtY] == 1) {
                visit[nxtX][nxtY] = true;
                q.add(new int[]{nxtX, nxtY, depth + 1});
            }
        }
    }
}
