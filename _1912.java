import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int answer;
	static int[] arr;
	static int[] dp;
	static boolean[] visit;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	arr = new int[N];
    	dp = new int[N];
    	visit = new boolean[N];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	for (int i = 0; i < N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	recursion(0);
    	
    	int answer = -1001;
    	for (int i = 0; i < N; i++) {
    		answer = Math.max(answer, dp[i]);
    	}
    	System.out.println(answer);
    }
    
    static int recursion(int idx) {
    	if (idx == N - 1) {
    		visit[idx] = true;
    		dp[idx] = arr[idx];
    		return dp[idx];
    	}
    	
    	if (!visit[idx]) {
    		dp[idx] = Math.max(recursion(idx + 1) + arr[idx], arr[idx]);
    		visit[idx] = true;
    		return dp[idx];
    	}
    	else {
    		return dp[idx];
    	}
    }
}