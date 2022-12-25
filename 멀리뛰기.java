class Solution {
    int[] dp = new int[2001];
    public long solution(int n) {
        long answer = fibonacci(n);
        return answer % 1234567;
    }
    
    public int fibonacci(int n) {
        if (n == 1) {
            dp[1] = 1;
            return dp[n];
        }
        if (n == 2) {
            dp[2] = 2;
            return dp[n];
        }
        
        if (dp[n] == 0) {
            dp[n] = (fibonacci(n - 1) + fibonacci(n - 2)) % 1234567;
        }
        return dp[n];
    }
}

/* 5칸 일 때 (8)
1,1,1,1,1
1,2,1,1
1,1,2,1
1,1,1,2
2,1,1,1
1,2,2
2,1,2
2,2,1

2칸일 때(2)
1,1
2

6칸일때 (13)
1,1,1,1,1,1
1,1,1,1,2
1,1,1,2,1
1,1,2,1,1
1,2,1,1,1
2,1,1,1,1
1,1,2,2
1,2,2,1
1,2,1,2
2,1,1,2
2,1,2,1
2,2,1,1
2,2,2
*/


