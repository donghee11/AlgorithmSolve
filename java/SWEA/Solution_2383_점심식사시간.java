package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2383_점심식사시간 {
	private static int N;
	private static int map[][], answer;
	private static Stair stair[]; //계단은 2개
	private static ArrayList<Person> person;
	private static int peopleCnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			StringTokenizer st;
			map=new int[N][N];
			stair=new Stair[3];
			int idx=1;
			answer=Integer.MAX_VALUE;
			person=new ArrayList<>();
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						person.add(new Person(i,j,false));
					}else if(map[i][j]>=2) {
						stair[idx]=new Stair(i,j,map[i][j]);
						idx++; //stair 1번, 2번 선택하는 것
					}
				}
			}//end of input
			//어떤 계단 select할건지 배열 만들기
			peopleCnt=person.size();
			select=new int[peopleCnt];
			//모두 1또는 2로 마킹하기
			dfs(0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}
	
	private static void dfs(int cnt) {
		if(cnt==peopleCnt) {
			int tmp_time=Math.max(calc(1), calc(2));
			answer=Math.min(answer, tmp_time);
			return;
		}
		select[cnt]=1;
		dfs(cnt+1);
		
		select[cnt]=2;
		dfs(cnt+1); //얘는 어차피 다시 덮어져서 되돌리진 않아도 된다, 
	}

	private static int calc(int index) {
		///index번 계단을 선택한 애들
		 ArrayList<Integer> list=new ArrayList<>();
		 for(int i=0; i<peopleCnt; i++) {
			 if(select[i]==index) {
				 int dist=Math.abs(stair[index].r-person.get(i).r)+Math.abs(stair[index].c-person.get(i).c);
				 list.add(dist+1); //출발+도착시간
			 }
		 }
		Collections.sort(list);
		int time=0;
		int cnt=0; //현재 큐에 있는 사람 명수
		int listSize=list.size();
		int totalCnt=0;
		Queue<Integer> q=new LinkedList<>();
		while(true) {
			time++;
			//내려가기
			int size=q.size();
			for(int i=0; i<size; i++) {
				int x=q.poll();
				if(x+stair[index].len==time) {
					cnt--;
					totalCnt++;
					continue;
				}
				q.add(x);
			}
			//계단에 태우기
			while(list.size()!=0) {
				if(cnt<3 && list.get(0)<=time) {
					//현재 큐에 3보다 작고, 거리 순으로 현재 시간보다 안전하게 들어올수 있는애면;
					q.add(time);
					cnt++;
					list.remove(0); 
				}else break;
			}
			if(totalCnt==listSize) {
				return time;
			}
		}
	}

	private static int[]select;
	private static class Stair{
		int r, c;
		int len;
		public Stair(int r, int c, int len) {
			super();
			this.r = r;
			this.c = c;
			this.len = len;
		}
		
	}
	private static class Person{
		int r,c;
		boolean finish; //내려갔는지
		public Person(int r, int c, boolean finish) {
			super();
			this.r = r;
			this.c = c;
			this.finish = finish;
		}
		
	}
}
