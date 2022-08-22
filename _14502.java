import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static boolean[][] visit;
    static int[][] copyArr;

    static int N,M;
    static int virusCount = Integer.MAX_VALUE; // 2개수
    static int answer = 0;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combination(0,0);
        System.out.println(answer);
    }

    static void combination(int x, int depth) {
        if (depth == 3) {
            countCheck();
            return;
        }

        for (int i = x; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visit[i][j]) {
                    visit[i][j] = true;
                    map[i][j] = 1;
                    combination(i, depth + 1);
                    map[i][j] = 0;
                    visit[i][j] = false;
                }
            }
        }
    }

    static void countCheck() {
        int count = 0;
        copyArr = copyArr();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    count = virusChange(i, j, copyArr, count);

                    if (count > virusCount) {
                        break;
                    }
                }

                if (count > virusCount) {
                    break;
                }
            }
        }
        pureCount();
        virusCount = Math.min(virusCount, count);
    }

    static int virusChange (int x, int y, int[][] copyMap, int virusCnt) {
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            if (nxtX > -1 && nxtX < N && nxtY > -1 && nxtY < M && copyMap[nxtX][nxtY] == 0) {
                copyMap[nxtX][nxtY] = 2;
                virusCnt = virusCnt + 1;
                virusCnt = virusChange(nxtX, nxtY, copyMap, virusCnt);
            }
        }
        return virusCnt;
    }

    static void pureCount() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyArr[i][j] == 0) {
                    count++;
                }
            }
        }

        answer = Math.max(answer, count);
    }

    static int[][] copyArr() {
        int[][] copyArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyArr[i][j] = map[i][j];
            }
        }
        return copyArr;
    }
}