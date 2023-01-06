class Solution {
    String[][] arr;
    boolean[][] visit;
    int[] dx = {0, -1, 0, 1};
    int[] dy = {1, 0, -1, 0};
    public int solution(int m, int n, String[] board) {
        arr = new String[m + 2][n + 2];
        visit = new boolean[m + 2][n + 2];

        for (int i = 0; i < m; i++) {
            String text = board[i];
            for (int j = 0; j < n; j++) {
                arr[i + 1][j + 1] = text.substring(j, j + 1);
            }
        }
        int answer = 0;

        while (true) {
            if (!check()) {
                break;
            }
            answer += reload();
        }

        return answer;
    }

    int reload() {
        int result = 0;
        // 점수 계산
        for (int i = 1; i < visit.length - 1; i++) {
            for (int j = 1; j < visit[0].length - 1; j++) {
                if (visit[i][j]) {
                    result++;
                    arr[i][j] = "";
                    visit[i][j] = false;
                }
            }
        }

        // 블럭세팅
        for (int i = 1; i < arr[0].length - 1; i++) {
            boolean check = false;
            int count = 0;
            for (int j = arr.length - 2; j > 0; j--) {
                if ("".equals(arr[j][i])) {
                    if (!check) {
                        check = true;
                    }
                    count++;
                }
                if (count > 0 && !"".equals(arr[j][i])) {
                    arr[j + count][i] = arr[j][i];
                    arr[j][i] = "";
                    check = false;
                }
            }
        }
        return result;
    }

    boolean check() {
        boolean result = false;
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = 1; j < arr[0].length - 1; j++) {
                // 체크했거나 비어있으면 탐색안하기
                if (visit[i][j] || "".equals(arr[i][j])) continue;

                // 탐색해서 한번이라도 점수낸 대상이 있다면
                if (quest(i, j)) result = true;
            }
        }
        return result;
    }

    boolean quest(int x, int y) {
        boolean answer = false;
        for (int i = 0; i < 4; i++) {
            int j = (i + 1) % 4;

            int nxtX1 = x + dx[i];
            int nxtY1 = y + dy[i];

            int nxtX2 = x + dx[j];
            int nxtY2 = y + dy[j];

            if (arr[x][y].equals(arr[nxtX1][nxtY1])
                    && arr[x][y].equals(arr[nxtX2][nxtY2])) {
                int diagonalX = x + dx[i] + dx[j];
                int diagonalY = y + dy[i] + dy[j];

                if (arr[x][y].equals(arr[diagonalX][diagonalY])) {
                    answer = true;
                    visit[x][y] = true;
                    visit[nxtX1][nxtY1] = true;
                    visit[nxtX2][nxtY2] = true;
                    visit[diagonalX][diagonalY] = true;
                }
            }
        }

        return answer;
    }
}
