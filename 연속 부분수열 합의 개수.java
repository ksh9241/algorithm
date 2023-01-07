import java.util.*;
class Solution {
    Set<Integer> set = new HashSet<>();
    public int solution(int[] elements) {
        
        for (int i = 1; i <= elements.length; i++) {
            sequence(i, elements);
        }
        int answer = set.size();
        return answer;
    }
    
    void sequence(int count, int[] elements) {
       for (int i = 0; i < elements.length; i++) {
           int number = 0;
           for (int j = i; j < i + count; j++) {
               int idx = j % elements.length;
               number += elements[idx];
           }
           set.add(number);
       } 
    }
}
