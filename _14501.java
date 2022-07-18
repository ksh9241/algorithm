import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [] dp = new int[n + 10];
		int [] T = new int[n + 10];
		int [] P = new int[n + 10];
		
		int max = 0;
		
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n + 1; i++) {
			dp[i] = Math.max(dp[i], max);
			dp[T[i] + i] = Math.max(dp[T[i] + i], dp[i] + P[i]);
			
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}