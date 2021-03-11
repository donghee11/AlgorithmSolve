package Feb_03;

import java.util.HashMap;

public class Solution_위장 {
	public int solution(String[][] clothes) {
        int answer = 0;
        ///////////////////////////
        
        HashMap<String,Integer> hm=new HashMap<>();
        for(int i=0; i<clothes.length; i++) {
        	hm.put(clothes[i][1], hm.getOrDefault(clothes[i][1], 0)+1);
        }
        
        //의상의 종류에 따라 옷의 가짓수가 나올것,
        answer=1;
        for(String key:hm.keySet()) {
        	answer*=(hm.get(key)+1);
        }
        answer-=1;
        //////////////////////////
        return answer;
    }
}
