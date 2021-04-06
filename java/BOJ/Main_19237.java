package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_19237 {
	private static int N, M, K; //N*N, 상어 M마리, 냄새 K
	private static Shark shark[];
	private static Smell map[][];
	private static int sharkCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		shark=new Shark[M+1]; //1~M번
		map=new Smell[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int x=Integer.parseInt(st.nextToken());
				if(x!=0) { //0이 아니라면, 해당 위치에 상어가 존재한다.
					shark[x]=new Shark(i,j,true);
					map[i][j]=new Smell(x,K); //처음에 받자마자 뿌림
				}else {
					map[i][j]=new Smell(0,0);
				}
			}
		}
		st=new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) {
			shark[i].d=Integer.parseInt(st.nextToken())-1;
		}
		//상어의 방향 우선순위가 4줄씩
		for(int i=1; i<=M; i++) {
			//shark[i]에 해당
			shark[i].dir=new int[4][4];
			for(int j=0; j<4; j++) {
				st=new StringTokenizer(br.readLine());
				for(int k=0; k<4; k++) {
					shark[i].dir[j][k]=Integer.parseInt(st.nextToken())-1;
					///현재 j방향일때 k=0인게 우선순위 -->그리고 그 값의 dir[][]찾기
				}
			}
		}
		//상어는 이동 + 뿌리기를 반복한다. 상어가 1마리 남았을 때 종료
		sharkCnt=M;
		int t=0;
		while(true) {
			if(sharkCnt==1) break;
			moveShark();
			t++;
			if(t>1000) {
				t=-1; break;
			}
		}
		System.out.println(t);
	}
	private static int dr[]= {-1,1,0,0};
	private static int dc[]= {0,0,-1,1}; //위,아래,왼쪽,오른쪽
	private static void moveShark() {
		//모든 상어가 동시에 이동을 한다.
		//우선순위에서 아무냄새가 없으면 이동, 아니면 자기 냄새 있는 칸으로 이동한다.
		//모든 상어가 이동한 후 한칸에 여러마리의 상어가 남아있으면, 격자 밖으로
		//상어의 이동을 나타낼 맵 
		ArrayList<Smell> C_map[][]=new ArrayList[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++) {
				C_map[i][j]=new ArrayList<>();
			}
		//map에서 이동을 시킨다
		for(int i=1; i<=M; i++) {
			if(!shark[i].flag) continue; //이미 죽은 상어는 x
			
			//상어의 현재 방향에 맞게 이동시키기
			int r=shark[i].r; int c=shark[i].c; int d=shark[i].d;
			boolean change=false;
			for(int j=0; j<4; j++) {
				int nr=r+dr[shark[i].dir[d][j]];
				int nc=c+dc[shark[i].dir[d][j]];
				if(nr>=0 && nc>=0 && nr<N && nc<N) {
					//빈칸이면
					if(map[nr][nc].idx==0) {
						C_map[nr][nc].add(new Smell(i,K));
						shark[i].r=nr; shark[i].c=nc;
						shark[i].d=shark[i].dir[d][j];
						change=true;
						break;
					}
				}
			}
			if(!change) { //다른 칸으로 못갔으면, 자기 냄새있는곳으로 선택
				for(int j=0; j<4; j++) {
					int nr=r+dr[shark[i].dir[d][j]];
					int nc=c+dc[shark[i].dir[d][j]];
					if(nr>=0 && nc>=0 && nr<N && nc<N && map[nr][nc].idx==i) {
						//자기 냄새 있는곳으로
						C_map[nr][nc].add(new Smell(i,K));
						shark[i].r=nr; shark[i].c=nc;
						shark[i].d=shark[i].dir[d][j];
						break;
					}
				}
			}
			
		}//모든 상어를 C_map으로 이동시켰다
		//상어가 이동할 위치를 C_map에 표시했음.
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(C_map[i][j].size()>1) { //상어 두마리 들어간경우
					Collections.sort(C_map[i][j]); //idx 작은거 빼고 날리기
					map[i][j].idx=C_map[i][j].get(0).idx;
					map[i][j].cnt=K;
					for(int k=1; k<C_map[i][j].size();k++) {
						shark[C_map[i][j].get(k).idx].flag=false;
						sharkCnt--;
					}
				}else if(C_map[i][j].size()==1) {
					map[i][j].idx=C_map[i][j].get(0).idx;;
					map[i][j].cnt=K;
				}else { //새로 들어간 상어는 없는경우,
					if(map[i][j].cnt==0) continue; //바꿀거없고
					else if(map[i][j].cnt==1) {
						map[i][j].idx=0; map[i][j].cnt=0;
					}else {
						map[i][j].cnt--;
					}
				}
			}
		}
	}


	private static class Smell implements Comparable<Smell>{
		int idx, cnt; //냄새 주인, 냄새 양
		public Smell(int idx, int cnt) {
			super();
			this.idx = idx;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Smell o) {
			return this.idx-o.idx;
		}
		
	}
	private static class Shark{
		int r, c; //현재위치
		int d; //현재 방향
		boolean flag; 
		int dir[][];
		public Shark(int r, int c, boolean flag) {
			super();
			this.r = r;
			this.c = c;
			this.flag=flag;
		}
		
	}
}
