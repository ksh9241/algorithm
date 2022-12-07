class Solution {
    public String solution(String input_string) {
        String answer = "";
        int[] arr = new int[26];
        int[] visit = new int[26];

        char preChar = input_string.charAt(0);
        arr[(int)preChar - 97]++;
        int count = 1;
        for (int i = 1; i < input_string.length(); i++) {
            char c = input_string.charAt(i);
            if (preChar == c) {
                arr[(int)c - 97]++;
                count++;
                continue;
            } else {
                if (count > 1) {
                    visit[(int)preChar - 97]++;
                    arr[(int) c - 97]++;
                } else {
                    arr[(int)c - 97]++;
                }
            }
            preChar = c;
            count = 1;
        }
        if (count > 1) {
            visit[(int) preChar - 97]++;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 1) {
                if (visit[i] > 1 || visit[i] == 0) {
                    char c = (char) (i + 97);
                    answer+= String.valueOf(c);
                }
            }
        }
        if (answer.length() == 0) {
            answer = "N";
        }
        return answer;
    }
}