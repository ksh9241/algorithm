import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Main {
	static int T,N;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	T = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < T; i++) {
    		N = Integer.parseInt(br.readLine());
    		int[][] arr = new int[N][2];
    		int count = 1;
    		for (int j = 0; j < N; j++) {
    			StringTokenizer st = new StringTokenizer(br.readLine());
    			arr[j][0] = Integer.parseInt(st.nextToken());
    			arr[j][1] = Integer.parseInt(st.nextToken());
    		}
    		
    		Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] arr1, int[] arr2) {
					return Integer.compare(arr1[0], arr2[0]);
				}
    		}); 
    		
    		int pivot = arr[0][1];
    		for (int k = 1; k < N; k++) {
    			if (arr[k][1] < pivot) {
    				pivot = arr[k][1];
    				
    				count++;
    			}
    		}
    		System.out.println(count);
    	}
    }
}