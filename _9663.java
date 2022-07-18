import java.io.*;
import java.util.*;

public class Main {
	static int answer, n;
	static int [] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= n; i++) {
        	map = new int[15];
        	map[1] = i;
        	dfs(1);
        }
        
        System.out.println(answer);
    }
    
    static void dfs (int row) {
    	if (row == n) {
    		++answer; 
    	} else {
    		for (int i = 1; i <= n; i++) {
    			map[row + 1] = i;
    			if (isPossible(row + 1)) {
    				dfs(row + 1);
    			} else {
    				map[row + 1] = 0;
    			}
    		}
    	}
    	map[row] = 0;
    }
    
    static boolean isPossible (int c) {
    	for (int i = 1; i < c; i++) {
    		// 같은 행, 열
    		if (map[i] == map[c]) {
    			return false;
    		}
    		
    		// 대각선
    		if (Math.abs(map[i] - map[c]) == Math.abs(i - c)) {
    			return false;
    		}
    	}
    	return true;
    }
}