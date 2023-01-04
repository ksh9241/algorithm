import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        int[][] arr = new int[10000001][1];
        for (int i = 0; i < tangerine.length; i++) {
            arr[tangerine[i]][0]++;
        }
        
        // 내림 정렬
        Arrays.sort(arr, (o1, o2) -> {
            return o2[0] - o1[0];
        });
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] > 0 && k > 0) {
                answer++;
                k -= arr[i][0];
            } 
            if (k <= 0) {
                break;
            }
        }
        return answer;
    }
}
