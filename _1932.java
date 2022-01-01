import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int [][] arr;
	static int [][] answer;
	static int n;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		answer = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int j = 0;
			while (st.hasMoreTokens()) {
				arr[i][j++] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp(0, 0);
		System.out.println(answer[0][0]);
		
	}
	
	
	static int dp (int depth, int idx) {
		if (depth == n - 1) {
			answer[depth][idx] = arr[depth][idx];
			return answer[depth][idx];
		}
		
		if (answer[depth][idx] == 0) {
			answer[depth][idx] = Math.max(dp(depth + 1, idx), dp(depth + 1, idx + 1)) + arr[depth][idx]; 
		}
		return answer[depth][idx];

	}
}