import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map = new int[100][100];
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 세로

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
            List<Integer> list = new ArrayList<>();
            list.add(dir);
            check(x, y, type, 0, list);
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) System.out.println(i + " " + j);
            }
        }
    }

    static void quest(int x, int y, int dir) {
        // 오른쪽
        if (dir == 0) {
            map[x][y + 1] = 1;
        }
        // 위쪽
        else if (dir == 1) {
            map[x - 1][y] = 1;
        }
        // 왼쪽
        else if (dir == 2) {
            map[x][y - 1] = 1;
        }
        // 아래
        else if (dir == 3) {
            map[x + 1][y] = 1;
        }
    }

    static void check (int x, int y, int type, int depth, List<Integer>list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            int dir = list.get(i);
            if (depth != 0) dir = (dir + 1) % 4;
            quest(x, y, dir);
            switch (dir) {
                case 0 :
                    y = y + 1;
                    break;
                case 1 :
                    x = x - 1;
                    break;
                case 2 :
                    y = y + 1;
                    break;
                case 3 :
                    x = x + 1;
                    break;
            }
            if (depth != 0) {
                list.add(dir);
            }
        }
        if (type == depth) {
            return;
        }
        check(x, y, type, depth + 1, list);
    }
}