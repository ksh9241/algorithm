import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] list = s.split(" ");
        
        int minVal = Integer.parseInt(list[0]);
        int maxVal = Integer.parseInt(list[0]);
        
        for (String val : list) {
            minVal = Math.min(minVal, Integer.parseInt(val));
            maxVal = Math.max(maxVal, Integer.parseInt(val));
        }
        
        answer += String.valueOf(minVal);
        answer += " " + maxVal;

        return answer;
    }
}
