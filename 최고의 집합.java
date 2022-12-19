import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        int[] answer;
        if (s == 1 || n > s) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            int num = 0;
            int remain = 0;
            num = s / n;
            remain = s % n;
            answer = new int[n];
            for (int i = 0; i < n; i++) {
                answer[i] = num;
            }
            
            for (int i = 0; i < remain; i++) {
                answer[i] += 1;
            }
        }
        Arrays.sort(answer);
        return answer;
    }
}

