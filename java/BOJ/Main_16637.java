package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//괄호추가하기
public class Main_16637 {
	private static int N,answer=Integer.MIN_VALUE;
	private static char map[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		String str=br.readLine();
		map=new char[N];
		for(int i=0; i<N; i++) {
			map[i]=str.charAt(i);
		}//end of input
		
		dfs(map[0]-'0',1,false); //처음엔 괄호 없어도댐,
		System.out.println(answer);
	}
	
	private static void dfs(int sum,int idx,boolean flag) { //flag는 괄호유무
		if(idx==N) {
			answer=Math.max(answer, sum);
			return;
		}
		if(flag) { //괄호존재
			int tmp=cal(map[idx-1]-'0', map[idx+1]-'0', map[idx]);
			if(idx-2>=0)dfs(cal(sum,tmp,map[idx-2]),idx+2,false);
		}else {
			//괄호가 없으면 그냥 그 전꺼랑 계산하기
			int tmp=cal(sum,map[idx+1]-'0',map[idx]);
			dfs(tmp, idx+2, false);
			//괄호 만들기
			if(idx+2<=N-1) dfs(sum,idx+2,true);
		}
	}
	private static int cal(int a, int b, char op) {
		switch(op) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		}
		return 0;
	}
}
