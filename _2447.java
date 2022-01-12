import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Main {
	static int N;
	static char[][] arr;
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(arr[i], ' ');
		}
		star(N, 0, 0);
		
		for (int i = 0; i < N; i++) {
			System.out.println(arr[i]);
		}
	}
	
	static void star (int n, int x, int y) {
		if (n == 1) {
			arr[x][y] = '*';
			return;
		}
		int val = n / 3;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 & j == 1) continue;
				star(val, x + (val * i), y + (val * j));
			}
		}
	}
}