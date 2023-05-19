import java.util.*;
class Solution {
	static Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (int i = 0; i < info.length; i++) {
            dfs("", info[i].split(" "), 0);
        }

        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] q = query[i].split(" ");
            int score = Integer.parseInt(q[1]);
            answer[i] = binarySearch(q[0], score);
        }


        return answer;
    }


    public int binarySearch(String key, int score) {
        if (map.containsKey(key)) {
            List<Integer> list = map.get(key);
            int start = 0;
            int end = list.size() - 1;

            if (list.get(end) < score) {
                return 0;
            }

            while (start < end) {
                int mid = (start + end) / 2;
                if (list.get(mid) < score) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return list.size() - start;
        }
        return 0;
    }

    public void dfs(String s, String[] info, int depth) {
        if (depth == 4) {
            if (map.containsKey(s) == false) {
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(info[4]));
                map.put(s, list);
            }
            else {
                map.get(s).add(Integer.parseInt(info[4]));
            }
            return;
        }
        dfs(s + "-", info, depth + 1);
        dfs(s + info[depth], info, depth + 1);
    }
}