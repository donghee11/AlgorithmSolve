package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17822 {
	private static int N, M, T;
	private static int map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken()); //N개의 원판에 M개의 숫잘ㄹ T번 돌린다
		
		map=new int[N+1][M];
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}//end of input
		
		for(int tc=1; tc<=T; tc++) {
			//x원판을 d방향으로 k만큼 회전시킨다!
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			rotate(x,d,k);
			//원판에 수가 남아있으면, 인접하면서 수가 같은것을 모두 찾는다.
			erase();
		}

		System.out.println(calculate());
	}
	private static int calculate() {
		int answer=0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M; j++) {
				answer+=map[i][j];
			}
		}
		return answer;
	}
	private static void erase() {
		//동시에 일어나는일, 바로 지우지말고, list안에 보관해두고 지우자.
		Queue<int[]> q=new LinkedList<>();
		//1.같은 원판내에서 확인하기
		int totalsum=0; //전체 합
		int totalcnt=0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<M-1; j++) {
				totalsum+=map[i][j];
				if(map[i][j]==0) continue; //0인곳은 볼필요가 없어
				totalcnt++;
				if(map[i][j]==map[i][j+1]) {
					//같은 숫자면 넣기
					q.add(new int [] {i,j});
					q.add(new int [] {i,j+1});
				}
			}
			totalsum+=map[i][M-1];
			if(map[i][M-1]!=0) {
				totalcnt++;
				if(map[i][M-1]==map[i][0]) {
					q.add(new int [] {i,0});
					q.add(new int [] {i,M-1});
				}
			}
		}
		//2. 전 원판이랑 같으닞 확인하기
		//모든 열에 대해 조사
		for(int i=0; i<M; i++) {
			for(int j=2; j<=N; j++) {
				if(map[j][i]!=0) {
					if(map[j][i]==map[j-1][i]) {
						q.add(new int[] {j,i});
						q.add(new int[] {j-1,i});
					}
				}
			}
		}
		double avg=(double)totalsum/totalcnt;
		if(q.size()==0) {
			for(int i=1; i<=N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==0) continue;
					if(map[i][j]>avg) map[i][j]-=1;
					else if(map[i][j]<avg) map[i][j]+=1;
				}
			}
		}
		//q에 들어간것들 싹 0으로 바꾸기
		while(!q.isEmpty()) {
			int []p=q.poll();
			map[p[0]][p[1]]=0;
		}
	}
	private static void rotate(int c, int d, int k) {
		
		for(int x=c; x<=N; x+=c) {
			if(d==1) { //시계 방향
				//돌릴원판은 map[x]에 해당하는 원판이다
				int tmp[]=new int[M];
				for(int i=M-1; i>=0; i--) {
					if(i-k>=0) {
						tmp[i-k]=map[x][i];
					}else {
						tmp[i-k+M]=map[x][i];
					}
				}
				//최종적으로 map에 복사
				for(int i=0; i<M; i++)
					map[x][i]=tmp[i];
			}else { //반시계 방향
				int tmp[]=new int[M];
				for(int i=0; i<M; i++) {
					if(i+k>=M) {
						tmp[i+k-M]=map[x][i];
					}else {
						tmp[i+k]=map[x][i];
					}
				}
				for(int i=0; i<M; i++)
					map[x][i]=tmp[i];
			}
		}
	}
}
