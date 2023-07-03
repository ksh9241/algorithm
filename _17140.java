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
        if (r - 1 < A.length && c - 1 < A[0].length && A[r - 1][c - 1] == k) {
            System.out.println(time);
            return;
        }
        while (time <= 100) {
            int rCnt = A.length;
            int cCnt = A[0].length;

            String type = cCnt - rCnt > 0 ? "C" : "R";
            check(type);

            if (r - 1 < A.length && c - 1 < A[0].length && A[r - 1][c - 1] == k) {
                time++;
                break;
            }
            time++;
        }
        if (time > 100) {
            time = -1;
        }

        System.out.println(time);
    }

    public static void check(String type) {
        // 행의개수 x >= 열의개수 y : R연산 행에 대해서 수, 수 등장 횟수
        // 행의개수 x < 열의개수 y : C연산 열에 대해서 수, 수 등장 횟수
        // 정렬 방식 count 오름차순, 동일할 시 넘버 오름차순
        List<List<Number>> list = new ArrayList<>();
        int maxLen = Integer.MIN_VALUE;

        int[] num = new int[101];
        if ("R".equals(type)) {
            // List add
            for (int i = 0; i < A.length; i++) {
                list.add(new ArrayList<Number>());
            }

            // 계산하기
            for (int i = 0; i < A.length; i++) {
                Map<String, Integer> map = new HashMap<>();
                for (int j = 0; j < A[i].length; j++) {
                    if (A[i][j] > 0) {
                        map.put(String.valueOf(A[i][j]), map.getOrDefault(String.valueOf(A[i][j]), 0) + 1);
                    }
                }

                for (String key : map.keySet()) {
                    list.get(i).add(new Number(Integer.parseInt(key), map.get(key)));
                }
            }

            // 정렬
            for (int i = 0; i < list.size(); i++) {
                Collections.sort(list.get(i));
                maxLen = Math.max(maxLen, list.get(i).size());
            }

            // 배열 다시 세팅
            int[][] copyA = new int[A.length][maxLen * 2];

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    copyA[i][j * 2] = list.get(i).get(j).number;
                    copyA[i][(j * 2) + 1] = list.get(i).get(j).count;
                }
            }

            A = copyA;

        } else {

            for (int i = 0; i < A[0].length; i++) {
                list.add(new ArrayList<Number>());
            }

            // 계산하기
            for (int i = 0; i < A[0].length; i++) {
                Map<String, Integer> map = new HashMap<>();
                for (int j = 0; j < A.length; j++) {
                    if (A[j][i] > 0) {
                        map.put(String.valueOf(A[j][i]), map.getOrDefault(String.valueOf(A[j][i]), 0) + 1);
                    }
                }

                for (String key : map.keySet()) {
                    list.get(i).add(new Number(Integer.parseInt(key), map.get(key)));
                }
            }

            // 정렬
            for (int i = 0; i < list.size(); i++) {
                Collections.sort(list.get(i));
                maxLen = Math.max(maxLen, list.get(i).size());
            }

            // 배열 다시 세팅
            int[][] copyA = new int[maxLen * 2][A[0].length];

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).size(); j++) {
                    copyA[j * 2][i] = list.get(i).get(j).number;
                    copyA[(j * 2) + 1][i] = list.get(i).get(j).count;
                }
            }

            A = copyA;
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
                return this.number - o.number;
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

