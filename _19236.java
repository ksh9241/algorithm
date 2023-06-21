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

        shuffle(map, visit, new int[]{0,0});

        Fish shark = map[0][0];
        // 밥먹을 시간 최초 인입 경우의 수 최대 3개밖에 없음.
        for (int i = 0; i < 3; i++) {
            int nxtX = 0;
            int nxtY = 0;

            nxtX += dx[shark.direction];
            nxtY += dy[shark.direction];
            System.out.println("시작");
            info(map, visit);
            System.out.println();
            quest (nxtX, nxtY, map.clone(), visit.clone(), 0, shark.direction);
        }

        System.out.println(answer);
    }

    private static void info (Fish[][] map, boolean[][] visit) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!visit[i][j]) {
                    System.out.print(map[i][j].num + print(map[i][j].direction) + " ");
                }
                else {
                    System.out.print("먹힘 ");
                }
            }
            System.out.println();
        }
    }

    private static void quest(int x, int y, Fish[][]map, boolean[][] visit, int score, int shark) {
        // 상어가 이동할 백트래킹 구현
        boolean shuffleCheck = true;
        // 4 x 4 최대 3회만 반복
        int nxtX = x;
        int nxtY = y;
        for (int i = 0; i < 3; i++) {
            nxtX += dx[shark];
            nxtY += dy[shark];

            if (check(nxtX, nxtY, visit)) {
                shuffleCheck = false;
                visit[nxtX][nxtY] = true;
                quest(nxtX, nxtY, map, visit, score + getScore(nxtX, nxtY, map), map[nxtX][nxtY].direction);
                visit[nxtX][nxtY] = false;
            }
        }

        // 상어가 먹을 곳이 없을 때 다시 셔플
        if (shuffleCheck) {
            // 셔플 후 먹을 곳 있을때만 다시 탐색
            shuffle(map, visit, new int[]{x, y});

            boolean questCheck = false;
            int chkX = x;
            int chkY = y;
            for (int i = 0; i < 3; i++) {
                chkX += dx[shark];
                chkY += dy[shark];
                if (check(chkX, chkY, visit)) {
                    questCheck = true;
                    break;
                }
            }
            if (questCheck) {
                quest(x, y, map, visit, score, shark);
            } else {

                answer = Math.max(score, answer);
                return;
            }
        }
    }

    private static int getScore(int x, int y, Fish[][] map) {
        return map[x][y].num;
    }

    private static void shuffle(Fish[][] map, boolean[][] visit, int[] sharkIdx) {
        int idx = 1;
        while (idx <= 16) {
            boolean v = false;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Fish f = map[i][j];
                    if (f.num == idx) {
                        if ( !(i == sharkIdx[0] && j == sharkIdx[1]) ) {
                            process(i, j, map, visit);
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

    private static void process(int x, int y, Fish[][] map, boolean[][] visit) {
        Fish f = map[x][y];

        for (int i = 0; i < dx.length; i++) {
            int nxtX = dx[(f.direction + i) % 8] + x;
            int nxtY = dy[(f.direction + i) % 8] + y;

            if (check(nxtX, nxtY, visit)) {
                Fish to = map[nxtX][nxtY];
                map[x][y] = to;
                f.direction = (f.direction + i) % 8;
                map[nxtX][nxtY] = f;

                boolean tmp = visit[x][y];
                visit[x][y] = visit[nxtX][nxtY];
                visit[nxtX][nxtY] = tmp;
                break;
            }
        }
    }

    private static boolean check (int x, int y, boolean[][] visit) {
        return x < 4 && y < 4 && x >= 0 && y >= 0 && !visit[x][y];
    }

    private static String print(int num) {
        switch(num) {
            case 0 :
                return "↑";
            case 1 :
                return "↖";
            case 2 :
                return "←";
            case 3 :
                return "↙";
            case 4 :
                return "↓";
            case 5 :
                return "↘";
            case 6 :
                return "→";
            case 7 :
                return "↗";
        }
        return null;
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
