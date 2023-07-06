import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        
        // 등수 바뀐 횟수 처리
        for (int i = 0; i < callings.length; i++) {
            int up = map.get(callings[i]);
            int down = up - 1;
            
            String upName = players[up];
            String downName = players[down];
            
            players[down] = upName;
            players[up] = downName;
            
            map.remove(upName);
            map.remove(downName);
            
            map.put(upName, down);
            map.put(downName, up);
        }
        
        String[] answer = players;
        return answer;
    }
}
