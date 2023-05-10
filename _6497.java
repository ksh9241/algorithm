import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (M == 0 && N == 0) break;
            List<Node> list = new ArrayList<>();
            int[] parent = new int[M];

            for (int i = 0; i < M; i++) {
                parent[i] = i;
            }
            int totalCost = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list.add(new Node(node1, node2, cost));
                totalCost += cost;
            }

            Collections.sort(list);

            System.out.println(String.valueOf(totalCost - kruskal(list, parent)));
        }
    }

    public static int kruskal(List<Node> list, int[] parent) {
        int cost = 0;
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (!findParent(node.nodes[0], node.nodes[1], parent)) {
                unionParent(node.nodes[0], node.nodes[1], parent);
                cost += node.cost;
            }
        }

        return cost;
    }

    public static void unionParent(int node1, int node2, int[] parent) {
        int a = getParent(node1, parent);
        int b = getParent(node2, parent);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static boolean findParent(int node1, int node2, int[] parent) {
        int a = getParent(node1, parent);
        int b = getParent(node2, parent);

        return a == b;
    }

    public static int getParent(int node, int[] parent) {
        if (node == parent[node]) {
            return node;
        }

        return parent[node] = getParent(parent[node], parent);
    }

    public static class Node implements Comparable<Node>{
        public Node(int node1, int node2, int cost) {
            nodes[0] = node1;
            nodes[1] = node2;
            this.cost = cost;
        }

        int[] nodes = new int[2];
        int cost;

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "nodes=" + Arrays.toString(nodes) +
                    ", cost=" + cost +
                    '}';
        }
    }
}



