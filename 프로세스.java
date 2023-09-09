import java.util.*;


class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Locations> list = new LinkedList<>();
        
        for (int i = 0; i < priorities.length; i++) {
            list.add(new Locations(i, priorities[i]));
        }
        
        Arrays.sort(priorities);
        
        int idx = priorities.length - 1;

        while (!list.isEmpty()) {
            Locations l = list.poll();
            
            // 조회한 값이 같으면
            if (priorities[idx] == l.number) {
                answer++;
                idx--;
                if (location == l.location) {
                    break;
                }
            } else {
                list.add(l);
            }
        }
        
        
        return answer;
    }
}

class Locations {
    int location;
    int number;
    
    public Locations (int location, int number) {
        this.location = location;
        this.number = number;
    }
    
    public String toString() {
        return "location == " + location + " number == " + number;
    }
}
