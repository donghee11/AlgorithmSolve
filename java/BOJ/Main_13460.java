package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//구슬탈출

public class Main_13460 {
	private static int N, M;
	private static int hole_r, hole_c;
	private static int red_r, red_c, blue_r, blue_c;
	private static char map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new char[N][M];
		for(int i=0; i<N; i++) {
			String str=br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]=str.charAt(j);
				if(map[i][j]=='B') {
					blue_r=i; blue_c=j;
				}
				if(map[i][j]=='R') {
					red_r=i; red_c=j;
				}
				if(map[i][j]=='O') {
					hole_r=i; hole_c=j; //구멍의 위치 기억하기,
				}
			}
		}//end of input
		System.out.println(BFS());
		
		
	}
	private static int dr[]= {0,0,-1,1};
	private static int dc[]= {-1,1,0,0};
	private static boolean visit[][][][];
	private static int BFS() {
		//시작점은 이미 고정되어있음,
		visit=new boolean[N][M][N][M];
		Queue<Pair> q=new LinkedList<>();
		//처음에 넣을 때 방향을 4방향을 모두 넣고,
		//그 방향에서 갈 수 있는 애들을 조사해가면서
		//불가능한애들은 그냥 빼버리고, 가능한것들만 큐 사이즈만큼 넣고 돌려
		q.add(new Pair(red_r,red_c, blue_r, blue_c, 2,0));
		visit[red_r][red_c][blue_r][blue_c]=true;
		//0이면 이전에 좌우 중에 하나로 움직인거고, 1이면 이전에 상하중에 하나로 움직인거
ex:		while(!q.isEmpty()) {
			int qsize=q.size();
			while(qsize-->0) { //한바퀴 돌면서
				Pair p=q.poll();
				int rr=p.rr; int rc=p.rc;
				int br=p.br; int bc=p.bc;
				int dir=p.dir; int cnt=p.cnt;
				if(cnt>9) break ex; //이거면 못옮긴다는 뜻임
				//이동시키기
				//dir에 해당한 애를 전에 옮겼다는 뜻, 
				if(dir!=0) { //좌우로 움직이기
					//빨간공, 파란공 둘다 일단 끝까지 움직임
					//좌, 우
					boolean flag[]=new boolean[2]; //구멍을 만났는지 확인하자
					
					for(int i=0; i<2; i++) {
						flag[0]=false; flag[1]=false;
						int nrr=rr; int nrc=rc;
						while(map[nrr][nrc]!='#') { //벽을 만나기전까지
							nrr+=dr[i]; nrc+=dc[i];
							if(nrr==hole_r && nrc==hole_c) {
								//이동중에 빨간공이 구멍을 만났다.
								flag[0]=true;
								//이위치에서 break;
								break;
							}
						}
						//빠져나온 순간은 벽을 만난뒤, 
						nrr-=dr[i]; nrc-=dc[i];
						//파란공도 똑같이 움직이기
						int nbr=br; int nbc=bc;
						while(map[nbr][nbc]!='#') {
							nbr+=dr[i]; nbc+=dc[i];
							if(nbr==hole_r && nbc==hole_c) {
								//구멍을 만났다.
								flag[1]=true;
								break;
							}
						}
						//flag검사했을 때,
						if(flag[0] && !flag[1]) {
							return (cnt+1);
						}else if(!flag[0] && flag[1]) {
							//파란공이 먼저 들어갔으면, 밑에 보지말기!!
							//visit해줘야되나?
//							visit[nrr+dr[i]][nrc+dc[i]][nbr][nbc]=true;
							continue;
						}else if(flag[0] && flag[1]){
							continue;
						}
						//여기까지오는 경우, 둘다 구멍아닌경우
						nbr-=dr[i]; nbc-=dc[i];
						if(nrr==nbr && nrc==nbc) {
							//만약 두개위치가 같으면? 조정해줘야함, 
							if(i==0) { //원래 오른쪽에 있던걸 +1
								if(rc<bc) nbc+=1;
								else nrc+=1;
							}else { //원래 왼쪽에 있던걸 -1
								if(rc<bc) nrc-=1;
								else nbc-=1;
							}
						}
						if(!visit[nrr][nrc][nbr][nbc]) {
							visit[nrr][nrc][nbr][nbc]=true;
							q.add(new Pair(nrr,nrc,nbr,nbc,0,cnt+1));
						}
					}
				}
				if(dir!=1) {//상하로 움직이기
					//상, 하
					boolean flag[]=new boolean[2]; //구멍을 만났는지 확인하자
					
					for(int i=2; i<4; i++) {
						flag[0]=false; flag[1]=false;
						int nrr=rr; int nrc=rc;
						while(map[nrr][nrc]!='#') { //벽을 만나기전까지
							nrr+=dr[i]; nrc+=dc[i];
							if(nrr==hole_r && nrc==hole_c) {
								//구멍을 만났다.
								flag[0]=true;
								break;
							}
						}
						//빠져나온 순간은 벽을 만난뒤, 
						nrr-=dr[i]; nrc-=dc[i];
						//파란공도 똑같이 움직이기
						int nbr=br; int nbc=bc;
						while(map[nbr][nbc]!='#') {
							nbr+=dr[i]; nbc+=dc[i];
							if(nbr==hole_r && nbc==hole_c) {
								//구멍을 만났다.
								flag[1]=true;
								break;
							}
						}
						//flag검사했을 때,
						if(flag[0] && !flag[1]) {
							return (cnt+1);
						}else if(!flag[0] && flag[1]) {
							//파란공이 먼저 들어갔으면, 밑에 보지말기!!
							//visit해줘야되나?
//							visit[nrr+dr[i]][nrc+dc[i]][nbr][nbc]=true;
							continue;
						}else if(flag[0] && flag[1]){
							continue;
						}
						nbr-=dr[i]; nbc-=dc[i];
						//만약 두개위치가 같으면? 조정해줘야함, 
						//원래 빨간색이 위였으면
						if(nrr==nbr && nrc==nbc) {
							if(i==2) { //위
								//원래 위에있던걸 그대로, 아래에있던걸 +1
								if(rr<br) nbr+=1;
								else nrr+=1;
							}else { //아래
								//원래 위에있던걸 -1
								if(rr<br) nrr-=1;
								else nbr-=1;
							}
						}
						if(!visit[nrr][nrc][nbr][nbc]) {
							visit[nrr][nrc][nbr][nbc]=true;
							q.add(new Pair(nrr,nrc,nbr,nbc,1,cnt+1));
						}
					}
				}
			}
		}
		return -1; //여기까지 온다는건, 10번안에 안끝났단뜻
	}
	
	private static class Pair{
		int rr, rc, br, bc;
		int dir, cnt;
		public Pair(int rr, int rc, int br, int bc, int dir, int cnt) {
			super();
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
			this.dir = dir;
			this.cnt = cnt;
		}
		
		
	}
	
	
}
