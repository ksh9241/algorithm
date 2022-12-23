import java.util.*;
class Solution {
    List<int[]> list = new ArrayList<>();
    public int[][] solution(int n) {
        
        hanoi(n, 1, 3, 2);
        
        
        int[][] answer = new int[list.size()][2];
        
        for (int i = 0; i < list.size(); i++) {
            int[] listVal = list.get(i);
            answer[i][0] = listVal[0];
            answer[i][1] = listVal[1];
        }
        return answer;
    }
    
    public void hanoi (int n, int from, int to, int sub) {
        if (n == 1) {
            list.add(new int[]{from, to});
            return;
        }
        
        hanoi (n - 1, from, sub, to);
        list.add(new int[]{from, to});
        hanoi (n - 1, sub, to, from);
    }
}
