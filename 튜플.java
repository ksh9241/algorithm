import java.util.*;
class Solution {
    public int[] solution(String s) {
        // split 자르기
        String[] split = s.split("},");
        
        // replace로 숫자만 만들기
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].replaceAll("\\{", "")
                                .replaceAll("\\}", "");
        }
        
        // 적은숫자대로 정렬
        Arrays.sort(split, (o1, o2) -> {
            return o1.length() - o2.length();
        });
        
        // 처리
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[100001];
        for (String numberList : split) {
            String[] numbers = numberList.split(",");
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                if (arr[num] == 0) {
                    arr[num]++;
                    list.add(num);
                }
            }
        }
        
        
        
        int[] answer = new int[list.size()];
        
        int idx = 0;
        for (int number : list) {
            answer[idx] = number;
            idx++;
        }
        
        return answer;
    }
}
