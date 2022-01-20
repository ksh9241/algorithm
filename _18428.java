import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static String [][] CLASS;
	static int N, count;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		CLASS = new String[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				CLASS[i][j] = st.nextToken();
			}
		}
		
		backTraking(0);
		
		System.out.println("NO");
	}
	static void backTraking (int depth) {
		if (depth == 3) {
			boolean chk = true;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ("T".equals(CLASS[i][j])) {
						if (!check(i,j)) {
							chk = false;
						}
					}
				}
			}
			
			if (chk) {
				System.out.println("YES");
				System.exit(0);
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ("X".equals(CLASS[i][j])) {
					CLASS[i][j] = "O";
					backTraking(depth + 1);
					CLASS[i][j] = "X";
				}
			}
		}
	}
	
	static boolean check (int x, int y) {
		boolean chk = true;
		for (int i = x; i < N; i++ ) {
			if ("S".equals(CLASS[i][y])) {
				chk = false;
				break;
			} else if ("O".equals(CLASS[i][y])) break;
		}
		
		for (int i = x; i >= 0; i--) {
			if ("S".equals(CLASS[i][y])) {
				chk = false;
				break;
			} else if ("O".equals(CLASS[i][y])) break;
		}
		
		for (int i = y; i < N; i++) {
			if ("S".equals(CLASS[x][i])) {
				chk = false;
				break;
			} else if ("O".equals(CLASS[x][i])) break;
		}
		
		for (int i = y; i >= 0; i--) {
			if ("S".equals(CLASS[x][i])) {
				chk = false;
				break;
			} else if ("O".equals(CLASS[x][i])) break;
		}
		
		return chk;
	}
}