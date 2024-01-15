import java.util.*;

class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    
    public int solution(int[][] land) {
        int answer = 0;
        
        // 시추관을 설치할 y값
        for (int i = 0; i < land[0].length; i++) {
            int totalCount = 0;
            boolean[][] visit = new boolean[land.length][land[0].length];
            
            // 시추관 작업
            for (int j = 0; j < land.length; j++) {
                if (land[j][i] == 1 && !visit[j][i]) {
                    totalCount += quest(land, visit, new int[]{j, i}); 
                }
            }
            
            // 최대값 설정
            answer = Math.max(answer, totalCount);
        }
        
        return answer; 
    }
    
    public int quest(int[][] land, boolean[][] visit, int[] idx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(idx);

        int result = 0;
        
        if (visit[idx[0]][idx[1]]) {
            return result;
        }

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];
            
            if (visit[x][y]) {
                continue;
            }
            
            visit[x][y] = true;
            result++;

            for (int i = 0; i < 4; i++) {
                int nxtX = x + dx[i];
                int nxtY = y + dy[i];

                if (nxtX >= 0 && nxtY >= 0 && nxtX < land.length && nxtY < land[0].length && !visit[nxtX][nxtY] && land[nxtX][nxtY] == 1) {
                    q.add(new int[]{nxtX, nxtY});
                }
            }
        }    
        return result;
    }
    
}
