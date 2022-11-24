class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int[] arr = new int[26]; // 알파벳 담을 배열

        for (int i = 0; i < survey.length; i++) {
            calculrator(survey[i], choices[i], arr);
        }
        answer = calculAnswdr(arr);
        return answer;
    }

    static void calculrator(String type, int number, int[] arr) {
        int[] score = {0,3,2,1,0,1,2,3};
        char left = type.charAt(0);
        char right = type.charAt(1);

        if (number / 4 >= 1) {
            arr[right - 65] += score[number];
        } else {
            arr[left - 65] += score[number];
        }
    }

    static String calculAnswdr(int[] arr) {
        StringBuilder answer = new StringBuilder();
        int[][] setType = {{'R','T'}, {'C','F'}, {'J','M'}, {'A','N'}};

        for (int i = 0; i < 4; i++) {
            int left = setType[i][0] - 65;
            int right = setType[i][1] - 65;

            if (arr[left] >= arr[right]) {
                char val = (char) setType[i][0];
                answer.append(String.valueOf(val));
            } else {
                char val = (char) setType[i][1];
                answer.append(String.valueOf(val));
            }
        }

        return answer.toString();
    }
}

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        for (int i = 0; i < babbling.length; i++) {
            String check = babbling[i].replaceAll("aya", "1")
                    .replaceAll("ye","2")
                    .replaceAll("woo","3")
                    .replaceAll("ma","4");

            try {
                boolean flag = true;
                int replace = Integer.parseInt(check);
                for (int j = 0; j < check.length() - 1; j++) {
                    if (check.charAt(j) == check.charAt(j + 1)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    answer++;
                }
            } catch( NumberFormatException e) {
                continue;
            }
        }
        return answer;
    }
}