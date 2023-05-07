import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 6;
        int[][] network = {{1, 2}, {3, 5}, {4, 2}, {5, 6}};
        int[][] repair = {{3, 2, 10}, {5, 4, 15}};

        long answer =
                solution.solution(n, network, repair);
        System.out.println(answer);
    }


    static class Solution {
        public int solution(int n, int[][] network, int[][] repair) {
            List<Edge> list = new ArrayList<>();
            for (int i = 0; i < network.length; i++) {
                list.add(new Edge(network[i][0], network[i][1], 0));
            }

            for (int i = 0; i < repair.length; i++) {
                list.add(new Edge(repair[i][0], repair[i][1], repair[i][2]));
            }

            Collections.sort(list);

            int[] parent = new int[n + 1];


            for (int i = 1; i < parent.length; i++) {
                parent[i] = i;
            }

            int count = 0;
            int answer = 0;
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                if (!findParent(parent, edge.node[0], edge.node[1])) {
                    count++;
                    answer+= edge.distance;
                    unionParent(parent, edge.node[0], edge.node[1]);
                }
            }

            if (count != n - 1) {
                answer = -1;
            }

            return answer;
        }

        public int getParent(int[] parent, int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = getParent(parent, parent[x]);
        }

        public void unionParent(int[] parent, int a, int b) {
            a = getParent(parent, a);
            b = getParent(parent, b);
            if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }

        public boolean findParent(int[] parent, int a, int b) {
            a = getParent(parent, a);
            b = getParent(parent, b);
            if (a == b) {
                return true;
            }
            return false;
        }
    }

    static class Edge implements Comparable<Edge> {

        public Edge(int a, int b, int distance) {
            node[0] = a;
            node[1] = b;
            this.distance = distance;
        }
        int[] node = new int[2];
        int distance;


        @Override
        public String toString() {
            return "Edge{" +
                    "node=" + Arrays.toString(node) +
                    ", distance=" + distance +
                    '}';
        }

        @Override
        public int compareTo(Edge o) {
            return distance - o.distance;
        }
    }
}



