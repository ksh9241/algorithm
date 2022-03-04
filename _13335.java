import java.io.*;
import java.util.*;

public class Main {
    private static int N,W,L;
    
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int time = 0;
        int totalW = 0;
        int[] d = new int[N + 1];
        int idx = 0;
        
        while (true) {
            time++;
            
            if (d[N -  1] == W) {
                break;
            }
            
            if (d[idx] == 0 && arr[idx] != 0) {
                if (totalW + arr[idx] <= L) {
                    totalW += arr[idx];
                    idx = idx + 1;
                }
            }
            
            for (int i = 0; i < idx; i++) {
                d[i]++;
                if (d[i] == W) {
                    totalW -= arr[i];
                }
            }
        }
        System.out.println(time);
    }
}