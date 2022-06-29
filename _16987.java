import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int answer;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	
    	// A.내구도 - B.무게 , B.내구도 - A.무게
    	List<egg> list = new ArrayList<>();
    	
    	for (int i = 0; i < N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		egg e = new egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    		
    		list.add(e);
    	}
    	boolean[] visit = new boolean[N]; 
    	
    	
    	recursion(list, visit, 0, 0);
    	System.out.println(answer);
    }
    
    static void recursion(List<egg> list, boolean[] visit, int turn, int m) {
    	if (turn >= N) {
    		answer = Math.max(m, answer);
    		return;
    	}
    	
    	if (visit[turn]) {
    		recursion(list, visit, turn + 1, m);
    	}
    	else {
    		boolean flag = true;
    		
    		for (int i = 0 ; i < N; i++) {
    			int temp = 0;
    			
    			if (!visit[i]) flag = false;
    			
    			if (!visit[i] && i != turn) {
    				list.get(turn).durability -= list.get(i).weight;
    				list.get(i).durability -= list.get(turn).weight;
    				
    				if (list.get(i).durability <= 0) {
    					visit[i] = true;
    					temp++;
    				}
    				
    				if (list.get(turn).durability <= 0) {
    					visit[turn] = true;
    					temp++;
    				}
    				
    				recursion(list, visit, turn + 1, m + temp);
    				
    				if (list.get(turn).durability <= 0) {
    					visit[turn] = false;
    				}
    				if (list.get(i).durability <= 0) {
    					visit[i] = false;
    				}
    				
    				list.get(turn).durability += list.get(i).weight;
    				list.get(i).durability += list.get(turn).weight;
    			}
    		}
    		
    		if (!flag) recursion(list, visit, N, m);
    	}
    }
    
    
    static class egg {
    	int durability;
    	int weight;
    	
    	public egg() {}
    	
    	public egg(int durability, int weight) {
    		this.durability = durability;
    		this.weight = weight;
    	}
    	
    	@Override
    	public String toString() {
    		return "durability = " + durability + " weight = " + weight; 
    	}
    }
}