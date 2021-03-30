package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//사다리조작
public class Main_15684 {
	private static int N, M, H,answer; //세로선, 가로선, 세로선마다 가로선 놓을수있는 위치 
	private static boolean flag[][]; //해당 가로, 세로가 만나는 지점에 선이있냐?
	private static int cnt[]; //i번째 세로줄에 현재 가로줄 몇개있는지? 무조건 짝수여야해
	public static void main(String[] args) throws IOException {
		//
		answer=4;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken()); ///현재 세워져있는 정보갯수
		H=Integer.parseInt(st.nextToken());
		cnt=new int[N+1];
		flag=new boolean[H+1][N+1]; //h라는 세로줄에, n으로 시작하는 가로줄 놓냐
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken()); //a번 위치에서!
			int b=Integer.parseInt(st.nextToken());//b와 b+1를 연결했어
			flag[a][b]=true;
			cnt[b]+=1;
		}
		int count=0;
		for(int i=1; i<N; i++) {
			if(cnt[i]%2==1)count++;
		}
		if(count>3) answer=-1;
		else setLine(0,1);
		if(answer==4) answer=-1;
		System.out.println(answer);
	}
	
	private static void setLine(int ans, int idx) {
		if(ans>3) return;
		if(isPossible()) {
			answer=Math.min(ans, answer);
			return; //조건 만족하면 끝내면 된다.
		}
		//조건 만족 안하면 모든 가로선 다 세워보기 가능 한곳에 최대 3개까지만
		for(int i=idx; i<=H; i++) {
			for(int j=1; j<N; j++) {
				if(flag[i][j]) continue;
				if(!flag[i][j-1] && !flag[i][j+1]) {
					flag[i][j]=true;
					setLine(ans+1, i);
					flag[i][j]=false;
				}
			}
		}
		
	}
	
	private static boolean isPossible() {
	//사다리 되는지 확인하기
		for(int i=1; i<=N; i++) { //i에서 출발해서 i로 갈 수 있는지
			int cur=i; //현재 위치 표시
			for(int j=1; j<=H; j++) {
				if(flag[j][cur]) {
					cur+=1;
				}else if(flag[j][cur-1]) {
					cur-=1;
				}
			}
			if(cur!=i) return false;
		}
		return true;
	}
}
