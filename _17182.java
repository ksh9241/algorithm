import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,K;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        initFloydWarshall();

        visit[K] = true;
        dfs(K, 0, 0);

        System.out.println(answer);
     }

     static void dfs( int start, int cost, int depth) {
        if (depth == N - 1) {
            answer = Math.min(cost, answer);
            return;
        }

         for (int i = 0; i < N; i++) {
             if (!visit[i]) {
                 visit[i] = true;
                 dfs(i, cost + map[start][i], depth + 1);
                 visit[i] = false;
             }
         }
     }

    static void initFloydWarshall() {

        /*
        * 2,3 > 2,1 + 1,3
        * */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }
    }
}
