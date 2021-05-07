import java.util.*;

class Solution {
    public int solution(String s) {
        int answer=s.length();
		int length=s.length();
		for(int len=1; len<=length/2; len++) {
			//길이가 len일 때,
			String tmp=s.substring(0,len);
			String newstr="";
			int idx=1;
			for(int i=len; i<length; i+=len) {
				String st="";
				if(i+len>=length) st=s.substring(i);
				else st=s.substring(i,i+len);
				if(st.equals(tmp)) {
					idx++;
				}else {
					if(idx<=1) newstr+=tmp;
					else {
						newstr+=idx;
						newstr+=tmp;
					}
					tmp=st;
					idx=1;
				}
			}
			if(idx<=1) newstr+=tmp;
			else {
				newstr+=idx;
				newstr+=tmp;
			}
			answer=Math.min(answer, newstr.length());
		}
		return answer;
    }
}
