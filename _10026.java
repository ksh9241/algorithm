import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static String[][] rgb;
    static boolean[][] visit;
    static int N;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main (String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        rgb = new String[N + 2][N + 2];
        visit = new boolean[N + 2][N + 2];

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= input.length(); j++) {
                rgb[i][j] = input.substring(j - 1, j);
            }
        }

        int colorCount = 0;
        int colorBlindnessCount = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    colorCheck(i, j, rgb[i][j]);
                    colorCount++;
                }
            }
        }

        visit = new boolean[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visit[i][j]) {
                    visit[i][j] = true;
                    colorBlindnessCheck(i, j, rgb[i][j]);
                    colorBlindnessCount++;
                }
            }
        }

        System.out.println(colorCount + " " + colorBlindnessCount);
    }

    static boolean check (int x, int y) {
        boolean result = true;
        if (x <= 0 || y <= 0 || x > N || y > N || visit[x][y] == true) {
            result = false;
        }
        return result;
    }

    static void colorCheck(int x, int y, String color) {
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            if (check(nxtX, nxtY) && color.equals(rgb[nxtX][nxtY])) {
                visit[nxtX][nxtY] = true;
                colorCheck(nxtX, nxtY, color);
            }
        }
    }

    static void colorBlindnessCheck(int x, int y, String color) {
        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            // B의 값만 체크
            if (check(nxtX, nxtY) && color.equals("B") && color.equals(rgb[nxtX][nxtY])) {
                visit[nxtX][nxtY] = true;
                colorBlindnessCheck(nxtX, nxtY, color);
            }
            // B외의 값만 체크
            else if (check(nxtX, nxtY) && !color.equals("B") && !"B".equals(rgb[nxtX][nxtY])) {
                visit[nxtX][nxtY] = true;
                colorBlindnessCheck(nxtX, nxtY, rgb[nxtX][nxtY]);
            }
        }
    }
}