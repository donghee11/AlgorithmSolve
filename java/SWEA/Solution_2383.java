package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_2383 {
	static int N, map[][], answer;
	static ArrayList<Info> list;
	static int place[][];
	static int peopleCnt; //사람 명수
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			int idx=0;
			peopleCnt=0;
			answer=Integer.MAX_VALUE;
			list=new ArrayList<>();
			place=new int[2][2];
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						list.add(new Info(i,j,peopleCnt++));
					}else if(map[i][j]>=2) {
						place[idx][0]=i; place[idx][1]=j;
						idx++; //계단 위치 표시하기
					}
				}
				
			}//end of input
			visit=new boolean[peopleCnt];
			select(0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			
		}//end of tc
		System.out.println(sb.toString());
	}
	static boolean visit[];
	private static void select(int cnt) {
		if(cnt==peopleCnt) {
			//모든 사람에 대해 다 뽑았으면, 계산
			//비선택 된 애들을 첫번째 계단을 보낸다고 생각,
			int a=move(0,false);
			int b=move(1,true);
			
			int tmp=Math.max(a,b);
//			System.out.println(a+" "+b);
			answer=Math.min(answer, tmp);
			return;
		}
		//비선택 하는 경우
		select(cnt+1);
		//선택 하는 경우
		visit[cnt]=true;
		select(cnt+1);
		visit[cnt]=false;
	}
	private static int move(int n, boolean flag) {
		//비선택 애들부터 n번 계단으로 이동시키기
		ArrayList<Integer> q=new ArrayList<>();
		int r=place[n][0]; int c=place[n][1];
		for(int i=0; i<peopleCnt; i++)
			if(visit[i]==flag) {
				q.add(Math.abs(r-list.get(i).r)+Math.abs(c-list.get(i).c));
			}
		//자동으로 오름차순 정렬이 된다.
		//지체된거 다 더하면됨
		int time=0;
		Collections.sort(q);
		if(q.size()>0) time=q.get(q.size()-1)+1;
		for(int i=q.size()-1; i>=0; i-=3) {
			//자기보다 3개 전 사람과의 dist차이를
			if(i>=3) {
				int diff=q.get(i)-q.get(i-3);
				if(diff<map[r][c]) { //만약,계단 길이보다 그 텀이 더 짧으면 그만큼기다려야지
					time+=(map[r][c]-diff);
				}
			}
		}
		time+=map[r][c];
		int tmp=0;
		for(int i=q.size()-2; i>=0; i-=3) {
			//자기보다 3개 전 사람과의 dist차이를
			if(i>=3) {
				int diff=q.get(i)-q.get(i-3);
				if(diff<map[r][c]) { //만약,계단 길이보다 그 텀이 더 짧으면 그만큼기다려야지
					tmp+=(map[r][c]-diff);
				}
			}
		}
		tmp+=map[r][c];
		time=time>tmp?time:tmp;
		tmp=0;
		for(int i=q.size()-3; i>=0; i-=3) {
			//자기보다 3개 전 사람과의 dist차이를
			if(i>=3) {
				int diff=q.get(i)-q.get(i-3);
				if(diff<map[r][c]) { //만약,계단 길이보다 그 텀이 더 짧으면 그만큼기다려야지
					tmp+=(map[r][c]-diff);
				}
			}
		}
		time=time>tmp?time:tmp;
		return time;
	}
	
	private static class Info{
		//사람 정보
		int r, c, idx; //위치, 번호

		public Info(int r, int c, int idx) {
			super();
			this.r = r;
			this.c = c;
			this.idx = idx;
		}
		
	}
}
