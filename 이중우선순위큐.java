import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        // 최소값 담기
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o1 - o2;
        });
        
        // 최대값 담기
        PriorityQueue<Integer> reversPq = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        
        for (int i = 0; i < operations.length; i++) {
            operation(pq, reversPq, operations[i]);
        }
        
        
        if (pq.size() > 0) {
            // 큐에 하나만 쌓여있는 경우
            if (pq.size() == 1) {
                int num = pq.poll();
                if (num > 0) {
                    answer[0] = num;
                } else {
                    answer[1] = num;
                }
            }
            else {
                answer[1] = pq.poll();
                int num = 0;
                int size = pq.size();
                for (int i = 0; i < size; i++) {
                    num = pq.poll();
                }
                answer[0] = num;
            }
        }
        
        return answer;
    }
    
    public void operation(PriorityQueue<Integer> pq, PriorityQueue<Integer> reversPq, String operation) {
        String[] split = operation.split(" ");
        int num = Integer.parseInt(split[1]);
        
        switch(split[0]) {
            case "I" :
                pq.add(num);
                reversPq.add(num);
                break;
            case "D" :
                // 최댓값 삭제
                if (num > 0) {
                    if (pq.size() > 0) {
                        pq.remove(reversPq.poll());
                    }
                }
                else { // 최솟값 삭제
                    if (pq.size() > 0) {
                        pq.poll();    
                    }
                }
                break;
        }
    }
}
