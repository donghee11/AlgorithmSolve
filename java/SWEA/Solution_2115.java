package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2115 {
	static int N,M,C,answer,first_person,second_person;
	static int map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			
			map=new int[N][N];
			answer=0;
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			visit=new boolean[N];
			first_person=0; second_person=0;
			if(2*M<=N) { //하나의 가로줄에 가능한 경우엔,가로줄 하나 선택하는 경우 고려하기
				for(int i=0; i<N; i++) {
					select(i);
				}
			}
			//아니면 2개의 가로줄을 선택한다
			comb(0,0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}//end of main
	static boolean visit[];
	private static void comb(int cnt, int idx) {
		if(cnt==2) {
			calc();
			
			return; 
		}
		
		for(int i=idx; i<N; i++) {
			visit[i]=true;
			comb(cnt+1,i+1);
			visit[i]=false;
		}
	}
	private static void dfs(int r, int c, int cnt, int ans, int sum) {
		if(sum>C) return; //이 경우는 xx
		if(cnt==M) {
			first_person=Math.max(first_person, ans);
			return;
		}
		dfs(r, c+1, cnt+1, ans+map[r][c]*map[r][c], sum+map[r][c]);
		dfs(r,c+1,cnt+1,ans,sum); //선택 안하는 경우
		
	}
	private static void dfs2(int r, int c, int cnt, int ans, int sum) {
		if(sum>C) return; //이 경우는 xx
		if(cnt==M) {
			second_person=Math.max(second_person, ans);
			return;
		}
		dfs2(r, c+1, cnt+1, ans+map[r][c]*map[r][c], sum+map[r][c]);
		dfs2(r,c+1,cnt+1,ans,sum); //선택 안하는 경우
		
	}
	
	private static void calc() {
		//visit 가 i인 두개의 가로줄 선택
		first_person=0; second_person=0;
		int idx=0; int row[]=new int[2];
		for(int i=0; i<N; i++) {
			if(visit[i]) row[idx++]=i;
		}
		
		for(int i=0; i+M-1<N; i++) {
			for(int j=0; j+M-1<N; j++) {
				first_person=0; second_person=0;
				dfs(row[0],i,0,0,0);
				dfs2(row[1],j,0,0,0);
				answer=Math.max(answer, first_person+second_person);
			}
		}
	}
	private static void select(int r) {
		for(int i=0; i<N; i++) { //시작점이 i 일때
			for(int j=i+M; j<N; j++) {
				if(j+M-1>=N) break;
				
				first_person=0; second_person=0;
				dfs(r,i,0,0,0);
				dfs2(r,j,0,0,0);
				answer=Math.max(answer, first_person+second_person);
				
			}
		}
	}
}
