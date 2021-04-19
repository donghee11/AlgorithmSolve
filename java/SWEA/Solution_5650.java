package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5650 {
	private static int N;
	private static int map[][];
	private static ArrayList<int[]> blacHole;; 
	private static Hole hole[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			StringTokenizer st;
//			blackHole=new ArrayList<>();
			map=new int[N][N];
			hole=new Hole[5]; //hole이 최대 5개
			for(int i=0; i<5; i++) hole[i]=new Hole();
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]>=6) {
						if(hole[map[i][j]-6].first_r==-1) {
							hole[map[i][j]-6].first_r=i;
							hole[map[i][j]-6].first_c=j;
						}else {
							hole[map[i][j]-6].second_r=i;
							hole[map[i][j]-6].second_c=j;
						}
					}
				}
			}///end of input
			
			
			//핀볼은 모든 시작점에서 시작가능하다, 모든 경우의 수 살펴보기
			int answer=0;
			for(int i=0;i<N; i++) {
				for(int j=0; j<N; j++) {
					//(i,j)에서 방향 d로
					if(map[i][j]==0) {
						for(int d=0; d<4; d++) {
							answer=Math.max(move(i,j,d), answer);
//							System.out.println(answer);
						}
					}
				}
			}
//			move(0,2,0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1}; //하, 상,우,좌
	private static int move(int x, int y, int d) {
		//(x,y)에서 시작해서, x,y에 도착하거나, 블랙홀을 만나면 끝난다
		int count=0;
		int r=x; int c=y;
		while(true) {
			int nr=r+dr[d]; int nc=c+dc[d];
			if(nr==x && nc==y) return count;
			if(nr>=0 && nc>=0 && nr<N && nc<N) {
				//범위안일때 블랙홀 만나면
				if(map[nr][nc]==-1) return count;
				
				if(map[nr][nc]>=1 && map[nr][nc]<=5) {
					d=changeDir(map[nr][nc],d);
					count++;
				}else if(map[nr][nc]>=6 && map[nr][nc]<=10) {
					int num=map[nr][nc];
					if(nr==hole[num-6].first_r && nc==hole[num-6].first_c) {
						nr=hole[num-6].second_r;
						nc=hole[num-6].second_c;
					}else {
						nr=hole[num-6].first_r;
						nc=hole[num-6].first_c;
					}
				}
				
				////r,c바꿔주기
				r=nr; c=nc;
			}else {
				//범위바깥,
				count++;
				d=changeDir(d);
				//r,c바꿔주지말기! 즉, 이동시키지않기
				r=nr; c=nc;
			}
		}
//		return count;
	}
	private static int changeDir(int x, int d) {
		if(x==1) {
			switch(d) {
			case 0: return 2;
			case 3: return 1;
			default: return changeDir(d);
			}
		}else if(x==2) {
			switch(d) {
			case 1: return 2;
			case 3: return 0;
			default: return changeDir(d);
			}
		}else if(x==3) {
			switch(d) {
			case 2: return 0;
			case 1: return 3;
			default: return changeDir(d);
			}
		}else if(x==4) {
			switch(d) {
			case 2: return 1;
			case 0: return 3;
			default:return changeDir(d);
			}
		}else if(x==5) {
			return changeDir(d);
		}
		return 0;
	}
	private static int changeDir(int d) {
		switch(d) {
		case 0: return 1;
		case 1: return 0;
		case 2: return 3;
		case 3: return 2;
		}
		return 0;
	}
	private static class Hole{
		int first_r, first_c;
		int second_r, second_c;
		public Hole() {
			this.first_r=-1;
			this.first_c=-1;
			this.second_c=-1;
			this.second_r=-1; //초기값
		}
	}
}
