import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int lastTime = attacks[attacks.length - 1][0];
        
        int nowHealth = health;
        int attacksIdx = 0;
        int continuitySuccess = 0;
        int idx = 0;
        
        while (true) {
            if (attacksIdx == attacks.length) {
                break;
            }
            
            idx++;
            int attackTime = attacks[attacksIdx][0];
            int attackDamage = attacks[attacksIdx][1];
            
            if (idx == attackTime) {
                nowHealth -= attackDamage;
                continuitySuccess = 0; // 연속성공 초기화
                attacksIdx++;
            } else { // 공격시간이 아닐경우
                continuitySuccess++;
                if (nowHealth < health) {
                    nowHealth += bandage[1];
                    nowHealth = Math.min(nowHealth, health);
                }
                
                if (continuitySuccess == bandage[0]) {
                    nowHealth += bandage[2];
                    continuitySuccess = 0;
                    nowHealth = Math.min(nowHealth, health);
                }
            }
            
            if (nowHealth <= 0) {
                nowHealth = -1;
                break;
            }
        }
        
        answer = nowHealth;
        if (nowHealth < 0) {
            answer = -1;
        }
        
        return answer;
    }
}
