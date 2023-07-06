import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        String[] arr = today.split("\\.");
        int processingDate = calculator(arr);

        Map<String, Integer> map = new HashMap<>();

        // 타입 별 날짜 세팅
        for (int i = 0; i < terms.length; i++) {
            String[] term = terms[i].split(" ");
            map.put(term[0], Integer.parseInt(term[1]) * 28);
        }

        // 1년 = 28 * 12
        // 1개월 = 28
        // 일자 = + 일자

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] val = privacies[i].split(" ");
            String[] array = val[0].split("\\.");

            int checkDate = calculator(array);
            checkDate += map.get(val[1]);

            System.out.println(processingDate);
            System.out.println(checkDate);
            System.out.println("----");

            if (processingDate >= checkDate) {
                list.add(i + 1);
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public int calculator (String[] arr) {
        int result = 0;
        result += Integer.parseInt(arr[0]) * 28 * 12;
        result += Integer.parseInt(arr[1]) * 28;
        result += Integer.parseInt(arr[2]);

        return result;
    }
}
