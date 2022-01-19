import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static String [][] CLASS;
	static int N, count;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		CLASS = new String[N + 2][N + 2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				CLASS[i][j] = st.nextToken();
			}
		}
		
		String result = "";
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if ("T".equals(CLASS[i][j])) {
					for (int k = 0; k < 4; k++) {
						if ("S".equals(CLASS[i + dx[k]][j + dy[k]])) count = 999;
						boolean b = backTraking(i,j, k);
						if (b && "X".equals(CLASS[i + dx[k]][j + dy[k]])) {
							CLASS[i + dx[k]][j + dy[k]] = "O";
							count++;
						}
					}
					info();
				}
			}
		}
		
		if (count > 3) {
			result = "NO";
		} else {
			result = "YES";
		}
		
		System.out.println(result);
	}

	
	static boolean backTraking(int x, int y, int idx) {
		if (x > N || y > N || x <= 0 || y <= 0) {
			return false;
		}
		
		if ("S".equals(CLASS[x][y])) {
			return true;
		}
		else if ("O".equals(CLASS[x][y])) {
			return false;
		}
		else {
			return backTraking(x + dx[idx], y + dy[idx], idx);
		}
	}
	
	static void info () {
		for (String[] i : CLASS) {
			for (String j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
}