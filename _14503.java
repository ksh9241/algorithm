import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dy = {0, 1, 0, -1};
	
	static int n,m;
	static int[][] room;
	
	static int answer = 0;
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		room = new int[n][m];
		
		// 로봇 청소기 위치
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());
		
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(r, c, direction);
		
		System.out.println(answer);
		
	}
	
	static void dfs(int r, int c, int dir) {
		if (room[r][c] == 1) return;
		else if (room[r][c] == 0) {
			room[r][c] = 2;
			answer++;
		}
		
		for (int i = 0; i < dx.length; i++) {
			dir = (dir + 3) % 4;
			int nr = r + dx[dir];
			int nc = c + dy[dir];
			
			if (room[nr][nc] > 0) continue;
			dfs(nr, nc, dir);
			return;
		}
		
		// 바라보는 방향을 유지한 채로 한칸 후진하고 2번으로 돌아감
		dfs(r - dx[dir], c - dy[dir], dir);
		return;
	}
}
