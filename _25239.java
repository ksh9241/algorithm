import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int hh;
	static int mm;
	static int [] recoveryList = new int[6];
	static boolean[] visit = new boolean[6];
	
	static int N;
	static int answer;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String time = br.readLine();
    	hh = Integer.parseInt(time.split(":")[0]);
    	mm = Integer.parseInt(time.split(":")[1]);
    
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for (int i = 0; i < 6; i++) {
    		recoveryList[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	N = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < N; i++) {
    		String line = br.readLine();
    		String event = line.split(" ")[1];
    		
    		calculator(event);
    	}
    	
    	answer = 0;
    	
    	for (int i = 0; i < recoveryList.length; i++) {
    		if (!visit[i]) {
    			answer += recoveryList[i];
    		}
    	}
    	
    	System.out.println(answer);
    }
    
    static void calculator (String event) {
    	int idx = 0;
    	initTime();
    	
    	switch (event) {
    		case "^" : 
    			if (mm != 0) {
    				idx = hh / 2;
    			}
    			else {
    				idx = hh / 2;
    				idx = idx == 6 ? idx - 1 : idx;
    			}
    			break;
    		case "10MIN" :
    				mm += 10;
    			break;
    			
    		case "30MIN" :
    				mm += 30;
    			break;

    		case "50MIN" :
    				mm += 50;
    			break;
    		
    		case "2HOUR" :
    				hh += 2;
    			break;
    			
    		case "4HOUR" :
    				hh += 4;
    			break;
    		
    		case "9HOUR" :
    				hh += 9;
    			break;
    	}
    	
    	visit[idx] = true;
    }
    
    static void initTime() {
    	if (hh > 12 || (hh == 12 && mm > 0)) {
    		hh -= 12;
    	} 
    	if (mm > 59) {
    		mm -= 60;
    		hh += 1;
    	}
    }
}
