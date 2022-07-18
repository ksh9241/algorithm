import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int answer = 0;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	List<Integer> list = new ArrayList<>();
    	for (int i = 0; i < N; i++) {
    		list.add(Integer.parseInt(st.nextToken()));
    	}
    	
    	recursion(list, 0);
    	System.out.println(answer);
    }
    
    static void recursion(List<Integer> list, int value) {
    	if (list.size() <= 2) {
    		if (answer < value) {
    			answer = value;
    		}
    		return;
    	}
    	
    	for (int i = 1; i < list.size() - 1; i++) {
    		int tmp = list.get(i);
    		int num = list.get(i - 1) * list.get(i + 1);
    		list.remove(i);
    		recursion(list, value + num);
    		list.add(i, tmp);
    	}
    }
}