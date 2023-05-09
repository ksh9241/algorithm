import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1};
    static int[] dy = {-1, 0, 1};
    public static void main(String[] args) throws IOException {

        Solution solution = new Solution();
//        String[] answer = solution.solution(new String[]{"EEEEE", "EEMEE", "EEEEE", "EEEEE"}, 2,0);
        String[] answer = solution.solution(new String[]{"MME", "EEE", "EME"}, 0,0);

        for (String s : answer) {
            System.out.println(s);
        }
    }

    static class Solution {
        Queue<Map> q = new LinkedList<>();
        public String[] solution(String[] arr, int n, int k) { // n == 세로, k == 가로
            String[][] map = new String[arr.length + 2][arr[0].length() + 2];

            for (int i = 0; i < arr.length; i++) {
                String landmine = arr[i];

                for (int j = 0; j < landmine.length(); j++) {
                    map[i + 1][j + 1] = landmine.substring(j, j + 1);
                }
            }

            if ("M".equals(map[n + 1][k + 1])) {
                map[n + 1][k + 1] = "X";
            } else {
                q.add(new Map(map[n + 1][k + 1], n + 1, k + 1));

                while(!q.isEmpty()) {
                    Map m = q.poll();
                    quest(map, m);
                }

                for (int i = 1; i < map.length; i++) {
                    for (int j = 1; j < map[0].length; j++) {
                        if ("M".equals(map[i][j])) {
                            map[i][j] = "E";
                        }
                    }
                }
            }


            String[] answer = new String[arr.length];
            for (int i = 1; i < map.length - 1; i++) {
                String val = "";
                for (int j = 1; j < map[0].length - 1; j++) {
                    val += map[i][j];
                }
                answer[i - 1] = val;
            }

            return answer;
        }

        public void quest(String[][] map, Map m) {
            int landMineCount = 0;

            // 지뢰가 8방향에 있을경우 숫자 값 입력
            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < dx.length; i++) {
                for (int j = 0; j < dy.length; j++) {
                    if (i == 1 && j == 1) continue; // 현재 자기 위치는 탐색 x

                    int nxtX = m.x + dx[i];
                    int nxtY = m.y + dy[j];
                    if (map[nxtX][nxtY] == null) continue;

                    if ("M".equals(map[nxtX][nxtY])) {
                        landMineCount++;
                    }
                    if ("E".equals(map[nxtX][nxtY])) {
                        list.add(new int[]{nxtX, nxtY});
                    }
                }
            }

            // 지뢰가 8방향에 없을경우 B값 입력
            if (landMineCount == 0) {
                map[m.x][m.y] = "B";

                for (int i = 0; i < list.size(); i++) {
                    int nxtX = list.get(i)[0];
                    int nxtY = list.get(i)[1];

                    q.add(new Map(map[nxtX][nxtY], nxtX, nxtY));
                }
            }

            if (landMineCount > 0) {
                map[m.x][m.y] = "" + landMineCount;
            }
        }

        public class Map {
            public Map(String type, int x, int y) {
                this.type = type;
                this.x = x;
                this.y = y;
            }

            String type;
            int x;
            int y;
        }
    }
}
