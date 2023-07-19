import java.util.*;

class Solution {
    String prevWord = "";
    Set<String> set = new HashSet<>();
    public int[] solution(int n, String[] words) {
        
        int idx = 0;
        
        boolean successCheck = true;
        
        for (int i = 0; i < words.length; i++) {
            if (i > 0 && check(words[i])) {
                idx = i;
                successCheck = false;
                break;
            }
            prevWord = words[i];
            set.add(words[i]);
        }
        
        System.out.println(idx);
        int turn = (idx % n) + 1;
        int cycle = (int) Math.ceil((double) (idx + 1) / n);
        
        if (successCheck) {
            turn = 0;
            cycle = 0;
        }
        
        int[] answer = {turn, cycle};

        

        return answer;
    }
    
    // 중복호출
    // 전 끝자리랑 매개변수 첫자리 다를 때
    public boolean check(String word) {
        boolean result = false;
        String prevText = prevWord.substring(prevWord.length() - 1, prevWord.length());
        String text = word.substring(0, 1);
        
        if (set.contains(word) || !text.equals(prevText)) {
            result = true;
        }
        
        return result;
    }
}
