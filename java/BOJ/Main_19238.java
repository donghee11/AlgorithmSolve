package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19238 {
	private static class Person implements Comparable<Person>{
		int start_r, start_c, end_r, end_c;
		public Person(int start_r, int start_c, int end_r, int end_c) {
			super();
			this.start_r = start_r;
			this.start_c = start_c;
			this.end_r = end_r;
			this.end_c = end_c;
		}
		@Override
		public int compareTo(Person o) {
			if(this.start_r==o.start_r) return this.start_c-o.start_c;
			return this.start_r-o.start_r;
		}
	}
	private static class Taxi{
		int r, c, cnt; //택시 현 위치, 연료량
		public Taxi(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	private static int N, M, L; // 맵크기, 손님수, 연료량
	private static int map[][];
	private static ArrayList<Person> list;
	private static Person person[];
	private static Taxi taxi;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) map[i][j]=-1; //벽으로 만들기
			}
		}
		st=new StringTokenizer(br.readLine());
		taxi=new Taxi(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, L);
		person=new Person[M+1];
		for(int i=1; i<=M; i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken())-1;
			int w=Integer.parseInt(st.nextToken())-1;
			map[x][y]=i; 
			person[i]=new Person(x,y,z,w);
		}//end of input
		int answer=0;
		for(int i=0; i<M; i++) {
			if(findMinDist()) {
				if(moveDist()) answer=taxi.cnt;
				else {
					answer=-1; break;
				}
			}else {
				answer=-1; break;
			}
		}
		System.out.println(answer);
		
	}
	private static int []dr= {1,-1,0,0};
	private static int []dc= {0,0,1,-1};
	private static boolean findMinDist() {
		//승객의 현 위치에서 bfs 시작하기
		Queue<Taxi> q=new LinkedList<>();
		list=new ArrayList<>();
		//만약 승객을 만나면 list에 보관하자
		boolean visit[][]=new boolean[N][N];
		q.offer(new Taxi(taxi.r, taxi.c, taxi.cnt));
		visit[taxi.r][taxi.c]=true;
		int cnt=taxi.cnt;
		while(!q.isEmpty()) {
			int qsize=q.size();
			while(qsize-->0) {
				Taxi p=q.poll();
				int r=p.r; int c=p.c; cnt=p.cnt;
				if(map[r][c]>=1) { //손님이 있는경우
					int n=map[r][c];
					list.add(new Person(person[n].start_r, person[n].start_c, person[n].end_r, person[n].end_c));
				}
				for(int i=0; i<4; i++) {
					int nr=r+dr[i]; int nc=c+dc[i];
					if(nr>=0 && nc>=0 && nr<N && nc<N && !visit[nr][nc] && map[nr][nc]!=-1 && cnt>=1) {
						q.offer(new Taxi(nr,nc,cnt-1));
						visit[nr][nc]=true;
					}
				}
			}
			if(list.size()>0) {
				Collections.sort(list);
				taxi.cnt=cnt;
				//거기에 있는 첫번째 손님 자리로 택시를 이동시키고
				Person p=list.get(0);
				taxi.r=p.start_r; taxi.c=p.start_c;
				return true;
			}
			
		}
		return false; 
	}
	
	
	private static boolean moveDist() {
		//택시 위치에서 시작해서 그 위치 값의 손님을 그 손님의 목적지에 데려다주자
		Queue<Taxi> q=new LinkedList<>();
		q.offer(taxi);
		boolean visit[][]=new boolean[N][N];
		visit[taxi.r][taxi.c]=true;
		int index=map[taxi.r][taxi.c]; //손님 번호
		map[taxi.r][taxi.c]=0;
		int dist_r=person[index].end_r; int dist_c=person[index].end_c; //목적지에 도착하면 끝
		while(!q.isEmpty()) {
			Taxi p=q.poll();
			int r=p.r; int c=p.c; int cnt=p.cnt;
			if(r==dist_r && c==dist_c) {
				taxi.cnt+=(taxi.cnt-cnt);
				taxi.r=r; taxi.c=c;
				return true;
			}
			for(int i=0; i<4; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(nr>=0 && nc>=0 && nr<N && nc<N && !visit[nr][nc] && map[nr][nc]!=-1 && cnt>=1) { //빈칸으로만이동
					q.offer(new Taxi(nr,nc,cnt-1));
					visit[nr][nc]=true;
				}
			}
		}
		return false;
		
	}
}
