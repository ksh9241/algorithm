import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        // targets 목록
        for (int i = 0; i < targets.length; i++) {
            int totalCount = 0;
            
            // 텍스트 읽어서 알파벳 읽기
            for (int j = 0; j < targets[i].length(); j++) {
                String alphabet = targets[i].substring(j, j + 1);
                
                int minIdx = Integer.MAX_VALUE;
                boolean check = true;
                
                // 최소 인덱스 위치 체크
                for (int k = 0; k < keymap.length; k++) {
                    int idx = keymap[k].indexOf(alphabet);
                    
                    if (idx > -1) {
                        minIdx = Math.min(minIdx, idx);
                        check = false;
                    }                    
                }
                
                if (check) {
                    totalCount = -1;
                    break;
                } else {
                    totalCount += minIdx + 1;
                }
            }
            
            answer[i] = totalCount;
        }        
        
        return answer;
    }
}
