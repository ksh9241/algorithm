1 (8/10)
import java.util.*;

class Solution {
    public int solution(int[] levels) {
        int answer = -1;

        Arrays.sort(levels);
        float persent = (100f / levels.length);

        float totalPersent = 0;
        for (int i = levels.length - 1; i >= 0; i--) {
            totalPersent += persent;
            if (totalPersent <= 25) {
                answer = levels[i];
            } else {
                break;
            }
        }
        return answer;
    }
}


2 (정답)
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = -1;



            if (s.indexOf("999") > -1) {
                answer = 999;
            } else if (s.indexOf("888") > -1) {
                answer = 888;
            } else if (s.indexOf("777") > -1) {
                answer = 777;
            } else if (s.indexOf("666") > -1) {
                answer = 666;
            } else if (s.indexOf("555") > -1) {
                answer = 555;
            } else if (s.indexOf("444") > -1) {
                answer = 444;
            } else if (s.indexOf("333") > -1) {
                answer = 333;
            } else if (s.indexOf("222") > -1) {
                answer = 222;
            } else if (s.indexOf("111") > -1) {
                answer = 111;
            } else if (s.indexOf("000") > -1) {
                answer = 0;
            } 


        return answer;
    }
}

3 (14/20)
import java.util.*;

class Solution {
    public boolean solution(String amountText) {
        boolean answer = true;

        String[] split = amountText.split(",");
        System.out.println(Arrays.toString(split));

        for (int i = 0; i < split.length; i++) {
            boolean flag = false;
            if (i == 0) {
                flag = numberCheck(split[i]);
                
                if (flag) {
                    flag = firstZeroNumCheck(split[i]);
                }

                if (flag && split.length > 1) {
                    flag = threeNumCheck(split[i], true);
                }
            } else {
                flag = numberCheck(split[i]);
                
                if (flag) {
                    flag = threeNumCheck(split[i], false);
                }
            }

            if (!flag) {
                answer = flag;
                break;
            }
        }

        return answer;
    }
    public boolean numberCheck(String s) {
        boolean result = false;

        try {
            Integer.parseInt(s);
            result = true;
        } catch(Exception e) {

        }

        return result;
    }
    
    public boolean firstZeroNumCheck(String s) {
        return !"0".equals(s.substring(0, 1));
    }

    public boolean threeNumCheck (String s, boolean firstYn) {
        boolean result = false;
        
        if (!firstYn && s.length() == 3) {
            result = true;
        }

        if (firstYn && s.length() <= 4) {
            result = true;
        }

        return result;
    }

    
    
}

5 (8/10)
import java.lang.Math;

class Solution {
    public long solution(long orderAmount, long taxFreeAmount, long serviceFee) {
        
        long answer = 0;
        long num = orderAmount - taxFreeAmount - serviceFee;

        if (num != 1) {
            answer = (long) Math.ceil(num / 10);
        }

        return answer;
    }
}

