import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] chk;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int[][] visit = new int[N][M];
        chk = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = map[i][j];
            }
        }

        quest(0, visit);
        System.out.println(answer);
    }

    static void quest(int x, int[][] visit) {
        for (int i = x; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!chk[i][j] && !(map[i][j] == 0 || map[i][j] == 6)) {
                    chk[i][j] = true;
                    check(i, j, map[i][j], visit);
                    chk[i][j] = false;
                }
            }
        }
        answer(visit);
    }

    static int[][] copyArr(int[][] to, int[][] from) {
        for (int i = 0; i < to.length; i++) {
            for (int j = 0; j < to[i].length; j++) {
                from[i][j] = to[i][j];
            }
        }
        return from;
    }

    static void check(int x, int y, int number, int[][] visit) {
        int[][] copy = new int[N][M];
        // 한방향
        if (number == 1) {
            for (int i = 1; i <= 4; i++) {
                copy = copyArr(visit, copy);
                quest(x, questType(x, y, visit, i));
                visit = copyArr(copy, visit);
            }
        }
        // 양쪽
        else if (number == 2) {
            for (int i = 1; i <= 2; i++) {
                copy = copyArr(visit, copy);
                quest(x, questType(x, y, visit, i, i + 2));
                visit = copyArr(copy, visit);
            }

        }
        // ㄱ자
        else if (number == 3) {
            for (int i = 1; i <= 4; i++) {
                int nxtNum = (i % 4) + 1;
                copy = copyArr(visit, copy);
                quest(x, questType(x, y, visit, i, nxtNum));
                visit = copyArr(copy, visit);
            }
        }
        // 3방향
        else if (number == 4) {
            for (int i = 1; i <= 4; i++) {
                int nxtNum = (i % 4) + 1;
                int nxtNum2 = i + 2 > 4 ? i + 2 - 4 : i + 2;
                copy = copyArr(visit, copy);
                quest(x, questType(x, y, visit, i, nxtNum, nxtNum2));
                visit = copyArr(copy, visit);
            }
        }
        // 십자
        else if (number == 5) {
            quest(x, questType(x, y, visit, 1, 2, 3, 4));
        }
    }

    // 방향 전체 탐색 : 1 = 왼쪽, 2 = 위쪽, 3 = 오른쪽, 4 = 아래
    static int[][] questType(int x, int y, int[][] visit, int... type) {
        for (int i = 0; i < type.length; i++) {
            // 왼쪽
            if (type[i] == 1) {
                if (y > 0) {
                    for (int j = y - 1; j >= 0; j--) {
                        if (map[x][j] == 6) {
                            break;
                        }
                        if (map[x][j] == 0) {
                            visit[x][j] = -1;
                        }
                    }
                }
            }
            // 위쪽
            if (type[i] == 2) {
                if (x > 0) {
                    for (int j = x - 1; j >= 0; j--) {
                        if (map[j][y] == 6) {
                            break;
                        }
                        if (map[j][y] == 0) {
                            visit[j][y] = -1;
                        }
                    }
                }
            }
            // 오른쪽
            if (type[i] == 3) {
                if (y < M - 1) {
                    for (int j = y + 1; j < M; j++) {
                        if (map[x][j] == 6) {
                            break;
                        }
                        if (map[x][j] == 0) {
                            visit[x][j] = -1;
                        }
                    }
                }
            }
            // 아래
            if (type[i] == 4) {
                if (x < N - 1) {
                    for (int j = x + 1; j < N; j++) {
                        if (map[j][y] == 6) {
                            break;
                        }
                        if (map[j][y] == 0) {
                            visit[j][y] = -1;
                        }
                    }
                }
            }
        }
        return visit;
    }

    // 루프 끝나면 최소값 비교
    private static void answer(int[][] visit) {
        int count = 0;
        for (int i = 0; i < visit.length; i++) {
            for (int j = 0; j < visit[i].length; j++) {
                if (visit[i][j] == 0) {
                    count++;
                }
            }
        }
        answer = Math.min(count, answer);
    }
}