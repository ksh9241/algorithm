import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
       int n = Integer.parseInt(br.readLine());

       int[][] time = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            time[i][0] = startTime;
            time[i][1] = endTime;
        }

        Arrays.sort(time, new Comparator<int[]>(){

            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[1] == o1[1]) {
                    return Integer.compare(o2[0], o1[0]);
                }
                return Integer.compare(o2[1], o1[1]);
            }
        });


        int idx = time[0][0]; // 12
        int count = 1;
        for (int i = 1; i < time.length; i++) {
            if (idx >= time[i][1]) {
                idx = time[i][0];
                count++;
                continue;
            }
            if (idx < time[i][0]) {
                idx = time[i][0];
            }

        }
        System.out.println(count);
    }
}
