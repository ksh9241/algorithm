class Solution {
    int[] dp;
    public int solution(int n) {
        dp = new int[n + 1];
        
        int answer = recursion(n);
        return answer ;
    }
    
    public int recursion(int n) {
        if (n == 0) {
            dp[n] = 0;
            return 0;
        }
        if (n == 1) {
            dp[n] = 1;
            return 1;
        }
        
        if (dp[n - 1] == 0) {
            recursion(n - 1);
        }
        if (dp[n - 2] == 0) {
            recursion(n - 2);
        }
        
        dp[n] = (dp[n - 1] + dp[n - 2]) % 1234567;
        return dp[n];
    }
}
