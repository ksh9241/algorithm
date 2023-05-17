import java.util.*;
class Solution {
    int[][] map;
    public int[] solution(int n) {
        map = new int[n][n];

        process(n, "D", 0, 0, 0);
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    break;
                }
                list.add(map[i][j]);
            }
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // up일 때 n - 2
    // n == depth랑 같을 때 n - 1만큼 루프돌리기
    public void process(int n, String type, int downCycle, int upCycle, int num) {
        // 종료조건
        if (n == downCycle + upCycle) return;

        if ("D".equals(type)) {
            for (int i = 0 + downCycle + upCycle; i < n - downCycle; i++) {
                if (map[i][downCycle] != 0) continue;
                map[i][downCycle] = ++num;
            }

            // 제일 아래일때는
            for (int i = downCycle + 1; i < n - downCycle; i++) {
                if (map[n - downCycle - 1][i] != 0) break;
                map[n - downCycle - 1][i] = ++num;
            }

            process(n, "U", downCycle + 1, upCycle, num);
        } else {
            for (int i = n - upCycle - 1; i >= 0 + downCycle; i--) {
                if (map[i][i - upCycle] != 0) continue;
                map[i][i - upCycle] = ++num;
            }

            process(n, "D", downCycle, upCycle + 1, num);
        }
    }
}
