import java.util.*;
class Solution {
    int[] answer;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    public int[] solution(String[][] places) {
        answer = new int[places.length];

        for (int k = 0; k < places.length; k++) {
            String[][] map = new String[places[k].length][places[k][0].length()];

            for (int i = 0; i < places[k].length; i++) {
                for (int j = 0; j < places[k][i].length(); j++) {
                    map[i][j] = places[k][i].substring(j, j + 1);
                }
            }

            answer[k] = check(map);
        }

        return answer;
    }

    public int check(String[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if ( "P".equals(map[i][j]) ) {
                    if (!quest(i, j, map)) {
                        return 0;
                    }
                }
            }
        }

        return 1;
    }

    // 탐색 과정에서 X일경우 파티션
    // O 일 경우 맨허튼거리 탐색
    /*
    * 1. 거리가 2보다 크다면 거리두기 성공
      2. 거리가 2보다 작다면 거리두기 실패
      3. 거리가 2일 경우에는 다시 3가지 조건을 확인한다
        3-1. 가로로 한칸 띄어져 있는 경우 => r1 == r2, [c2 - 1]이 사이 값(c1 < c2)
        3-2. 세로로 한칸 띄어져 있는 경우 => c1 == c2, [r2 - 1]이 사이 값(r1 < r2)
        3-3. 대각선으로 한칸 띄어져 있는 경우 => [r1][c2] , [r2][c1]이 대각선 좌표옆 두개의 값
    * */
    public boolean quest(int x, int y, String[][] map) {
        boolean result = true;

        for (int i = 0; i < 4; i++) {
            int nxtX = x + dx[i];
            int nxtY = y + dy[i];

            // 범위 초과했을 땐 패스
            if (nxtX < 0 || nxtY < 0 || nxtX > map.length - 1 || nxtY > map[0].length - 1) {
                continue;
            }

            // 맨허튼
            if ("O".equals(map[nxtX][nxtY])) {
                for (int j = 0; j < 4; j++) {
                    int lastX = nxtX + dx[j];
                    int lastY = nxtY + dy[j];

                    if ((x == lastX && y == lastY) || lastX < 0 || lastY < 0 || lastX > map.length - 1 || lastY > map[0].length - 1) continue;

                    if ("P".equals(map[lastX][lastY])) {
                        result = false;
                        break;
                    }

                }
            }
            else if ("P".equals(map[nxtX][nxtY])) {
                result = false;
            }

            if (!result) break;
        }

        return result;
    }
}
