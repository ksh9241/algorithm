class Solution {
    String w;
    int answer = 0;
    int count = 0;
    String[] alphabet = {"A", "E", "I", "O", "U"};
    public int solution(String word) {
        w = word;
        dfs("", 0);
        return answer;
    }
    
    void dfs(String text, int depth) {
        if (w.equals(text)) {
            answer = count;
            return;
        }
        
        if (depth == 5) {
            return;      
        }
        
        for (int i = 0; i < 5; i++) {
            count++;
            dfs(text + alphabet[i], depth + 1);
        }
    }
}
