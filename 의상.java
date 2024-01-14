import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (int i = 0; i < clothes.length; i++) {
            String key = clothes[i][1];
            String value = clothes[i][0];
            
            if (map.get(key) == null) {
                List<String> list = new ArrayList<>();
                list.add(value);
                map.put(key, list);
                
                continue;
            } 
            
            List<String> list = map.get(key);
            list.add(value);
            map.put(key, list);
        }
        
        List<Integer> counts = new ArrayList<>();
        int answer = 0;
        
        for (String key : map.keySet()) {
            List<String> values = map.get(key);
            counts.add(values.size() + 1);
        }
        
        int multiply = 1;

        for (int i : counts) {
            multiply *= i;
        }

        answer += multiply - 1;
        
        return answer;
    }
}
