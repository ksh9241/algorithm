import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    String[] globalMinerals;
    public int solution(int[] picks, String[] minerals) {

        globalMinerals = minerals;
        int maxIdx = (int) Math.ceil((float)(minerals.length - 1) / 5) + 1;
        int[] pick = new int[3];

        int totalCount = maxIdx;
        for (int i = 0; i < 3; i++) {
            if (totalCount == 0) {
                break;
            }

            if (totalCount >= picks[i]) {
                pick[i] = picks[i];
                totalCount -= picks[i];
            } else {
                pick[i] = totalCount;
                totalCount = 0;
            }
        }

        quest(0, pick, 0);
        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        return answer;
    }

    public void quest(int depth, int[] pick, int sum) {
        if (pick[0] == 0 && pick[1] == 0 && pick[2] == 0) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 0; i < pick.length; i++) {
            if (pick[i] > 0) {
                int num = calculator(depth, i);
                pick[i] -= 1;
                quest(depth + 1, pick, sum + num);
                pick[i] += 1;
            }
        }
    }

    public int calculator(int idx, int mineralType) {
        int startIdx = idx * 5;
        int endIdx = startIdx + 5;

        int result = 0;

        if (endIdx > globalMinerals.length) {
            endIdx = globalMinerals.length;
        }

        for (int i = startIdx; i < endIdx; i++) {
            if ("diamond".equals(globalMinerals[i])) {
                if (mineralType == 0) {
                    result++;
                } else if (mineralType == 1) {
                    result += 5;
                } else {
                    result += 25;
                }
            } else if ("iron".equals(globalMinerals[i])) {
                if (mineralType == 0) {
                    result++;
                } else if (mineralType == 1) {
                    result++;
                } else {
                    result += 5;
                }
            } else {
                result++;
            }
        }

        return result;
    }
}
