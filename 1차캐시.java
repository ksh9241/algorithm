import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> list = new ArrayList<>();

        int idx = 0;
        if (cacheSize > 0) {
            for (int i = 0; i < cities.length; i++) {
                if (idx == 0) {
                    list.add(cities[i]);
                    answer += 5;
                    idx++;
                    continue;
                }

                boolean flag = false;
                for (int j = 0; j < list.size(); j++) {
                    String city = list.get(j);
                    if (city.toUpperCase().equals(cities[i].toUpperCase())) {
                        answer++;
                        flag = true;

                        list.remove(city);
                        list.add(city);
                        break;
                    }
                }

                if (!flag) {
                    if (list.size() < cacheSize) {
                        list.add(cities[i]);
                        answer += 5;
                        idx++;
                    } else {
                        list.remove(0);
                        list.add(cities[i]);
                        answer += 5;
                    }
                }
            }
        }else {
            answer += cities.length * 5;
        }

        return answer;
    }
}
