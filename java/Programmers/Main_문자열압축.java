package May_01;

public class Main_문자열압축 {
	public static void main(String[] args) {
		System.out.println(solution("aabbaccc"));
	}
	
	
	private static int solution(String s) {
		int answer=s.length();
		int length=s.length();
		for(int len=2; len<length/2; len++) {
			//길이가 len일 때,
			String tmp=s.substring(0,len);
			String newstr="";
			int idx=0;
			for(int i=len; i+len<length; i+=len) {
				String st=s.substring(i,i+len);
				if(st.equals(tmp)) {
					idx++;
				}else {
					if(idx==1) newstr+=tmp;
					else {
						if(idx<10) newstr+="1";
						else if(idx<100) newstr+="11";
						else if(idx<1000) newstr+="111";
						newstr+=tmp;
					}
					tmp=st;
					idx=1;
				}
			}
			if(idx==1) newstr+=tmp;
			else {
				if(idx<10) newstr+="a";
				else if(idx<100) newstr+="aa";
				else if(idx<1000) newstr+="aaa";
				newstr+=tmp;
			}

			answer=Math.min(answer, newstr.length());
		}
		return answer;
	}
}
