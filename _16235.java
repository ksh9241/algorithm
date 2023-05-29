import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // N == , M == 상도가 심은 나무 정보, K == 종료 연도

    static int N, M, K;
    static int[][] land;
    static Tree[][] tree;
    static int[][] A;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        land = new int[N + 1][N + 1];
        tree = new Tree[N + 1][N + 1];
        A = new int[N + 1][N + 1];
        // 양분 정보 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i + 1][j + 1] = Integer.parseInt(st.nextToken());
                land[i + 1][j + 1] = 5; // S2D2의 양분 초기값은 5씩 줘야됨.
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                tree[i][j] = new Tree();
            }
        }

        // 나무 정보 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            Tree t = tree[x][y];
            t.add(year);
            tree[x][y] = t;
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Tree t = tree[i][j];
                if (t.count()) {
                    answer += t.years.size();
                }
            }
        }

        System.out.println(answer);
    }

    static class Tree {
        List<Integer> years = new ArrayList<>();
        int idx = -1;

        public void add(int year) {
            years.add(year);
            Collections.sort(years);
            idx++;
        }

        public boolean count() {
            return years.size() > 0;
        }

        // 여름에 나무 삭제할 때 처리
        public int actualization() {
            int[] nums = new int[idx + 1];
            // 양분을 먹은 정보는 나이 한살 증가.
            for (int i = 0; i < idx + 1; i++) {
                nums[i] = years.get(i) + 1;
            }

            int food = 0;
            // 양분을 먹지못하고 죽은 나무들은 토탈값 합친 후 양분 추가.
            for (int i = idx + 1; i < years.size(); i++) {
                food += years.get(i) / 2;
            }

            years = new ArrayList<>();
            for (int i : nums) {
                years.add(i);
            }

            return food;
        }

        // 가을에 나무 번식할 때 사용
        public void check(int x, int y) {
            for ( int i = 0; i < years.size(); i++ ) {
                if (years.get(i) % 5 == 0) {
                    breeding(x, y);
                }
            }
        }

        @Override
        public String toString() {
            return years.toString();
        }
    }

    public static void breeding(int x, int y) {
        // 8방향 나무 심기
        for (int i = 0; i < 8; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            if (nxtX < 1 || nxtY < 1 || nxtX > N || nxtY > N) {
                continue;
            }

            Tree t = tree[nxtX][nxtY];
            t.add(1);
            tree[nxtX][nxtY] = t;
        }
    }

    // 나이만큼 양분먹고 나이 + 1
    // 하나의 칸에 여러 개의 나무가 있다면 나이가 어린 나무부터 양분 흡수
    // 양분이 나이보다 부족할 경우 바로 죽는다.
    public static void spring() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 나무가 심어져 있다면
                if ( tree[i][j].count() ) {
                    Tree t = tree[i][j];

                    for (int k = 0; k < t.years.size(); k++) {
                        int num = t.years.get(k);
                        if ( land[i][j] >= num ) {
                            land[i][j] -= num;
                        } else {
                            t.idx--;
                        }
                    }
                }
            }
        }
    }

    // 죽은 나무가 양분으로 변한다.
    // 죽은 나무마다 나이 / 2 양분 추가
    public static void summer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 나무 나이 현행화 및 양분 추가
                if ( tree[i][j].count() ) {
                    Tree t = tree[i][j];

                    land[i][j] += t.actualization();
                }
            }
        }
    }

    // 나무의 나이가 5배수이면 인접한 8개의 칸에 나이 1의 나무가 생긴다.
    public static void autumn() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 나무 번식
                if ( tree[i][j].count() ) {
                    Tree t = tree[i][j];
                    t.check(i, j);
                }
            }
        }
    }

    // 양분을 추가한다. 양분의 양은 A[r][c]
    public static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                land[i][j] += A[i][j];
            }
        }
    }
}



