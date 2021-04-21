package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//통나무옮기기
public class Main_1938 {
	private static int N, shape, answer;
	private static int map[][];
	private static int block[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		block=new int[3][2];
		map=new int[N][N];
		int idx=0;
		for(int i=0; i<N; i++) {
			String str=br.readLine();
			for(int j=0; j<N; j++) {
				char c=str.charAt(j);
				if(c=='B') {
					block[idx][0]=i; block[idx][1]=j;
					idx++;
				}else if(c=='E') map[i][j]=2; //E표시
				else if(c-'0'==1) map[i][j]=1; ///장애물표시,
			}
		}
		if(block[0][0]==block[1][0]) shape=0;//가로
		else shape=1; //세로
		//시작위치에서 E를 찾을 때까지, 최소횟수
		answer=Integer.MAX_VALUE;
		bfs();
		answer=answer==Integer.MAX_VALUE?0:answer;
		System.out.println(answer);
//		recur(0);
	}
	private static void bfs() {
		Queue<int[]> q=new LinkedList<>();
		boolean visit[][][]=new boolean[N][N][2]; //해당 자리에서 ,shape로
		//q에 넣는 r,c는 3개중 가운데 위치와 shape과 dist를 넣자
		q.add(new int[] {block[1][0], block[1][1], shape,0});
		visit[block[1][0]][block[1][1]][shape]=true;
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int r=p[0]; int c=p[1]; int s=p[2]; int dist=p[3];
			if(map[r][c]==2) {
				if(check(r,c,s))
					answer=Math.min(answer, dist);
			}
			if(s==0) {
				for(int i=0; i<4; i++) {
					int nr=r+dr[i]; int nc=c+dc[i];
					if(nr>=0 && nc-1>=0 && nr<N && nc+1<N && !visit[nr][nc][s]) {
						if(map[nr][nc-1]!=1 && map[nr][nc+1]!=1 && map[nr][nc]!=1) {
							q.add(new int[] {nr,nc,s, dist+1});
							visit[nr][nc][s]=true;
						}
					}
				}
				//회전, 이때는 모양이 변경된다
				if(!visit[r][c][1-s] && isPossible(r,c)) {
					visit[r][c][1-s]=true;
					q.add(new int[] {r,c,1-s,dist+1});
				}
			}else if(s==1) {
				for(int i=0; i<4; i++) {
					int nr=r+dr[i]; int nc=c+dc[i];
					if(nr-1>=0 && nc>=0 && nr+1<N && nc<N && !visit[nr][nc][s]) {
						if(map[nr-1][nc]!=1 && map[nr][nc]!=1 && map[nr+1][nc]!=1) {
							visit[nr][nc][s]=true;
							q.add(new int[] {nr,nc,s,dist+1});
						}
					}
				}
				//회전, 이때는 모양이 변경된다
				if(!visit[r][c][1-s] && isPossible(r,c)) {
					visit[r][c][1-s]=true;
					q.add(new int[] {r,c,1-s,dist+1});
				}
			}
			

		}
	}
	private static boolean check(int r, int c, int s) {
		if(s==0) {
			if(map[r][c-1]==2 && map[r][c+1]==2) return true;
		}else if(s==1) {
			if(map[r+1][c]==2 && map[r-1][c]==2) return true; 
		}
		return false;
	}
	private static boolean isPossible(int r, int c) {
		//(r,c)에서 주변 8개 되는지 살피기
		for(int i=0; i<8; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr<0 || nc<0 || nr>=N || nc>=N || map[nr][nc]==1) return false;
		}
		return true;
	}
	//U,D,L,R,T
	private static int dr[]= {-1,1,0,0,-1,-1,1,1};
	private static int dc[]= {0,0,-1,1,-1,1,-1,1};

	
	//회전은 따로처리
}


