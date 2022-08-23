import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,H; // N : 세로선, M : 가로선, H : 세로선마다 가로선을 놓을 수 있는 개수
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 사다리 서로 연결
            map[x][y] = y + 1;
            map[x][y + 1] = y;
        }

        quest(1,1,1, 0, map);
        if (answer == Integer.MAX_VALUE || answer > 3) answer = -1;
        System.out.println(answer);

    }

    static void quest(int x, int y, int num, int count, int[][] map) {
        if (count > 3) return;
        if (y == num && x == H + 1) {
            if (y < N) {
                quest(1, y + 1, num + 1, count, map);
            } else { // 끝까지 탐색이 끝났을 경우 최소카운트 비교
                answer = Math.min(count, answer);
                return;
            }
        }
        // 기본 세팅된 가로선이 없다면
        if(x <= H && map[x][y] == 0) {
            quest(x + 1, y,num, count, map);

            // 왼쪽탐색 가능할 때
            if (y - 1 > 0 && map[x][y - 1] == 0) {
                map[x][y - 1] = y;
                map[x][y] = y - 1;
                quest(x + 1, y - 1, num, count + 1, map);
                map[x][y] = 0;
                map[x][y - 1] = 0;
            }

            // 오른탐색 가능할 때
            if (y + 1 <= N && map[x][y + 1] == 0) {
                map[x][y + 1] = y;
                map[x][y] = y + 1;
                quest(x + 1, y + 1, num, count + 1, map);
                map[x][y + 1] = 0;
                map[x][y] = 0;
            }
        } else  if (x <= H && map[x][y] != 0) {
            quest(x + 1, map[x][y], num, count, map);
        }
    }
}