import java.util.*;

class Solution {
    boolean[] visit = new boolean[1_000_001];
    List<List<Integer>> list = new ArrayList<>();

    int donutCount = 0; // idx 1
    int lineCount = 0; // idx 2
    int eightCount = 0; // idx 3

    boolean check = false;

    public int[] solution(int[][] edges) {
        // Output 0번째 생성 정점, 1번째 도넛모양 개수(자기자신), 2번째 막대 그래프 개수, 3번째 8자모양 개수
        int[] answer = new int[4];

        for (int i = 0; i < 1_000_001; i++) {
            list.add(new ArrayList<Integer>());
        }

        // 생성노드 찾기
        boolean[] addNodes = new boolean[1_000_001];
        int maxIdx = 0;


        // 노드 세팅
        for (int i = 0 ; i < edges.length; i++) {
            int idx = edges[i][0];
            int value = edges[i][1];

            addNodes[value] = true;
            maxIdx = Math.max(maxIdx, idx);
            list.get(idx).add(value);
        }

        // 생성노드 찾기
        int addNode = 0;
        for (int i = 1; i <= maxIdx; i++) {
            if (!addNodes[i] && list.get(i).size() > 1) {
                addNode = i;
                visit[i] = true;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0 ; i < list.get(addNode).size(); i++) {
            q.add(list.get(addNode).get(i));
        }

        while(!q.isEmpty()) {
            int idx = q.poll();
            dfs(idx, idx, false);
            check = false;
        }
        donutCount += list.get(addNode).size();

        answer = new int[]{addNode, donutCount, lineCount, eightCount};
        // 생성 노드 (다른 노드에서 탐색하는 게 없을 때)

        return answer;
    }
    public void dfs(int startIdx, int idx, boolean tooIdx) {

        // 막대 그래프
        if (list.get(idx).size() == 0 && !check) {
            lineCount++;
            donutCount--;
            check = true;
            return;
        }

        // 8자 모양 그래프 || 도넛그래프
        if (startIdx == idx && !check) {
            boolean flag = true;
            for (int i : list.get(idx)) {
                if (!visit[i]) {
                    flag = false;
                }
            }

            // 8자 그래프
            if (flag && tooIdx) {
                eightCount++;
                donutCount--;
                check = true;
                return;
            }
            // 도넛그래프
            if (flag && !tooIdx) {
                check = true;
                return;
            }
        }

        if (!check) {
            if (list.get(idx).size() == 2) {
                tooIdx = true;
            }
            for (int i = 0; i < list.get(idx).size(); i++) {
                int nxtIdx = list.get(idx).get(i);
                if (!visit[nxtIdx] || startIdx == nxtIdx) {
                    visit[nxtIdx] = true;
                    dfs(startIdx, nxtIdx, tooIdx);
                }
            }
        }
    }
}