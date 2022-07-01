public class Solution {
    public String solution(int num) {
        String answer = String.valueOf(num);
    	answer = answer.replaceAll("0", "zero")
						.replaceAll("1", "one")
						.replaceAll("2", "two")
						.replaceAll("3", "three")
						.replaceAll("4", "four")
						.replaceAll("5", "five")
						.replaceAll("6", "six")
						.replaceAll("7", "seven")
						.replaceAll("8", "eight")
						.replaceAll("9", "nine")
						;

        return answer;
    }
}

class Solution {
    public int solution(int[] prices) {
        int answer = 0;
    	int max = Integer.MIN_VALUE;
    	int min = prices[0];
    	
    	boolean visit = false;
    	
    	for (int i = 0; i < prices.length; i++) {
    		if (!visit && min < prices[i]) {
    			visit = true;
    		}
    		if (max < prices[i]) max = prices[i];
    		else if (min > prices[i]) min = prices[i];
    	}
    	if (visit) answer = max - min;
        return answer;
    }
}

class Solution
{
    public long[] solution(long n)
    {
        long []answer = {0,0};
        long val = (n / 7) * 2;
    	long min = val;
    	long max = val;
    	if (n % 7 > 0) {
            if (n % 7 < 6) {
                min += 0;
            }else {
                min += 1;
            }
    		max += 2;
    	}
        answer[0] = min;
        answer[1] = max;
        return answer;
    }
}

class Solution
{
    static boolean[][] visit;
	static int count = 0;
	static int max = 0;

    static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
    public int[] solution(int[][] v)
    {
        visit = new boolean[v.length][v[0].length];
        int result = 0;
        for (int i = 0; i < v.length; i++) {
    		for (int j = 0; j < v[i].length; j++) {
    			if (!visit[i][j] && v[i][j] == 1) {
    				count++;
    				visit[i][j] = true;
    				max++;
    				dfs(i, j, v);
    				result = Math.max(result, max);
    				max = 0;
    			}
    		}
    	}

        int[] answer = new int[2];
        answer[0] = count;
        answer[1] = result;
        return answer;
    }

    static void dfs(int x, int y, int[][] v) {
    	for (int i = 0; i < 4; i++) {
    		int nxtX = x + dx[i];
    		int nxtY = y + dy[i];
    		
    		if (check(nxtX, nxtY, v)) {
    			max++;
    			visit[nxtX][nxtY] = true;
    			dfs(nxtX, nxtY, v);
    		}
    	}
    }
    
    static boolean check(int x, int y, int[][]v) {
    	if (x >= v.length || x < 0 || y < 0 || y >= v[0].length || visit[x][y] || v[x][y] == 0) {
    		return false;
    	}
    	return true;
    }
}