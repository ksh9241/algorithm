import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int[][] rgb;
	static int[][] DP;
	static int n;
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		rgb = new int[n][3];
		DP = new int[n][3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DP[0][0] = rgb[0][0];
		DP[0][1] = rgb[0][1];
		DP[0][2] = rgb[0][2];
		
		
		System.out.println(Math.min(recursion(2, n - 1) ,Math.min(recursion(0, n - 1),  recursion(1, n - 1))));
	}
	
	static int recursion (int idx, int depth) {
		
		if (DP[depth][idx] == 0) {
			
			if (idx == 0) {
				DP[depth][0] = Math.min(recursion (1, depth - 1), recursion (2, depth - 1)) + rgb[depth][0];
			}
			else if (idx == 1) {
				DP[depth][1] = Math.min(recursion (0, depth - 1), recursion (2, depth - 1)) + rgb[depth][1];
			}
			else {
				DP[depth][2] = Math.min(recursion (0, depth - 1), recursion (1, depth - 1)) + rgb[depth][2];
			}
		}
		
		return DP[depth][idx];
	}
}