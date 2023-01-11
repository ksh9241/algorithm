class Solution {
    int[][] map;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int answer = Integer.MAX_VALUE;
    int N,M;
    public int solution(int n, int m, int[][] hole) {
        map = new int[m + 2][n + 2];
        boolean[][] visit = new boolean[m + 2][n + 2];
        N = n;
        M = m;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = 100001;
            }
        }

        // 지뢰 설치
        for (int i = 0; i < hole.length; i++) {
            map[hole[i][1]][hole[i][0]] = -1;
        }
        
        dfs(1, 1, true, visit, map, 0);
        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        return answer;
    }

    void dfs(int x, int y, boolean shoes, boolean[][] v, int[][] map, int count) {
        if (x == M && y == N) {
            answer = Math.min(answer, count);
        }

        if (shoes) {
            for (int i = 0; i < 4; i++) {
                int nxtX = x + dx[i] + dx[i];
                int nxtY = y + dy[i] + dy[i];
                if (nxtX <= M && nxtY <= N && nxtX > 0 && nxtY > 0 && map[nxtX][nxtY] > 0 && !v[nxtX][nxtY]) {
                    v[nxtX][nxtY] = true;
                    dfs(nxtX, nxtY, false, v, map, count + 1);
                    v[nxtX][nxtY] = false;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];
            if (!v[nxtX][nxtY] && nxtX <= M && nxtY <= N && nxtX > 0 && nxtY > 0 && map[nxtX][nxtY] > 0) {
                
                v[nxtX][nxtY] = true;
                dfs(nxtX, nxtY, shoes, v, map, count + 1);
                v[nxtX][nxtY] = false;
            }
        }
    }
}
