import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int checkIdx = 0;
        
        for (int i = 0; i < A.length; i++) {
            if (A[checkIdx] < B[i]) {
                answer++;
                checkIdx++;
            }
        }
        return answer;
    }
}
