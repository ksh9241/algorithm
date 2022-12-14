class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            int value = 0;
            for (int j = i; j <= n; j++) {
                value += j;
                if (value == n) {
                    answer++;
                    break;
                }
                if (value > n) {
                    break;
                }
            }    
        }
        
        return answer;
    }
}
