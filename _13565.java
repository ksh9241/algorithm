import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] arr;
    static int[][] check;
    static boolean answer = false;
    
    static int [] dx = {1, 0, -1, 0};
    static int [] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        arr = new int[n + 2][m + 2];
        
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(str.substring(j - 1, j)); 
            }
        }
        
        for (int j = 1; j <= m; j++) {
        	if (arr[1][j] == 0) {
        		check = arr.clone();
        		dfs(1, j);
        	}
        }
        
        if (answer) {
        	System.out.println("YES");
        } else {
        	System.out.println("NO");
        }
    }
    
    static void dfs (int i, int j) {
    	if (i == n) {
    		answer = true;
    		return;
    	}
    	
    	if (check[i + 1][j] == 1 && check[i][j + 1] == 1 && check[i - 1][j] == 1 && check[i][j - 1] == 1) return;
    	check[i][j] = 1;
    	
    	for (int k = 0; k < dx.length; k++) {
    		if (i + dx[k] > 0 && i + dx[k] <= n && j + dy[k] > 0 && j + dy[k] <= m) {
    			if (check[i + dx[k]][j + dy[k]] == 0) {
    				dfs(i + dx[k], j + dy[k]);
    			}
    		}
    	}
    }
}