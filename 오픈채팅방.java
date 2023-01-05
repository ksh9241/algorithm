import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        Queue<User> q = new LinkedList<>();
        Map<String, String> map = new HashMap<>();
        
        for (int i = 0; i < record.length; i++) {
            String[] info = record[i].split(" ");
            // 닉변은 큐에 안쌓기
            if (!"Change".equals(info[0])) {
                q.add(new User(info[1], info[0]));    
            }
            
            // 나가는건 맵 수정 안하기
            if (!"Leave".equals(info[0])) {
                map.put(info[1], info[2]);    
            }
        }
        
        String[] answer = new String[q.size()];
        int idx = 0;
        
        while (!q.isEmpty()) {
            User user = q.poll();
            String nick = map.get(user.userId);
            
            switch (user.type) {
                case "Enter" :
                    answer[idx] = nick + "님이 들어왔습니다.";
                    idx++;
                    break;
                case "Leave" :
                    answer[idx] = nick + "님이 나갔습니다.";
                    idx++;
                    break;
            }
        }
        
        
        return answer;
    }
    
    class User {
        String userId;
        String type;
        
        public User (String userId, String type) {
            this.userId = userId;
            this.type = type;
        }
    }
}
