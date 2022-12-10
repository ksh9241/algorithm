class Solution {
    String[] arr = {"Rr","RR","Rr","Rr","rr"};

    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int depth = queries[i][0];
            int index = queries[i][1];
            answer[i] = find(depth, index);
        }
        return answer;
    }

    private String find(int depth, int idx) {
        String answer = "";
        if (depth == 1) {
            return "Rr";
        }
        if (depth == 2) {
            return arr[idx];
        }
        String type = find(depth - 1, idx % 4 == 0 ? idx / 4 : (idx / 4) + 1);
        if ("Rr".equals(type)) {
            if (idx % 4 == 0) {
                answer = "rr";
            } else if (idx % 4 == 1) {
                answer = "RR";
            } else {
                answer = "Rr";
            }
        } else {
            answer = type;
        }
        return answer;
    }
}