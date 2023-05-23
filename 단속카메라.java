import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int len = routes.length;
        List<int[]> list = new ArrayList<>();
        for (int[] i : routes) {
            list.add(i);
        }

        Collections.sort(list, (a,b) -> a[1] - b[1]);

    
        while(!list.isEmpty()) {
            int position = list.get(0)[1];
            list.remove(0);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[0] <= position) {
                    list.remove(i);
                    i--;
                }
            }

            answer++;
        }


        return answer;
    }
}
