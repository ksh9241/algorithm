import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int N,M;
	static int c; // bfs로 구한 방 최대 개수
	static int maxRoomCount;
	static int calcRoomCount = 0;
	
	static int[][] room;
	static int[] roomCount;			// 최대카운트를 받음.
	static int[][] copyRoom2;		// 탐색 순번을 받음.
	static boolean[][] visit;
	static boolean[][] tmp;
	static int count = 0;
	static Queue q;
	
	static int[] compass = {1, 2, 4, 8};
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer (br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	room = new int[M + 2][N + 2];
    	copyRoom2 = new int[M + 2][N + 2];
    	roomCount = new int[(M * N) + 1];
    	visit = new boolean[M + 2][N + 2];
    
    	
    	q = new LinkedList<int[]>();	// x, y 받기
    	
    	for (int i = 1; i <= M; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 1; j <= N; j++) {
    			room[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	questRoom();
    	calcTotalRoom();
    	
    	System.out.println(count);
    	System.out.println(maxRoomCount);
    	System.out.println(calcRoomCount);
    }

	static void questRoom() {
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (!visit[i][j]) {
					c = 0;
					q.add(new int[]{i,j});
					bfs();
					count++;
					maxRoomCount = Math.max(maxRoomCount, c);
				}
			}
		}
	}
	
	static void bfs() {
		tmp = new boolean[M + 2][N + 2];
		while (!q.isEmpty()) {
			int[] t = (int[]) q.poll(); 
			int x = t[0];
			int y = t[1];
			
//			if (!tmp[x][y])	
				c++;
			
			tmp[x][y] = true;
			roomCheck(x, y);
		}
		
		// 방 최종 크기의 값을 copyRoom에 값을 넣어준다.
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (tmp[i][j]) {
					roomCount[count + 1] = c;
					copyRoom2[i][j] = count + 1;
				}
			}
		}
	}
	
	static void roomCheck(int x, int y) {
		int num = room[x][y];
		
		/* 
			이진법 한 값이 동,서,남,북 값과 동일하면 벽이 있다는 의미
		*/
		
		for (int i = 0; i < 4; i++) {
			if ((num & compass[i]) != compass[i]) {
				int nxtX = x + dx[i];
				int nxtY = y + dy[i];
				if (!tmp[nxtX][nxtY] && !visit[nxtX][nxtY]) {
					q.add(new int[] {nxtX, nxtY});
					visit[nxtX][nxtY] = true;
				}
			}
		}
	}
	
	// 최대값을 기준으로 하는게 무조건 큰 값이 아닐 수 있기 떄문에 완탐으로 비교해야됨
	static void calcTotalRoom() {
		for (int i = 1; i <= M; i++) {
    		for (int j = 1; j <= N; j++) {
				for (int k = 0; k < 4; k++) {
					int nxtX = i + dx[k];
					int nxtY = j + dy[k];
					
					int roomCountIdx = copyRoom2[nxtX][nxtY];	// 세컨드 값 구하기
					int plusNum = roomCount[roomCountIdx];
					if (copyRoom2[i][j] != copyRoom2[nxtX][nxtY]) {
						roomCountIdx = copyRoom2[i][j];
						calcRoomCount = roomCount[roomCountIdx] + plusNum > calcRoomCount 
								? roomCount[roomCountIdx] + plusNum : calcRoomCount; 
					}
				}
    		}
    	}
	}
}