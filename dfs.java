class Solution {
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int count;

    public int[] solution(int rows, int columns, int[][] lands) {
        arr = new int[rows + 1][columns + 1];
        visit = new boolean[rows + 1][columns + 1];

        // 땅인 부분만 우선 1로 변환
        for (int i = 0; i < lands.length; i++) {
            int x = lands[i][0];
            int y = lands[i][1];
            arr[x][y] = 1;
        }
        
        visit[1][1] = true;
        arr[1][1] = 3;
        seeCheck(1,1,rows,columns);

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (arr[i][j] == 0 && !visit[i][j]) {
                    count = 1;
                    visit[i][j] = true;
                    recursion(i, j, rows, columns);
                    min = Math.min(min, count);
                    max = Math.max(max, count);
                }
            }
        }
        if (min == Integer.MAX_VALUE) min = -1;
        if (max == Integer.MIN_VALUE) max = -1;
        int[] answer = new int[2];
        answer[0] = min;
        answer[1] = max;
        return answer;
    }

    static void recursion(int x, int y, int rowLen, int columnLen) {
         for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            if (check(nxtX, nxtY, rowLen, columnLen)) {
                visit[nxtX][nxtY] = true;
                count++;
                recursion(nxtX, nxtY, rowLen, columnLen);
            }            
        }
    }

    static void seeCheck(int x, int y, int rowLen, int columnLen) {
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            if (check(nxtX, nxtY, rowLen, columnLen)) {
                visit[nxtX][nxtY] = true;
                arr[nxtX][nxtY] = 3; // 바다는 3으로 변경
                seeCheck(nxtX, nxtY, rowLen, columnLen);
            }            
        }
    }

    static boolean check(int x, int y, int rowLen, int columnLen) {
        if (x < 0 || y < 0 || x > rowLen || y > columnLen || arr[x][y] != 0 || visit[x][y]) {
            return false;
        }
        return true;
    }
}