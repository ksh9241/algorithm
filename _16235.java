import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // N == , M == 상도가 심은 나무 정보, K == 종료 연도

    static int N, M, K;
    static int[][] land;
    static int[][] A;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static Queue<Tree> trees = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        land = new int[N + 1][N + 1];
        A = new int[N + 1][N + 1];
        // 양분 정보 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i + 1][j + 1] = Integer.parseInt(st.nextToken());
                land[i + 1][j + 1] = 5; // S2D2의 양분 초기값은 5씩 줘야됨.
            }
        }


        // 나무 정보 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            trees.add(new Tree(x, y, year));
        }

        for (int i = 0; i < K; i++) {
            List<Tree> tmp = new ArrayList<>();
            List<Tree> food = new ArrayList<>();

            while (!trees.isEmpty()) {
                Tree tree = trees.poll();

                if (land[tree.x][tree.y] - tree.age < 0) {
                    food.add(new Tree(tree.x, tree.y, tree.age));
                } else {
                    tmp.add(new Tree(tree.x, tree.y, tree.age + 1));
                    land[tree.x][tree.y] -= tree.age;
                }
            }

            for (Tree tree : food) {
                land[tree.x][tree.y] += tree.age / 2;
            }

            for (Tree tree : tmp) {
                trees.add(tree);

                if (tree.age % 5 == 0) {
                    breeding(tree.x, tree.y);
                }
            }

            winter();
        }

        int answer = trees.size();

        System.out.println(answer);
    }

    static class Tree implements Comparable<Tree>{
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
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

            trees.add(new Tree(nxtX, nxtY, 1));
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
