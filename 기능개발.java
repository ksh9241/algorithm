import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        int minDay = 0;
        int[] time = new int[101];
        for (int i = 0; i < progresses.length; i++) {
            int remainProgress = 100 - progresses[i];
            int t = remainProgress / speeds[i];
            if (remainProgress % speeds[i] > 0) {
                t++;
            }
            if (i == 0) {
                minDay = t;
            } else {
                minDay = Math.max(minDay, t);
            }
            time[minDay]++;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i : time) {
            if (i != 0) {
                list.add(i);
            }
        }
        
        int[] answer = new int[list.size()];
        int idx = 0;
        for (int i : list) {
            answer[idx] = i;
            idx++;
        }
        
        
        return answer;
    }
}
