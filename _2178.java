import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static int N,M;
	static int[][] map;
	static int[][] visit;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main (String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(s.substring(j,j+1));
			}
		}
		
		visit[0][0] = 1;
		bfs();
		
		System.out.println(visit[N - 1][M - 1]);
	}
	
	static void bfs () {
		Queue<node> q = new LinkedList<>();
		q.add(new node(0,0));
		
		while (!q.isEmpty()) {
			node n = q.poll();
			int x = n.getX();
			int y = n.getY();
			
			for (int i = 0; i < 4; i++) {
				int nxtX = x + dx[i];
				int nxtY = y + dy[i];
				
				if (nxtX < 0 || nxtY < 0 || nxtX >= N || nxtY >= M) {
					continue;
				} 
				
				if (map[nxtX][nxtY] == 1) {
					if (visit[nxtX][nxtY] == 0) {
						visit[nxtX][nxtY] = visit[x][y] + 1;
						q.add(new node(nxtX, nxtY));
					} else {
						if (visit[nxtX][nxtY] > visit[x][y] + 1) {
							visit[nxtX][nxtY] = visit[x][y] + 1;
							q.add(new node(nxtX, nxtY));
						}
					}
				}
				
			}
		}
	}
	
	static class node {
		private int x;
		private int y;
		
		public node (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return this.x;
		}
		
		public int getY() {
			return this.y;
		}
	}
}