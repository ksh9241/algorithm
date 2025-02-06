import java.util.*;

class Solution {
    int[] globalDiffs, globalTimes;
    long globalLimit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        globalDiffs = diffs;
        globalTimes = times;
        globalLimit = limit;
        
        for (int num : diffs) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        
        boolean flag = true;
        
        int level = max;
        
        // 계산식 : (현재시간 + 이전시간) * 틀린횟수 + 현재시간
        while (flag) {
            long result = calculator(level);
            // 수용가능 레벨이면
            if (limit >= result) {
                answer = level;
                
                max = level;
                level = (max + min) / 2;
            } else {
                min = level;
                level = (max + min) / 2;
            }
            
            // 탐색종료
            if (level == min) {
                flag = false;
            }
        }
        
        if (limit >= calculator(1)) {
            answer = 1;
        }
        
        
        return answer;
    }
    
    public long calculator(int level) {
        long result = globalTimes[0];
        
        for (int i = 1; i < globalDiffs.length; i++) {
            long count = 0;
            
            if (globalDiffs[i] > level) {
                count = globalDiffs[i] - level;
                
                result += (globalTimes[i] + globalTimes[i - 1]) * count + globalTimes[i];
            } else {
                result += globalTimes[i];
            }
        }
        
        return result;
    }
}
