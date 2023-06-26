import java.util.*;
import java.io.*;

public class Main {

    static int r,c,k;
    static int[][] A = new int[3][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // A[r - 1][c - 1] = k
        int time = 0;
        while (time <= 100) {
            int rCnt = A.length;
            int cCnt = A[0].length;

            String type = cCnt - rCnt > 0 ? "C" : "R";
            check(type);

            if (A[r - 1][c - 1] == k) {
                break;
            }
            time++;
        }

        System.out.println(time);
    }

    public static void check(String type) {
        // 행의개수 x >= 열의개수 y : R연산 행에 대해서 수, 수 등장 횟수
        // 행의개수 x < 열의개수 y : C연산 열에 대해서 수, 수 등장 횟수
        // 정렬 방식 count 오름차순, 동일할 시 넘버 오름차순
        List<List<Number>> list = new ArrayList<>();

        int[] num = new int[101];
        if ("R".equals(type)) {
            // List add
            for (int i = 0; i < A[0].length; i++) {
                list.add(new ArrayList<Number>());
            }

            // 계산하기
            for (int i = 0; i < A.length; i++) {
                Map<String, Integer> map = new HashMap<>();
                for (int j = 0; j < A[i].length; j++) {
                    map.put(String.valueOf(A[i][j]), map.getOrDefault(String.valueOf(A[i][j]), 0) + 1);
                }

                for (String key : map.keySet()) {
                    list.get(i).add(new Number(Integer.parseInt(key), map.get(key)));
                }
            }



        } else {

        }
    }

    public static class Number implements Comparable<Number>{
        int number;
        int count;

        public Number(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(Number o) {
            if (this.count == o.count) {
                return o.number - this.number;
            }
            return this.count - o.count;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "number=" + number +
                    ", count=" + count +
                    '}';
        }
    }
}


