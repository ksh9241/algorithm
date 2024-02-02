import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
        List<String> strList1 = new ArrayList<>();
        List<String> strList2 = new ArrayList<>();
        
        int startIdx = 0;        
        
        for (int i = 1; i < str1.length(); i++) {
            if (checkAlphabet(str1.charAt(startIdx)) && checkAlphabet(str1.charAt(i))) {
                strList1.add(str1.substring(startIdx, i + 1));
                startIdx = i;
            } else {
                startIdx = i;
            }
        }
        
        startIdx = 0;
        for (int i = 1; i < str2.length(); i++) {
            if (checkAlphabet(str2.charAt(startIdx)) && checkAlphabet(str2.charAt(i))) {
                strList2.add(str2.substring(startIdx, i + 1));
                startIdx = i;
            } else {
                startIdx = i;
            }
        }
        
        
        Collections.sort(strList1);
        Collections.sort(strList2);
        
        float intersectionCount = strList1.size() + strList2.size();
        float unionCount = 0;
        
        int idx = 0;
        boolean[] visit = new boolean[strList2.size()];
        for (int i = 0; i < strList1.size(); i++) {
            for (int j = 0; j < strList2.size(); j++) {
                if (strList1.get(i).toLowerCase().equals(strList2.get(j).toLowerCase()) && !visit[j]) {
                    unionCount++;
                    visit[j] = true;
                    break;
                }
            }
        }
        
        intersectionCount -= unionCount;
        
        double d = (Double.isNaN(unionCount / intersectionCount)) ? 65536 : unionCount / intersectionCount * 65536;
        
        answer = (int)d;
        return answer;
    }
    
    public boolean checkAlphabet(char c) {
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
            return true;
        }
        return false;
    }
}
