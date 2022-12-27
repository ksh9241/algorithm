import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        int idx = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] <= idx) {
                break;
            }
            idx++;
        }
        return answer = idx;
    }
}
