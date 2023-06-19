import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static boolean[][] visit = new boolean[4][4];
    static int answer = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Fish[][] map = new Fish[4][4];

        visit[0][0] = true; // 상어가 먹은 곳

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;
                Fish f = new Fish(num, direction);
                map[i][j] = f;
            }
        }

        shuffle(map);

        Fish shark = map[0][0];

        for (Fish[] ff : map) {
            for (Fish f : ff) {
                System.out.print(f.num + " ");
            }
            System.out.println();
        }


        quest(0, 0, map.clone(), 0);
        System.out.println(answer);
    }

    private static void shuffle(Fish[][] map) {
        int idx = 1;
        while (idx <= 16) {
            boolean v = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Fish f = map[i][j];
                    if (f.num == idx) {
                        if ( !visit[i][j] ) {
                            process(i, j, map);
                        }
                        idx++;
                        v = true;
                    }

                    if (v) {
                        break;
                    }
                }
                if (v) {
                    break;
                }
            }
        }
    }

    private static void quest(int x, int y, Fish[][]map, int score) {

    }

    private static void process(int x, int y, Fish[][] map) {
        Fish f = map[x][y];

        for (int i = 0; i < dx.length; i++) {
            int nxtX = dx[(f.direction + i) % 8] + x;
            int nxtY = dy[(f.direction + i) % 8] + y;

            if (nxtX < 4 && nxtY < 4 && nxtX >= 0 && nxtY >= 0 && !visit[nxtX][nxtY]) {
                Fish to = map[nxtX][nxtY];
                map[x][y] = to;
                map[nxtX][nxtY] = f;
                break;
            }
        }
    }

}

/*
* 1번부터
* ↑, ↖, ←, ↙, ↓, ↘, →, ↗
 * */
class Fish {
    int num;
    int direction;

    public Fish(int num, int direction) {
        this.num = num;
        this.direction = direction;
    }
}

