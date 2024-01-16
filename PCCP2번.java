import java.util.*;

class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    
    boolean[][] visit; 
    int[][] oilLand;
    public int solution(int[][] land) {
        int answer = 0;
        visit = new boolean[land.length][land[0].length];
        oilLand = new int[land.length][land[0].length];

        Map<Integer, Integer> oil = new HashMap<>();
        int oilId = 1;
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] == 1 && !visit[i][j]) {
                    int size = quest(land, new int[]{i, j}, oilId); 
                    oil.put(oilId, size);
                    oilId++;
                }
            }
        }

        int[] arr = new int[land[0].length];
        for (int i = 0; i < land[0].length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < land.length; j++) {
                if (land[j][i] == 1) {
                    set.add(oilLand[j][i]);
                }
            }

            int totalCount = 0;
            for (int k : set) {
                totalCount += oil.get(k);
            }
            arr[i] = totalCount;
        }

        for (int maxNum : arr) {
            answer = Math.max(answer, maxNum);
        }
        
        return answer; 
    }
    
    public int quest(int[][] land, int[] idx, int oilId) {
        Queue<int[]> q = new LinkedList<>();
        q.add(idx);

        int result = 0;

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];

            if (visit[x][y]) {
                continue;
            }

            visit[x][y] = true;
            oilLand[x][y] = oilId;
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
