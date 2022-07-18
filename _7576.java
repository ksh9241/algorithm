import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] days;
    static int M,N;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static Queue<int[]> q;

    public static void main (String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N + 2][M + 2];
        days = new int[N + 2][M + 2];
        q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == 1) {
                   q.add(new int[]{i,j});
                   days[i][j] = 1;
                }
            }
        }
        bfs();
        int max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            if (flag) break;
            for (int j = 1; j <= M; j++) {
                if (days[i][j] == 0 && map[i][j] != -1) {
                    max = -1;
                    flag = true;
                    break;
                }else {
                    max = Math.max(max, days[i][j]);
                }
            }
        }
        max = max == -1 ? -1 : max - 1;
        System.out.println(max);
    }

    static void bfs () {
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            check(tmp[0], tmp[1]);
        }
    }

    static void check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            if (nxtX < 1 || nxtY < 1 || nxtX > N || nxtY > M || map[nxtX][nxtY] == -1 || days[nxtX][nxtY] != 0) {
                continue;
            }
            else {
                q.add(new int[]{nxtX,nxtY});
                days[nxtX][nxtY] = days[x][y] + 1;
            }
        }
    }
}