import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static String [][] CLASS;
	static int N;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
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
		
		String result = "";
		int count = 0;
		for (int i = 0; i < CLASS.length; i++) {
			for (int j = 0; j < CLASS[i].length; j++) {
				if ("T".equals(CLASS[i][j])) {
					count += check(i,j);
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
	
	static int check (int x, int y) {
		int result = 0;
		
		// 선생님 인접공간에 학생이 있다면 무조건 감시를 피할 수 없으므로 999반환
		for (int i = 0; i < 4; i++) {
			int nxtX = dx[i] + x;
			int nxtY = dy[i] + y;
			
			if (nxtX >= 0 && nxtY >= 0 && nxtX < N && nxtY < N) {
				if ("S".equals(CLASS[nxtX][nxtY])) {
					result = 999;
					break;
				}
			}
		}
		
		// 선생님 인접공간에 학생이 없다면 
		if (result == 0) {
			
			boolean c = false;
			// x값 체크
			for (int i = 0; i < N; i++) {
				if ("S".equals(CLASS[i][y])) {
					if (x > i) {
						if ("T".equals(CLASS[x - 1][y])) continue;
						CLASS[x - 1][y] = "O";
						c = true;
					} else {
						if ("T".equals(CLASS[x + 1][y])) continue;
						CLASS[x + 1][y] = "O";
					}
				}
			}
			
			// y값 체크
			for (int i = 0; i < N; i++) {
				if ("O".equals(CLASS[x][i])) break;
				if ("S".equals(CLASS[x][i])) {
					if (y > i) {
						if ("T".equals(CLASS[x][y - 1])) continue;
						CLASS[x][y - 1] = "O";
					} else {
						if ("T".equals(CLASS[x][y + 1])) continue;
						CLASS[x][y + 1] = "O";
					}
					result++;
					break;
				}
			}
		}
		
		return result;
	}
}