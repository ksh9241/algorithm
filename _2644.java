import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int N, M;
	static int answer;
	static int[][] map;
	static int[] d;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
    	N = Integer.parseInt(br.readLine());
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int target1 = Integer.parseInt(st.nextToken());
    	int target2 = Integer.parseInt(st.nextToken());
    	
    	M = Integer.parseInt(br.readLine());
    	map = new int[N + 1][N + 1];
    	
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int parent = Integer.parseInt(st.nextToken());
    		int child = Integer.parseInt(st.nextToken());
    		
    		map[parent][child] = 1;
    		map[child][parent] = 1;
    	}
    	
    	d = new int[N + 1];
    	bfs(target1, target2);
    	if (d[target2] == 0) {
    		System.out.println(-1);
    	}
    	else {
    		System.out.println(d[target2]);
    	}
    }
    
    static void bfs(int start, int end) {
    	Queue<Integer> q = new LinkedList<Integer>();
    	
    	q.add(start);
    	while (!q.isEmpty()) {
    		int v = q.poll();
    		
    		if (v == end) {
    			break;
    		}
    		
    		for (int i = 1; i <= N; i++) {
    			if (map[v][i] == 1 && d[i] == 0) {
    				d[i] = d[v] + 1;
    				q.add(i);
    			}
    		}
    	}
    }
}