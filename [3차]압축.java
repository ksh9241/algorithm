import java.util.*;
class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int idx = 1;
        for (int i = 'A'; i <= 'Z'; i++) {
            map.put(String.valueOf((char)i), idx);
            idx++;
        }
        
        for (int i = 0; i < msg.length(); i++) {
            String text = msg.substring(i, i + 1);
            if (i < msg.length() - 1) { // 마지막 인덱스 값은 안태운다.
                int ii = i + 1; // i를 j + 1로 하면 i++에 영향도 생김
                
                for (int j = ii; j < msg.length(); j++) {
                    String nextText = text + msg.substring(j, j + 1);
                    // 합친 문자열이 맵에 존재하지 않으면 현재입력 값 list에 담고 신규 값 추가.
                    if (map.get(nextText) == null) {
                        list.add(map.get(text));
                        map.put(nextText, idx);
                        idx++;
                        break;
                    } else { // 합친 문자열이 존재하면 합친 문자열을 현재 텍스트로 변경
                        text = nextText;
                        
                        if (j == msg.length() - 1) {
                            list.add(map.get(text));
                        }
                        
                        i++;
                    }
                }    
            } else { // 마지막 텍스트 처리
                list.add(map.get(text));
            }
        }
        
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
