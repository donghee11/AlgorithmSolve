package Feb_03;

import java.util.Arrays;
import java.util.HashMap;

public class Solution_완주하지못한선수 {
	public String solution(String[] participant, String[] completion) {
        String answer = "";
        /////////////////////
        Arrays.sort(participant);
        Arrays.sort(completion);
        for(int i=0; i<completion.length; i++) {
        	if(!participant[i].equals(completion[i])) {
        		//다르면, 해당 선수 return
        		answer=participant[i];
        		break;
        	}
        }
        if(answer.equals("")) answer=participant[participant.length-1];
        //////////////////////////
        
        
        /////////HashMap 사용법////////////////
        HashMap<String, Integer> hm =new HashMap<>();
        //participant에 있는 참가자들을 순서대로 집어넣기
        for(String player:participant) {
        	//getOrDefault: 해당 key 값이 존재하면, 그 value값 찾아서 1더하고 없으면 0+1=1 넣어주기,
        	//그냥 get 쓰면 중복체크가 안되고, 해당 player의 값이 1로 갱신되어버린다.
        	hm.put(player, hm.getOrDefault(participant, 0)+1);
        }
        for(String player:completion) {
        	hm.put(player, hm.get(player)-1); //player에 해당하는 key값을 찾아서 1씩 값 낮추기
        }
        for(String key:hm.keySet()) {
        	//모든 hm의 key들을 돌아다녔을때
        	if(hm.get(key)!=0)
        		answer=key; break;
        }
        return answer;
    }
}
