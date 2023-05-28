class Solution {
    int[][] map;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int answer = Integer.MAX_VALUE;

    // 1 : 아래로 들어온 케이스
    // 2 : 위로 올라온 케이스
    // 3 : 오른쪽으로 이동한 케이스
    // 4 : 왼쪽으로 이동한 케이스
    public int solution(int[][] board) {
        map = new int[board.length][board[0].length];

        dfs(0, 0, 0, 0, board);

        return answer * 100;
    }

    public void dfs(int x, int y, int cost, int direction, int[][] board) {
        // 종료조건
        if (x == map.length - 1 && y == map[0].length - 1) {
            answer = Math.min(answer, cost);
            map[x][y] = answer;
            return;
        }

        // 이미 더 적은 비용이 존재한다면 탐색종료
        if (map[x][y] > 0 && map[x][y] < cost) {
            return;
        } else {
            map[x][y] = cost;
        }

        quest(x, y, cost, direction, board);
    }

    public void quest(int x, int y, int cost, int direction, int[][] board) {
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            // 탐색 범위를 벗어나거나 벽일 경우
            if (nxtX < 0 || nxtY < 0 || nxtX >= map.length || nxtY >= map[0].length || board[nxtX][nxtY] == 1) {
                continue;
            }

            int nextDirection = direction(dx[i], dy[i]);
            if (direction > 0 && direction != nextDirection) {
                dfs(nxtX, nxtY, cost + 6, nextDirection, board);
            } else {
                dfs(nxtX, nxtY, cost + 1, nextDirection, board);
            }
        }
    }

    public int direction(int x, int y) {
        if (x == 0) {
            if (y > 0) {
                return 3;
            } else {
                return 4;
            }
        } else if (x > 0) {
            return 1;
        } else {
            return 2;
        }
    }
}
