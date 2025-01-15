import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        Stack<Plan> stack = new Stack<>();
        int nowTime = 0;
        String[] answer = new String[plans.length];
        int idx = 0;
        
        
        PriorityQueue<Plan> pq = new PriorityQueue<>(new Comparator<Plan>() {
            @Override
            public int compare(Plan o1, Plan o2) {
                return o1.nowTime - o2.nowTime;
            }
        });
        
        // PQ에 세팅
        for (String[] arr : plans) {            
            Plan p = new Plan(arr[0], changedMinute(arr[1]) , Integer.parseInt(arr[2]));
            pq.add(p);
        }
        
        Plan first = pq.poll();
        while ( !(pq.isEmpty() && stack.isEmpty()) ) {
        // while ( idx == answer.length ) {
            
            if (!pq.isEmpty()) {
                Plan second = pq.poll();
                
                // 하던일이 다음일보다 빨리 끝날경우
                if (second.nowTime - first.nowTime >= first.remainingTime) {
                    answer[idx] = first.subjects;
                    idx++;

                    // 남은시간
                    int remainderTime = second.nowTime - first.nowTime - first.remainingTime;

                    while (remainderTime > 0 && !stack.isEmpty()) {
                        Plan job = stack.pop();

                        if (remainderTime - job.remainingTime >= 0) {
                            answer[idx] = job.subjects;
                            idx++;

                            remainderTime -= job.remainingTime;
                        } else {
                            job.remainingTime -= remainderTime;
                            remainderTime = 0;
                            stack.add(job);
                        }
                    }
                } else { // 하던일 중간에 다음일을 할 시간인 경우
                    first.remainingTime -= second.nowTime - first.nowTime;
                    stack.add(first);
                }
                
                if (idx == answer.length -1) {
                    answer[idx] = second.subjects;
                    idx++;
                }
                
                first = second;
            } else { // pq에는 값이 없고 스택에만 있는경우
                answer[idx] = first.subjects;
                idx++;
                
                while (!stack.isEmpty()) {
                    Plan second = stack.pop();
                
                    answer[idx] = second.subjects;
                    idx++;
                }
            }
        }
        
        
    
        return answer;
    }
    
    class Plan {
        public Plan() {};

        public Plan(String subjects, int nowTime, int remainingTime) {
            this.subjects = subjects;
            this.nowTime = nowTime;
            this.remainingTime = remainingTime;
        }

        String subjects; // 과목
        int nowTime; // 시작시간
        int remainingTime; // 수행시간
    }
    
    
    int changedMinute(String nowTime) {
        int minute = 0;
        String arr[] = nowTime.split(":");
        
        minute += ( Integer.parseInt(arr[0]) * 60 );
        minute += Integer.parseInt(arr[1]);
        
        return minute;
    }
}
