package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//1초가 지나갈 때 마다 해야할일 : R연산 수행할지, C 연산 수행할지 결정
//행, 열에 대해 각각 arraylist를 만들어야할듯  정렬은?
//정렬 기준 1. 수의 횟수 -> 같으면 수의 오름차순으로
//i번째 행에 
public class Main_17140 {
	private static class Pair implements Comparable<Pair>{
		int n, cnt;
		public Pair(int n, int cnt) {
			super();
			this.n = n;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.cnt==o.cnt) return this.n-o.n;
			return this.cnt-o.cnt;
		}
		
	}
	private static int r,c,k;
	private static int map[][];
  private static int r_num;
  private static int c_num;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken())-1;
		c=Integer.parseInt(st.nextToken())-1;
		k=Integer.parseInt(st.nextToken());
		map=new int[101][101]; //길이 최대 100. 넉넉하게 +1
		
		for(int i=0; i<3; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int t=0; int answer=0;
		r_num=3; c_num=3; //초기에 열과 행의 길이
		while(true) {
			if(t>100) {
				answer=-1; break;
			}
			if(map[r][c]==k) {
				answer=t; break;
			}
			if(r_num>=c_num) { //R연산
				R();
			}else { //C연산
				C();
			}
//			System.out.println(t+" "+r_num+" "+c_num);
//			print();
			t++;
		}//만족할 때 까지 반복
		System.out.println(answer);
	}
  
	private static void C() {
		int tmpr_num=0;
		for(int j=0; j<c_num; j++) {
			Pair count[]=new Pair[101]; 
			for(int i=0; i<101; i++) count[i]=new Pair(i,101);
			for(int i=0; i<r_num; i++) {
				if(map[i][j]==0) continue;
				int cnt=count[map[i][j]].cnt;
				if(cnt==101) count[map[i][j]].cnt=1; //처음 참조된 경우
				else count[map[i][j]].cnt+=1; //처음참조된거 아닌 경우
			}
			Arrays.sort(count);
			int idx=0; 
			int tmp=0;
			for(int i=0; i<r_num; i++) { //최대 c_num개 일수밖에 없음
				if(count[i].cnt==101) {
					map[tmp++][j]=0; map[tmp++][j]=0;
					continue;
				}
				//아니라면, 넣어주기
				map[idx++][j]=count[i].n;
				map[idx++][j]=count[i].cnt;
				tmp=idx;
			}
			tmpr_num=Math.max(tmpr_num, idx);
		}
		r_num=tmpr_num; //최종 값 갱신
	}
	private static void R() { //R연산
		int tmpc_num=0;
		for(int i=0; i<r_num; i++) {
			//i번째 열에서 정렬을 수행해보자.
			//우선 탐색하는 방법에서 시간을 줄이긴 불가능함
			Pair count[]=new Pair[101]; 
			for(int j=0; j<101; j++) count[j]=new Pair(j,101);
			for(int j=0; j<c_num; j++) {
				if(map[i][j]==0) continue;
				int cnt=count[map[i][j]].cnt;
				if(cnt==101) count[map[i][j]].cnt=1; //처음 참조된 경우
				else count[map[i][j]].cnt+=1; //처음참조된거 아닌 경우
			}
			Arrays.sort(count);
			int idx=0;
			int tmp=0;
			for(int j=0; j<c_num; j++) { //최대 c_num개 일수밖에 없음
				if(count[j].cnt==101) {//이 때는 그냥 default 값
					map[i][tmp++]=0;
					map[i][tmp++]=0; continue;
				}
				//아니라면, 넣어주기
				map[i][idx++]=count[j].n;
				map[i][idx++]=count[j].cnt;
				tmp=idx;
			}
			//줄어든 곳도 있을수 있다! 이 경우는 나머지들 다 map 0으로 바꿔줘야햄
			tmpc_num=Math.max(tmpc_num, idx);
		}
		c_num=tmpc_num; //최종 값 갱신
	}
	
	
}
