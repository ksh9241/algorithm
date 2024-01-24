import java.util.*;

class Solution {
    int answer = 0;
    public int solution(int[][] targets) {


        // 오름차순 정렬
        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });

        quest(targets);

        return answer;
    }

    public void quest(int[][] targets) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < targets.length; i++) {
            int startVal = targets[i][0];
            int endVal = targets[i][1];

            if (end <= startVal) {
                answer++;
                start = startVal;
                end = endVal;
            } else {
                if (start < startVal) {
                    start = startVal;
                }
                
                if (end > endVal) {
                    end = endVal;
                }
            }
        }
    }
}
