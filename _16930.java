import java.io.*;
import java.util.*;

public class Main {
    static String[][] map;

    static int N,M,K;
    static Queue<int[]> q = new LinkedList<>();

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[][] minCheck;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new String[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = line.substring(j -1, j);
            }
        }

        minCheck = new int[N + 1][M + 1];

        st = new StringTokenizer(br.readLine());
        int strX = Integer.parseInt(st.nextToken());
        int strY = Integer.parseInt(st.nextToken());
        int endX = Integer.parseInt(st.nextToken());
        int endY = Integer.parseInt(st.nextToken());

        int answer = Integer.MAX_VALUE;

        q.add(new int[]{strX, strY});

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (i == strX && j == strY) continue;
                minCheck[i][j] = Integer.MAX_VALUE;
            }
        }

        while (!q.isEmpty()) {
            int[] direction = q.poll();
            int x = direction[0];
            int y = direction[1];

            // 종료조건
            if (x == endX && y == endY) {
                answer = Math.min(answer, minCheck[x][y]);
                break;
            }

            move(x, y, minCheck[x][y]);
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        System.out.println(answer);
    }

    static void move(int x, int y, int time) {

        // 방향 설정
        for (int i = 0; i < 4; i++) {
            int nxtX = x;
            int nxtY = y;

            for (int j = 0; j < K; j++) {
                nxtX += dx[i];
                nxtY += dy[i];

                // 맵 범위 체크
                if ( check(nxtX, nxtY)) {
                    if ("#".equals(map[nxtX][nxtY])) {
                        break;
                    }

                    // minCheck 위치 체크
                    if (minCheck[nxtX][nxtY] > time) {
                        minCheck[nxtX][nxtY] = time + 1;
                        q.add(new int[]{nxtX, nxtY});
                    }
                }
            }
        }

    }

    static boolean check(int nxtX, int nxtY) {
        return nxtX > 0 && nxtY > 0 && nxtX <= N && nxtY <= M;
    }
}
