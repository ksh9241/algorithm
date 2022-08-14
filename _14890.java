import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] map;
    static boolean[][] vCheckMap;
    static boolean[][] wCheckMap;

    static int count = 0;

    /**
     * @Description
     * N : 맵 크기
     * L : 경사로를 만들 때 필요한 블럭 수 (경사로는 무조건 한칸 차이 이내일 때만 가능)
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N + 2][N + 2];
        vCheckMap = new boolean[N + 2][N + 2];
        wCheckMap = new boolean[N + 2][N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        quest();
        System.out.println(count);
    }

    // i, j 가 동일할 땐 가로, 세로 모두 탐색 그 외엔 i = 1 (세로), j = 1 (가로) 탐색
    static void quest() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 || j == 1) {
                    check(i, j);
                }
            }
        }
    }

    static void check(int i, int j) {
        boolean flag = false;
        // 세로 탐색
        if (i == 1) {
            flag = true;
            for (int k = 1; k < N ; k++) {
                if (checkVertical(k, j)) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) count++;

        flag = false;
        // 가로 탐색
        if (j == 1) {
            flag = true;
            for (int k = 1; k < N ; k++) {
                if (checkWidth(i, k)) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) count++;
    }

    // 세로 체크
    static boolean checkVertical (int i, int j) {
        boolean result = true;
        int val = map[i][j];
        int nxtVal = map[i + 1][j];

        if (val == nxtVal) {
            result = false;
        }
        // 다음 값이 한칸 클 경우
        else if (val + 1 == nxtVal) {
            boolean f = true;
            for (int k = i; k > i - L; k--) {
                if (k < 1 || vCheckMap[k][j] || map[k][j] != val) {
                    f = false;
                    break;
                }
            }

            if (f) {
                for (int k = i; k > i - L; k--) {
                    vCheckMap[k][j] = true;
                }
                result = false;
            }
        }
        // 다음 값이 한칸 작을 경우
        else  if (val - 1 == nxtVal) {
            boolean f = true;
            for (int k = i + 1; k <= i + L; k++) {
                if (k > N || vCheckMap[k][j] || map[k][j] != nxtVal) {
                    f = false;
                    break;
                }
            }

            if (f) {
                for (int k = i + 1; k <= i + L; k++) {
                    vCheckMap[k][j] = true;
                }
                result = false;
            }
        }

        return result;
    }

    // 가로 체크
    static boolean checkWidth (int i, int j) {
        boolean result = true;
        int val = map[i][j];
        int nxtVal = map[i][j + 1];

        if (val == nxtVal) {
            result = false;
        }
        // 다음 값이 한칸 클 경우
        else if (val + 1 == nxtVal) {
            boolean f = true;
            for (int k = j; k > j - L; k--) {
                if (k < 1 || wCheckMap[i][k] || map[i][k] != val) {
                    f = false;
                    break;
                }
            }

            if (f) {
                for (int k = j; k > j - L; k--) {
                    wCheckMap[i][k] = true;
                }
                result = false;
            }
        }
        // 다음 값이 한칸 작을 경우
        else  if (val - 1 == nxtVal) {
            boolean f = true;
            for (int k = j + 1; k <= j + L; k++) {
                if (k > N || wCheckMap[i][k] || map[i][k] != nxtVal) {
                    f = false;
                    break;
                }
            }

            if (f) {
                for (int k = j + 1; k <= j + L; k++) {
                    wCheckMap[i][k] = true;
                }
                result = false;
            }
        }

        return result;
    }
}