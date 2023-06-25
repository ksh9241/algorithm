//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class Main {
//
//    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
//    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
//    static boolean[][] visit = new boolean[4][4];
//    static int answer = Integer.MIN_VALUE;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        Fish[][] map = new Fish[4][4];
//
//        visit[0][0] = true; // 상어가 먹은 곳
//
//        for (int i = 0; i < 4; i++) {
//            st = new StringTokenizer(br.readLine());
//
//            for (int j = 0; j < 4; j++) {
//                int num = Integer.parseInt(st.nextToken());
//                int direction = Integer.parseInt(st.nextToken()) - 1;
//                Fish f = new Fish(num, direction);
//                map[i][j] = f;
//            }
//        }
//
//
//
//        Fish shark = map[0][0];
//        quest (0, 0, map, visit, 0, shark.direction);
//
//        System.out.println(answer);
//    }
//
//    private static void info (Fish[][] map, boolean[][] visit) {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (!visit[i][j]) {
//                    System.out.print(map[i][j].num + print(map[i][j].direction) + " ");
//                }
//                else {
//                    System.out.print("먹힘 ");
//                }
//            }
//            System.out.println();
//        }
//    }
//
//    private static void quest(int x, int y, Fish[][]map, boolean[][] visit, int score, int shark) {
//        answer = Math.max(answer, score);
//
//        // 배열 깊은 복사
//        boolean[][] copyVisit = new boolean[visit.length][visit.length];
//        for (int i = 0; i < visit.length; i++) {
//            System.arraycopy(visit[i], 0, copyVisit[i], 0, visit.length);
//        }
//
//        Fish[][] copyFish = new Fish[map.length][map.length];
//        for (int i = 0; i < map.length; i++) {
//            System.arraycopy(map[i], 0, copyFish[i], 0, map.length);
//        }
//
//        // 물고기 이동
//        shuffle(copyFish, copyVisit, new int[]{0,0});
//
//        // 상어가 이동할 백트래킹 구현
//        boolean shuffleCheck = true;
//        // 4 x 4 최대 3회만 반복
//        int nxtX = x;
//        int nxtY = y;
//        for (int i = 0; i < 3; i++) {
//            nxtX += dx[shark];
//            nxtY += dy[shark];
//
//            if (check(nxtX, nxtY, visit)) {
//                visit[nxtX][nxtY] = true;
//                quest(nxtX, nxtY, copyFish, copyVisit, score + getScore(nxtX, nxtY, map), map[nxtX][nxtY].direction);
//                visit[nxtX][nxtY] = false;
//            }
//        }
//    }
//
//    private static int getScore(int x, int y, Fish[][] map) {
//        return map[x][y].num;
//    }
//
//    private static void shuffle(Fish[][] map, boolean[][] visit, int[] sharkIdx) {
//        int idx = 1;
//        while (idx <= 16) {
//            boolean v = false;
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    Fish f = map[i][j];
//                    if (f.num == idx) {
//                        if ( !(i == sharkIdx[0] && j == sharkIdx[1]) ) {
//                            process(i, j, map, visit);
//                        }
//                        idx++;
//                        v = true;
//                    }
//
//                    if (v) {
//                        break;
//                    }
//                }
//                if (v) {
//                    break;
//                }
//            }
//        }
//    }
//
//    private static void process(int x, int y, Fish[][] map, boolean[][] visit) {
//        Fish f = map[x][y];
//
//        for (int i = 0; i < dx.length; i++) {
//            int nxtX = dx[(f.direction + i) % 8] + x;
//            int nxtY = dy[(f.direction + i) % 8] + y;
//
//            if (check(nxtX, nxtY, visit)) {
//                Fish to = map[nxtX][nxtY];
//                map[x][y] = to;
//                f.direction = (f.direction + i) % 8;
//                map[nxtX][nxtY] = f;
//
//                boolean tmp = visit[x][y];
//                visit[x][y] = visit[nxtX][nxtY];
//                visit[nxtX][nxtY] = tmp;
//                break;
//            }
//        }
//    }
//
//    private static boolean check (int x, int y, boolean[][] visit) {
//        return x < 4 && y < 4 && x >= 0 && y >= 0 && !visit[x][y];
//    }
//
//    private static String print(int num) {
//        switch(num) {
//            case 0 :
//                return "↑";
//            case 1 :
//                return "↖";
//            case 2 :
//                return "←";
//            case 3 :
//                return "↙";
//            case 4 :
//                return "↓";
//            case 5 :
//                return "↘";
//            case 6 :
//                return "→";
//            case 7 :
//                return "↗";
//        }
//        return null;
//    }
//
//}
//
///*
//* 1번부터
//* ↑, ↖, ←, ↙, ↓, ↘, →, ↗
// * */
//class Fish {
//    int num;
//    int direction;
//
//    public Fish(int num, int direction) {
//        this.num = num;
//        this.direction = direction;
//    }
//}

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


