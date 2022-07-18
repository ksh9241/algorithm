import java.io.*;
import java.util.*;

public class Main {
	static int T, count;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {0, 1, 0 , -1};
	static int[] dy = {1, 0, -1 , 0};
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	T = Integer.parseInt(br.readLine());
    	
    	// 테스트 케이스 개수 
    	for (int i = 0; i < T; i++) {
    		count = 0;
    		st = new StringTokenizer(br.readLine());
    		
    		int M = Integer.parseInt(st.nextToken());	// 세로 길이
    		int N = Integer.parseInt(st.nextToken());	// 가로 길이
    		int K = Integer.parseInt(st.nextToken());	// 배추 위치 총 개수
    		
    		map = new int[M + 2][N + 2];
    		visit = new boolean[M + 2][N + 2];
    		
    		// 배추 위치 초기화
    		for (int j = 0; j < K; j++) {
    			st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			map[x + 1][y + 1] = 1;
    		}
    		
    		dfs();
    		System.out.println(count);
    	}
    }
    
    static void dfs() {
    	for (int i = 1; i < map.length - 1; i++) {
    		for (int j = 1; j < map[i].length - 1; j++) {
    			if (!visit[i][j] && map[i][j] == 1) {
    				checkLocation(i, j);
    				count++;
    			}
    		}
    	}
    }
    
    static void checkLocation(int x, int y) {
    	visit[x][y] = true;
    	
    	for (int i = 0; i < 4; i++) {
    		int nextX = x + dx[i];
    		int nextY = y + dy[i];
    		
    		if (!visit[nextX][nextY] && map[nextX][nextY] == 1) {
    			checkLocation(nextX, nextY);
    		}
    	}
    }
}