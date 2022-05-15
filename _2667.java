import java.io.*;
import java.util.*;

public class Main {
	static int N, count;
	static int[][] map;
	static boolean[][] visit;
	static List<Integer> list = new ArrayList<>();
	static int[] dx = {0, 1, 0 , -1};
	static int[] dy = {1, 0, -1 , 0};
	
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	map = new int[N + 2][N + 2];
    	visit = new boolean[N + 2][N + 2];
    	
    	for (int i = 1; i <= N; i++) {
    		String line = br.readLine();
    		for (int j = 1; j <= N; j++) {
    			map[i][j] = Integer.parseInt(line.substring(j - 1, j));
    		}
    	}
    	
    	for (int i = 1; i <= N; i++) {
    		for (int j = 1; j <= N; j++) {
    			count = 0;
    			if (!visit[i][j] && map[i][j] == 1) {
    				dfs(i, j);
    				list.add(count);
    			}
    		}
    	}
    	
    	
    	Collections.sort(list);
    	
    	System.out.println(list.size());
    	for(int i : list) {
    		System.out.println(i);
    	}
    }
    
    static void dfs(int x, int y) {
    	visit[x][y] = true;
    	count++;
    	
    	for (int i = 0; i < 4; i++) {
    		int chkX = x + dx[i];
    		int chkY = y + dy[i];
    		
    		if (!visit[chkX][chkY] && map[chkX][chkY] == 1) {
    			dfs(chkX, chkY);
    		}
    	}
    }
}