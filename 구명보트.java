import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int head = 0;
        int answer = 0;
        for (int i = people.length - 1; i >= head; i--) {
            int weight = people[i];
            int spare = limit - weight;
            if (people[head] <= spare) {
                head++;
            }
            answer++;
        } 
        return answer;
    }
}

