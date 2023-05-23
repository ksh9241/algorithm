import java.util.*;

class Solution {
    int[] answer = new int[2];
    Map<String, Integer> map = new HashMap<>();
    int minVal = Integer.MAX_VALUE;
    String minKey = "";

    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for( String s : gems ) {
            set.add(s);
        }

        int count = 0;
        for (int i = 0; i < gems.length; i++) {
            if (map.get(gems[i]) == null) {
                map.put(gems[i], i + 1);
                count++;
            } else {
                map.put(gems[i], i + 1);
            }

            if (count == set.size() && ("".equals(minKey) || minKey.equals(gems[i]))) {
                calculator();
            }
        }

        return answer;
    }

    public void calculator() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (String key : map.keySet()) {
            int num = map.get(key);
            if (min > num) {
                min = num;
                minKey = key;
            }
            max = Math.max(max, num);
        }

        int total = max - min;
        if (minVal > total) {
            minVal = total;
            answer[0] = min;
            answer[1] = max;
        }
    }
}
