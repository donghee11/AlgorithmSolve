package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_구슬탈출2 {
	static int N, M; //가로 세로
	static char map[][];
	static int red[], blue[];
	static int hole[];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		red=new int[2]; blue=new int[2];
		hole=new int[2];
		map=new char[N][M];
		for(int i=0; i<N; i++) {
			String str=br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]=str.charAt(j);
				if(map[i][j]=='R') {
					//빨간공의 위치
					red[0]=i; red[1]=j;
				}else if(map[i][j]=='B') {
					blue[0]=i; blue[1]=j;
				}else if(map[i][j]=='O') {
					hole[0]=i; hole[1]=j;
				}
			}
		}//end of input
		System.out.println(bfs());
	}
	
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	
	private static int bfs() {
		Queue<Pair> q=new LinkedList<>();
		q.add(new Pair(red[0], red[1], blue[0], blue[1]));
		boolean visit[][][][]=new boolean[N][M][N][M];
		visit[red[0]][red[1]][blue[0]][blue[1]]=true;
		int time=0;
		while(!q.isEmpty()) {
			int qsize=q.size();
			time++;
			if(time>10) return -1;
			while(qsize-->0) {
				Pair p=q.poll();
				int rr=p.rr;
				int rc=p.rc;
				int br=p.br;
				int bc=p.bc;
				//턴마다 한번 돌게하기 위해서
				for(int i=0; i<4; i++) {
					//r의 이동
					int red_r=rr; int blue_r=br;
					int red_c=rc; int blue_c=bc;
					boolean flag_r=true; boolean flag_b=true;
					while(map[red_r][red_c]!='#') {
						//벽을 만나기전까지 계속 이동
						//중간에 구멍을만나면
						if(map[red_r][red_c]=='O') {
							flag_r=false; break;
						}
						red_r+=dr[i]; red_c+=dc[i];
					}
					//나온 순간은 벽이랑 만난 순간이니까, 다시 한칸 돌려놓기
					while(map[blue_r][blue_c]!='#') {
						//벽을 만나기전까지 계속 이동
						//중간에 구멍을만나면
						if(map[blue_r][blue_c]=='O') {
							flag_b=false; break;
						}
						blue_r+=dr[i]; blue_c+=dc[i];
					}
					//만약 구멍이 들어간 경우라면 확인할것
					if(!flag_b) {
						continue; //얘는 앞으로 볼 필요없음
					}else if(!flag_r) {
						//이 경우가 우리가 찾던 경우
						return time;
					}
					//여기까지왔으면 둘다 아닌거니까, queue에 넣기
					red_r-=dr[i]; red_c-=dc[i];
					//b의 이동
					blue_r-=dr[i]; blue_c-=dc[i];
					//일단 같은 위치가 아니어야된다.
					if((red_r==blue_r) &&(red_c==blue_c)) {
						//순서유지해야함
						if(i==0) {
							if(rr<br) red_r-=dr[i];
							else blue_r-=dr[i];
						}else if(i==1) {
							if(rr<br) blue_r-=dr[i];
							else red_r-=dr[i];
						}else if(i==2) {
							if(rc<bc) 
								red_c-=dc[i];
							else blue_c-=dc[i];
						}else {
							if(rc<bc) blue_c-=dc[i];
							else red_c-=dc[i];
						}
					}
					if(!visit[red_r][red_c][blue_r][blue_c]) {
						visit[red_r][red_c][blue_r][blue_c]=true;
						q.add(new Pair(red_r, red_c, blue_r, blue_c));
					}
				}
			}
		}
		return -1; //여기까지 오면 불가능한 경우다.
	}
	private static class Pair{
		int rr, rc, br, bc;

		public Pair(int rr, int rc, int br, int bc) {
			super();
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
		}
		
		
	}
}
