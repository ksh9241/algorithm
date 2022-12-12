import java.util.*;
class Solution {
    int count = 0;
    public long[] solution(int[][] program) {
        long[] answer = new long[11];

        Arrays.sort(program, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            return o1[0] - o2[0];
        });

        int time = 0;
        q.add(program[0]);
        count++;

        int number = 0;
        int inTime = 0;
        int endTime = 0;
        int waitTime = 0;
        while (!q.isEmpty()) {
            int[] pollProgram = q.poll();
            number = pollProgram[0];
            inTime = pollProgram[1];
            endTime = pollProgram[2];
            waitTime = time - inTime;

            for (int i = 0; i < endTime; i++) {
                q = inputTimeAdd(time, program, q);
                time++;
            }
            answer[number] += waitTime;
        }
        answer[0] = time;

        return answer;
    }

    PriorityQueue<int[]> inputTimeAdd(int time, int[][] program, PriorityQueue<int[]> q) {
        int qSize = q.size();
        for(int i = count; i < program.length; i++) {
            if (time == program[i][1]) {
                q.add(program[i]);
                count++;
            }
            if (time < program[i][1]) {
                break;
            }
        }
        return q;
    }
}