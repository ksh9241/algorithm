import java.util.*;
class Solution {
    boolean[] visit;
    int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        visit = new boolean[words.length];
        recursion(words, begin, target, 0);
        
        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        return answer;
    }
    
    public void recursion(String[] words, String word, String target, int count) {
        if (word.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (!visit[i] && check(words[i], word)) {
                visit[i] = true;
                recursion(words, words[i], target, count + 1);
                visit[i] = false;
            }
        }
    }
    
    public boolean check(String listWord, String target) {
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if (listWord.charAt(i) == target.charAt(i)) {
                count++;
            }
        }
        
        return target.length() == count + 1;
    }
}
