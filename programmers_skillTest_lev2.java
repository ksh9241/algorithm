public class Main {
	static int[] answer = new int[2];
    public static void main(String[] args) throws IOException {
    	String s = "110010101001";
    	int depth = 0;
    	int zeroCount = 0;
    	while (!s.equals("1")) {
    		depth++;
    		int count = 0;
    		
    		// 0개수 세기.
    		for (int i = 0; i < s.length(); i++) {
    			if ("0".equals(s.substring(i,i + 1))) count++; 
    		}
    		
    		// 총 0 개수 세기
    		zeroCount += count;
    		
    		String replace = s.replaceAll("0", "");
    		s = Integer.toBinaryString(replace.length());
    	}
    	System.out.println(depth + " " + zeroCount);
    }
}