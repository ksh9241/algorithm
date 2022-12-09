import java.util.*;
class Solution {
    static int maxVal = 0;
    static boolean[] visit;
    public int solution(int[][] ability) {
        visit = new boolean[ability.length];
        recursion(ability,  new ArrayList<Integer>(), 0);
        int answer = maxVal;
        return answer;
    }

    static void recursion (int[][] ability, List<Integer> list, int index) {
        if (list.size() == ability[0].length) {
            int answer = 0;
            for (int i : list) {
                answer += i;
            }

            maxVal = Math.max(maxVal, answer);
            return;
        }

        for (int i = 0; i < ability.length; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            list.add(ability[i][index]);
            recursion(ability, list, index + 1);
            list.remove(index);
            visit[i] = false;
        }
    }
}