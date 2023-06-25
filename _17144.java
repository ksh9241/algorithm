import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int r; // 행
    static int c; // 열
    static int t; // 초
    static int[][] map;
    static boolean[][] visit;

    static int[][] cleaner = {{-1, -1}, {-1, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];


        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (cleaner[0][0] < 0) {
                        cleaner[0][0] = i;
                        cleaner[0][1] = j;
                    } else {
                        cleaner[1][0] = i;
                        cleaner[1][1] = j;
                    }
                }
            }
        }

        for (int i = 0; i < t; i++) {
            visit = new boolean[r][c];
            // 먼지들 체크
            check();

            // 증식
            multiplication();

            // 공기청정기
            clean();
        }

        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    answer += map[i][j];
                }
            }
        }

        System.out.println(answer);
    }

    private static void clean() {
        // 위로 먼지 이동
        int x = cleaner[0][0];
        int y = cleaner[0][1];

        for (int i = x - 1; i >= 0; i--) {
            if (i + 1 == x) {
                continue;
            }
            map[i + 1][y] = map[i][y];
        }

        for (int i = 1; i < c; i++) {
            map[0][i - 1] = map[0][i];
        }

        for (int i = 1; i <= x; i++) {
            map[i - 1][c - 1] = map[i][c - 1];
        }

        for (int i = c - 2; i > 0; i--) {
            map[x][i + 1] = map[x][i];
        }
        map[x][y + 1] = 0;

        // 아래로 먼지 이동
        x = cleaner[1][0];
        y = cleaner[1][1];

        for (int i = x + 2; i < r; i++) {
            map[i - 1][0] = map[i][0];
        }

        for (int i = 1; i < c; i++) {
            map[r - 1][i - 1] = map[r - 1][i];
        }

        for (int i = r - 2; i >= x; i--) {
            map[i + 1][c - 1] = map[i][c - 1];
        }

        for (int i = c - 2; i > 0; i--) {
            map[x][i + 1] = map[x][i];
        }
        map[x][y + 1] = 0;
    }

    private static void multiplication() {
        int[][] plusDust = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visit[i][j]) {
                    calculate(i, j, plusDust);
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += plusDust[i][j];
            }
        }
    }

    private static void calculate(int x, int y, int[][] plusDust) {
        int num = map[x][y] / 5;
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            if (nxtX >= 0 && nxtY >= 0 && nxtX < r && nxtY < c && map[nxtX][nxtY] > -1) {
                plusDust[nxtX][nxtY] += num;
                map[x][y] -= num;
            }
        }
    }

    private static void check () {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    visit[i][j] = true;
                }
            }
        }
    }
}


