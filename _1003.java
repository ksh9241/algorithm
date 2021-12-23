import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
	static int T;
	static Integer[][] DP;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		
		
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			setUp();
			
			fibonacci(n);
			
			System.out.println(DP[n][0] + " " + DP[n][1]);
		}
	}
	
	static Integer[] fibonacci (int n) {
		if (DP[n][0] == null || DP[n][1] == null) {
			DP[n][0] = fibonacci(n - 1)[0] + fibonacci(n - 2)[0];
			DP[n][1] = fibonacci(n - 1)[1] + fibonacci(n - 2)[1];
		}
		
		return DP[n];
	}
	
	static void setUp() {
		DP = new Integer[41][2];
		
		DP[0][0] = 1;
		DP[0][1] = 0;
		DP[1][0] = 0;
		DP[1][1] = 1;
	}
}