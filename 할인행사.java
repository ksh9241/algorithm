import java.util.*;
class Solution  {
    int answer = 0;
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> map = new HashMap<>();
        int totalCount = 0;
        for (int i = 0; i < number.length; i++) {
            map.put(want[i], number[i]);
            totalCount += number[i];
        }
        
        for (int i = 0; i < discount.length; i++) {
            quest(i, totalCount, discount, new HashMap<String, Integer>(map));
        }
        return answer;
    }
    
    void quest(int idx, int totalCount, String[] discount, Map<String, Integer> map) {
        int day = Math.min(10, discount.length - idx);
        for (int i = idx; i < day + idx; i++) {
            // 물품을 다 못사니 탐색종료
            if (day + idx - i < totalCount) {
                break;
            }
            
            if (map.get(discount[i]) != null && map.get(discount[i]) > 0) {
                map.put(discount[i], map.get(discount[i]) - 1);
                totalCount--;
            }
            
            // 모든 물품 구매했으면 종료
            if (totalCount == 0) {
                answer++;
                break;
            }
        }
    }
}
