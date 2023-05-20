 public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int pointer = 1;
        int index = 0;
        
        while (pointer <= n) {
            if (index < stations.length && pointer >= stations[index] - w) {
                pointer = stations[index] + w + 1;
                index++;
                continue;
            }
            answer++;
            pointer += (2 * w) + 1;
        }

        return answer;
    }
