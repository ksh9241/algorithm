import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        int[] node = new int[n + 1];
        for (int i = 2; i < node.length; i++) {
            node[i] = 50001;
        }
        
        int answer = 0;
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // 맵 초기화
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        
        // 노드 세팅
        for (int i = 0; i < edge.length; i++) {
            map.get(edge[i][0]).add(edge[i][1]);
            map.get(edge[i][1]).add(edge[i][0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while(!q.isEmpty()) {
            int number = q.poll();
            List<Integer> list = map.get(number);
            for (int i = 0; i < list.size(); i++) {
                if (node[list.get(i)] > node[number] + 1) {
                    node[list.get(i)] = node[number] + 1;
                    q.add(list.get(i));
                }
            }
        }
        
        Arrays.sort(node);
        answer++;
        for (int i = n; i >= 1; i--) {
            if (node[i] != node[i - 1]) {
                break;
            }
            answer++;
        }
         
        
        return answer;
    }
}
