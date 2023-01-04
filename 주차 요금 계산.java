import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> map = new HashMap<>();
        int[][] timeArr = new int[10000][2];

        for (int i = 0; i < timeArr.length; i++) {
            timeArr[i][0] = -1;
        }

        for (int i = 0; i < records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            int number = Integer.parseInt(st.nextToken());
            String inout = st.nextToken();

            String[] split = time.split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);

            if ("IN".equals(inout)) {
                timeArr[number][0] = hour;
                timeArr[number][1] = minute;
            } else {
                int parkingTime = 0;
                parkingTime += (hour - timeArr[number][0]) * 60;
                parkingTime += minute - timeArr[number][1];

                timeArr[number][0] = -1;
                timeArr[number][1] = -1;

                map.put(number, map.getOrDefault(number, 0) + parkingTime);
            }
        }

        // 주차를 나가지 않는 경우 확인
        for (int i = 0; i < 10000; i++) {
            if (timeArr[i][0] > -1) {
                int parkingTime = 0;
                parkingTime += (23 - timeArr[i][0]) * 60;
                parkingTime += 59 - timeArr[i][1];

                map.put(i, map.getOrDefault(i, 0) + parkingTime);
            }
        }

        // 키로 정렬
        Object[] mapkey = map.keySet().toArray();
        Arrays.sort(mapkey);

        int[] answer = new int[map.size()];
        for (int i = 0; i < mapkey.length; i++) {
            int totalTime = map.get((Integer) mapkey[i]);
            int amount = 0;
            if (totalTime > fees[0]) {
                 amount = fees[1] + ((int)Math.ceil((float)(totalTime - fees[0]) / fees[2]) * fees[3]);    
            } else {
                amount = fees[1];
            }
            
            answer[i] = amount;
        }

        return answer;
    }
}
