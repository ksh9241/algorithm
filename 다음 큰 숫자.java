class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for (int i = n + 1; i < 1_000_001; i++) {
            if (Integer.bitCount(n) == Integer.bitCount(i)) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}
