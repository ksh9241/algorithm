import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Main {
	static int N;
	static int[][] arr;
	static StringBuilder sb;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	Scanner sc = new Scanner(System.in);
    	N = Integer.parseInt(br.readLine());
    	
    	arr = new int[N][N];
    	sb = new StringBuilder();
    	for (int i = 0; i < N; i++) {
    		String st = br.readLine();
    		for (int j = 0; j < N; j++) {
    			arr[i][j] = Integer.parseInt(st.substring(j,j+1));
    		}
    	}
    	recursion(0,0,N);
    	
    	System.out.println(sb.toString());
    }
    
    static void recursion(int x, int y, int size) {
    	if (check(x, y, size)) {
    		if (arr[x][y] == 1) {
    			sb.append("1");
    		}
    		else if (arr[x][y] == 0) {
    			sb.append("0");
    		}
    		return;
    	}
    	
    	int newSize = size / 2;
    	sb.append("(");
    	
    	recursion(x, y, newSize);	// 왼쪽 위
    	recursion(x, y + newSize, newSize);	// 오른쪽 위
    	recursion(x + newSize, y, newSize);	// 왼쪽 아래
    	recursion(x + newSize, y + newSize, newSize);	// 오른쪽 아래
    	
    	sb.append(")");
    }
    
    static boolean check(int start, int end, int size) {
    	int target = arr[start][end];
    	for (int i = start; i < start + size; i++) {
    		for (int j = end; j < end + size; j++) {
    			if (target != arr[i][j]) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
}