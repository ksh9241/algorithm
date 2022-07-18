import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N,r,c;	// r = 행, c = 열
	static int[][] arr;
	static int answer = 0;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	arr = new int [2 * N][2 * N];
    	
    	int size = (int) Math.pow(2, N); 
    	recursion(r, c, size);
    	System.out.println(answer);
    }
    
    static void recursion(int x, int y, int size) {
    	if (size == 1) return;
    	
    	if (x < size / 2 && y < size / 2) {
    		recursion(x, y, size/2);
    	}
    	else if (x < size / 2 && y >= size / 2) {
    		answer += size * size / 4;
    		recursion(x, y - size / 2, size / 2);
    	}
    	else if (x >= size / 2 && y < size / 2) {
    		answer += (size * size / 4) * 2;
    		recursion(x - size / 2, y, size / 2);
    	}
    	else {
    		answer += (size * size / 4) * 3;
    		recursion(x - size / 2, y - size / 2, size / 2);
    	}
    }
}