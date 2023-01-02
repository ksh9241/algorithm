import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        // String sort하면 123, 1234, 1000 이런식으로 정렬되서 루프 한번으로 처리 됨.
        Arrays.sort(phone_book);
        
        boolean answer = true;
        for (int i = 0; i < phone_book.length - 1; i++) {
            int idx = phone_book[i + 1].indexOf(phone_book[i]);
            if (idx == 0) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
