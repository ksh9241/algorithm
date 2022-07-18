import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] arr;
	static boolean[] v;
	
	static int min = Integer.MAX_VALUE;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		v = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0, 0);
		System.out.println(min);
	}
	
	public static void combination (int start, int depth) {
		if (n / 2 == depth) {
			int teamS = 0;
			int teamL = 0;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) continue;
					
					if (v[i] && v[j]) {
						teamS += arr[i][j];
					} 
					
					if (!v[i] && !v[j]) {
						teamL += arr[i][j];
					}
				}
			}
			
			int val = Math.abs(teamL - teamS);
			
			min = Math.min(min,val);
			return;
		}
		
		for (int i = start; i < n; i++) {
			if (!v[i]) {
				v[i] = true;
				combination (i + 1, depth + 1);
				v[i] = false;
			}
		}
	}
}