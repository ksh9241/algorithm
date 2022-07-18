import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	static int R,C,N;
	
	static String[][] map;
	static int[][] visit;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new String[R][C];
		visit = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				String str = line.substring(j, j + 1); 
				map[i][j] = str;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				timeIncrease();
				continue;
			}
			timeIncrease();
			checkBoom();
			boom();
		}
		
		info();
	}
	static void checkBoom () {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (".".equals(map[i][j])) {
					map[i][j] = "O";
				}
			}
		}
	}
	
	static void timeIncrease () {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if ("O".equals(map[i][j])) {
					visit[i][j]++;
				}
			}
		}
	}
	
	static void boom() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (visit[i][j] == 3) {
					for (int k = 0; k < 4; k++) {
						int nxtX = i + dx[k];
						int nxtY = j + dy[k];
						
						if (nxtX >= 0 && nxtY >= 0 && nxtX < R && nxtY < C && visit[nxtX][nxtY] != 3) {
							map[nxtX][nxtY] = ".";
							visit[nxtX][nxtY] = 0;
						}
					}
					map[i][j] = ".";
					visit[i][j] = 0;
				}
			}
		}
	}
	
	static void info() {
		for (String [] i : map) {
			for (String j : i ) {
				System.out.print(j);
			}
			System.out.println();
		}
		System.out.println();
	}
}