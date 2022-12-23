import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, List<Music>> map = new HashMap<>();

        // 음악 별 리스트 담기
        for (int i = 0; i < genres.length; i++) {
            if (map.get(genres[i])  == null ) {
                List<Music> list = new ArrayList<>();
                list.add(new Music(i, plays[i]));
                map.put(genres[i], list);
                continue;
            }
            map.get(genres[i]).add(new Music(i, plays[i]));
        }

        int[][] check = new int[map.size()][3]; // 0 합, 1,2 :인덱스
        int index = 0;
        
        // 각 리스트 별 정렬 (많이 들은 횟수 , 인덱스)
        for (String key : map.keySet()) {
            List<Music> findList = map.get(key);

            if (findList.size() > 1) {
                Collections.sort(findList, (o1, o2) -> {
                    if (o1.play == o2.play) { // 들은 횟수가 동일 시 적은 인덱스 정렬
                        return o1.idx - o2.idx;
                    }

                    return o2.play - o1.play; // 많이 들은 횟수 정렬
                });
            }
            
            int totalPlay = 0; // 음악 당 전체 들은 횟수
            for (int i = 0; i < findList.size(); i++) {
                totalPlay += findList.get(i).play;
            }

            // 음악 당 리스트가 한개 이상일 때
            if (findList.size() > 1) {
                check[index][0] = totalPlay;
                check[index][1] = findList.get(0).idx;
                check[index][2] = findList.get(1).idx;

            } else {
                check[index][0] = totalPlay;
                check[index][1] = findList.get(0).idx;
                check[index][2] = -1;
            }
            index++;
        }
        
        // 토탈 들은 횟수 기준 정렬
        Arrays.sort(check, (o1, o2) -> {
            return o2[0] - o1[0];
        });

        
        List<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < check.length; i++) {
            answerList.add(check[i][1]);
            if (check[i][2] > -1) {
                answerList.add(check[i][2]);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}

class Music {
    int idx;
    int play;

    public Music(){}
    public Music(int idx, int play) {
        this.idx = idx;
        this.play = play;
    }
}
