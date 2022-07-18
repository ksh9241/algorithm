import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
	static int[] dp;
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		
		dp = new int[x + 1];
		
		recursion(x);
		System.out.println(dp[1]);
	}
	
	static void recursion (int idx) {
		if (idx == 1) {
			return;
		}
		if (idx % 3 == 0) {
			int nxtIdx = idx / 3;
			if (dp[nxtIdx] != 0 ) {
				if (dp[nxtIdx] > dp[idx] + 1) {
					dp[nxtIdx] = dp[idx] + 1;
					recursion(nxtIdx);
				}
			} else {
				dp[nxtIdx] = dp[idx] + 1;
				recursion(nxtIdx);
			}
		}
		
		if (idx % 2 == 0) {
			int nxtIdx = idx / 2;
			if (dp[nxtIdx] != 0) {
				if (dp[nxtIdx] > dp[idx] + 1) {
					dp[nxtIdx] = dp[idx] + 1;
					recursion(nxtIdx);
				}
			} else {
				dp[nxtIdx] = dp[idx] + 1;
				recursion(nxtIdx);
			}
		}
		
		int nxtIdx = idx - 1;
		if (dp[nxtIdx] != 0) {
			if (dp[nxtIdx] > dp[idx] + 1) {
				dp[nxtIdx] = dp[idx] + 1;
				recursion(nxtIdx);
			}
		} else {
			dp[nxtIdx] = dp[idx] + 1;
			recursion(nxtIdx);
		}
	}
}