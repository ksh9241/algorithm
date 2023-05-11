import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Planet[] xArr = new Planet[n];
        Planet[] yArr = new Planet[n];
        Planet[] zArr = new Planet[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            xArr[i] = new Planet(x, i);
            yArr[i] = new Planet(y, i);
            zArr[i] = new Planet(z, i);
        }

        Arrays.sort(xArr);
        Arrays.sort(yArr);
        Arrays.sort(zArr);

        List<int[]> list = new ArrayList<>();

        init(list, xArr, n);
        init(list, yArr, n);
        init(list, zArr, n);

        Collections.sort(list, (a, b) -> a[2] - b[2]);

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] val = list.get(i);

            if (!findParent(val[0], val[1], parent)) {
                answer += val[2];
                unionParent(val[0], val[1], parent);
            }
        }
        System.out.println(answer);
    }

    public static void init(List<int[]> list, Planet[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            list.add(new int[]{arr[i].idx, arr[i + 1].idx, Math.abs(arr[i].num - arr[i + 1].num)});
        }
    }

    public static int getParent(int number, int[] parent) {
        if (parent[number] == number) {
            return number;
        }
        return parent[number] = getParent(parent[number], parent);
    }

    public static void unionParent(int x, int y, int[] parent) {
        x = getParent(x, parent);
        y = getParent(y, parent);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    public static boolean findParent(int x, int y, int[] parent) {
        x = getParent(x, parent);
        y = getParent(y, parent);

        return x == y;
    }

    static class Planet implements Comparable<Planet> {
        public Planet(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
        int num;
        int idx;

        @Override
        public int compareTo(Planet o) {
            return num - o.num;
        }
    }
}



