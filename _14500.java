import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int ANSWER = Integer.MIN_VALUE;
    static List<Location> list = new ArrayList<>();
    static int N,M;
    static int[][] MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                check(i, j);
            }
        }

        System.out.println(ANSWER);
    }


    public static void check(int x, int y) {
        for (int i = 0; i < list.size(); i++) {
            Location location = list.get(i);
            boolean visit = true;
            int value = 0;

            for (int j = 0; j < location.x.length; j++) {
                int nxtX = x + location.x[j];
                int nxtY = y + location.y[j];

                if (nxtX < 0 || nxtY < 0 || nxtX >= N || nxtY >= M) {
                    visit = false;
                    break;
                }

                value += MAP[nxtX][nxtY];
            }

            if (visit) {
                ANSWER = Math.max(ANSWER, value);
            }
        }
    }

    static class Location {
        int[] x;
        int[] y;

        public Location(int[] x, int[] y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void init() {
        // ㄱ자
        list.add(new Location(new int[]{0,1,2,2}, new int[]{0,0,0,1}));
        list.add(new Location(new int[]{0,0,0,-1}, new int[]{0,1,2,2}));
        list.add(new Location(new int[]{0,-1,-2,-2}, new int[]{0,0,0,-1}));
        list.add(new Location(new int[]{0,0,0,1}, new int[]{0,-1,-2,-2}));

        list.add(new Location(new int[]{0,1,2,2}, new int[]{0,0,0,-1}));
        list.add(new Location(new int[]{0,0,0,1}, new int[]{0,1,2,2}));
        list.add(new Location(new int[]{0,-1,-2,-2}, new int[]{0,0,0,1}));
        list.add(new Location(new int[]{0,0,0,-1}, new int[]{0,-1,-2,-2}));

        // 일자
        list.add(new Location(new int[]{0,0,0,0}, new int[]{0,1,2,3}));
        list.add(new Location(new int[]{0,1,2,3}, new int[]{0,0,0,0}));

        // 네모
        list.add(new Location(new int[]{0,0,1,1}, new int[]{0,1,0,1}));

        // N자
        list.add(new Location(new int[]{0,1,1,2}, new int[]{0,0,1,1}));
        list.add(new Location(new int[]{0,0,-1,-1}, new int[]{0,1,1,2}));
        list.add(new Location(new int[]{0,1,1,2}, new int[]{0,0,-1,-1}));
        list.add(new Location(new int[]{0,0,1,1}, new int[]{0,1,1,2}));

        // T자
        list.add(new Location(new int[]{0,0,0,1}, new int[]{0,1,2,1}));
        list.add(new Location(new int[]{0,-1,-2,-1}, new int[]{0,0,0,1}));
        list.add(new Location(new int[]{0,0,0,-1}, new int[]{0,-1,-2,-1}));
        list.add(new Location(new int[]{0,1,2,1}, new int[]{0,0,0,-1}));
    }
}

