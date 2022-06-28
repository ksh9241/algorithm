import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int[][] arr;
	static int[] answer = new int[3];
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	N = Integer.parseInt(br.readLine());
    	arr = new int [N][N];

    	for (int i = 0; i < N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		
    		for (int j = 0; j < N; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	recursion(0, 0, N);
    	
    	for (int i : answer) {
    		System.out.println(i);
    	}
    	
    }
    
    static void recursion(int x, int y, int size) {
    	if (check(x, y, size)) {
    		answer[arr[x][y] + 1]++;
    		return;
    	} 
    	else {
    		for (int i = 0; i < 3; i++) {
    			recursion(x + (i * size / 3), y, size / 3);
    			recursion(x + (i * size / 3), y + size / 3, size / 3);
    			recursion(x + (i * size / 3), y + (size / 3) * 2, size / 3 );
    		}
    	}
    }
    
    static boolean check(int x, int y, int size) {
    	boolean result = true;
    	int target = arr[x][y];
    	for (int i = x; i < x + size; i++) {
    		for (int j = y; j < y + size; j++) {
    			if (target != arr[i][j]) {
    				result = false;
    				break;
    			}
    		}
    	}
    	
    	return result;
    }
}