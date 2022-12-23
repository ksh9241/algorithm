class Solution {
    
    int[][] dp;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        dp = new int[n + 1][m + 1];
        
        // 웅덩이 체크
        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][1];
            int y = puddles[i][0];
            dp[x][y] = -1;
        }
        dp[1][1] = 1;
        
        answer = recursion(n, m);
        
        return answer;
    }
    
    public int recursion(int n, int m) {
        if (n < 0 || m < 0 || dp[n][m] == -1) return 0;
        if (dp[n][m] > 0) return dp[n][m];
        
        return dp[n][m] = ((recursion(n - 1, m) + recursion(n, m - 1)) % 1000000007) ;
    }
}
