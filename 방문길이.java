import java.util.*;

class Solution {
    public int solution(String dirs) {
        int[] position = new int[]{5, 5};
        Map<String, List<String>> map = new HashMap<>();
        
        int answer = 0;

        for (int i = 0; i < dirs.length(); i++) {
            String control = dirs.substring(i, i + 1);
            int[] p = moving(control, position);
            
            if (p[0] < 0 || p[1] < 0 || p[0] >10 || p[1] > 10) {
                continue;
            } 
            String preIdx = "" + position[0] + position[1];
            String nxtIdx = "" + p[0] + p[1];
            
            boolean flag = true;
            
            if (!map.containsKey(preIdx)) {
                List<String> preList = new ArrayList<>();
                List<String> nxtList = new ArrayList<>();
                
                preList.add(preIdx);
                nxtList.add(nxtIdx);
                
                map.put(preIdx, nxtList);
                map.put(nxtIdx, preList);
                
            } else {
                List<String> nxtList = map.get(preIdx);
                
                for (int j = 0; j < nxtList.size(); j++) {
                    if (nxtList.get(j).equals(nxtIdx)) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    nxtList.add(nxtIdx);
                    if (!map.containsKey(nxtIdx)) {
                        map.put(nxtIdx, new ArrayList<>());
                    }
                    List<String> preList = map.get(nxtIdx);
                    preList.add(preIdx);
                    
                    map.put(preIdx, nxtList);
                    map.put(nxtIdx, preList);
                }
            }
            
            if (flag) {
                answer++;    
            }
            position = p;

        }
        
        return answer;
    }
    
    public int[] moving(String control, int[] indicators) {
        int[] result = new int[2];
        int x = 0;
        int y = 0;
        switch (control) {
            case "U" :
                x = indicators[0];
                y = indicators[1];
                
                result[0] = x - 1;
                result[1] = y;
                break;
            case "L" :
                x = indicators[0];
                y = indicators[1];
                
                result[0] = x;
                result[1] = y - 1;
                break;
                
            case "R" :
                x = indicators[0];
                y = indicators[1];
                
                result[0] = x;
                result[1] = y + 1;
                break;
            
            case "D" :
                x = indicators[0];
                y = indicators[1];
                
                result[0] = x + 1;
                result[1] = y;
                break;
        }
        
        return result;
    }
}
