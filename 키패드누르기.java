import java.util.*;
class Solution {
    // 0 ~ 9 까지 사용하는 손
    String[] checkHand = new String[]{"C", "L", "C", "R", "L", "C", "R", "L", "C", "R"};
    Map<Integer, int[]> map = new HashMap<>();
    
    int[] leftLocation = new int[]{3, 0};
    int[] rightLocation = new int[]{3, 2};
    
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        
        
        initKeyPad();
        
        
        for (int i = 0; i < numbers.length; i++) {
           String handVal = checkHand[numbers[i]];
            
           if ("C".equals(handVal)) {
              String resultVal = calculHandVal(map.get(numbers[i]), hand);
              sb.append(resultVal);
           } else if ("R".equals(handVal)) {
               sb.append(handVal);
               rightLocation = map.get(numbers[i]);
           } else {
               sb.append(handVal);
               leftLocation = map.get(numbers[i]);
           }
        }
        
        String answer = sb.toString();
        return answer;
    }
    private String calculHandVal (int[] btnLocation, String hand) {
        int leftVal = Math.abs(leftLocation[0] - btnLocation[0]) + Math.abs(leftLocation[1] - btnLocation[1]);
        int rightVal = Math.abs(rightLocation[0] - btnLocation[0]) + Math.abs(rightLocation[1] - btnLocation[1]);
        
        String result = "";
        
        if (rightVal == leftVal) {
            if ("left".equals(hand)) {
                result = "L";
                leftLocation = btnLocation;
            } else {
                result = "R";
                rightLocation = btnLocation;
            }
            
        } else if (rightVal < leftVal) {
            result = "R";
            rightLocation = btnLocation;
        } else {
            result = "L";
            leftLocation = btnLocation;
        }
        
        return result;    
    }
    
    private void initKeyPad() {
        map.put(1, new int[]{0,0});
        map.put(2, new int[]{0,1});
        map.put(3, new int[]{0,2});
        map.put(4, new int[]{1,0});
        map.put(5, new int[]{1,1});
        map.put(6, new int[]{1,2});
        map.put(7, new int[]{2,0});
        map.put(8, new int[]{2,1});
        map.put(9, new int[]{2,2});
        map.put(0, new int[]{3,1});
    }
}
