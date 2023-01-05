class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        
        for (int i = 0; i < skill_trees.length; i++) {
            int idx = 0;
            boolean check = true;
            
            for (int j = 0; j < skill.length(); j++) {
                String s = skill.substring(j, j + 1);
                
                if (skill_trees[i].indexOf(s) > -1) { // 스킬트리에 스킬이 존재하면
                    
                    // 이전 스킬트리보다 우선순위이면 불가
                    if (skill_trees[i].indexOf(s) < idx) {
                        check = false;
                        break;
                    }
                    idx = skill_trees[i].indexOf(s);
                } else { // 값이 없을 때
                    idx = 30;
                }
            }
            
            if (check) { // 찍을 수 있는 스킬트리이면 answer 증가
                answer++;
            }
        }
        
        return answer;
    }
}
