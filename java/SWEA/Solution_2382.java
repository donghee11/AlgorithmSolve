package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_미생물 {
	private static int N, M, K;
	private static Info map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());//셀의갯수
			M=Integer.parseInt(st.nextToken());//격리시간
			K=Integer.parseInt(st.nextToken()); //미생물 군집갯수
			map=new Info[N][N];
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					map[i][j]=new Info(0,0);
			for(int k=1; k<=K; k++) {
				//군집이 총 9개!
				st=new StringTokenizer(br.readLine());
				int r=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				int num=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				map[r][c]=new Info(num,d-1);
			}
			//M시간동안
			while(M-->0) {
				move();
			}
			sb.append("#").append(tc).append(" ").append(calc()).append("\n");
		}//end of tc, 초기화확인하ㅣㄱ
		System.out.println(sb.toString());
	}//end of main
	private static int calc() {
		int answer=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				answer+=map[i][j].num;
			}
		}
		return answer;
	}
	private static int dr[]= {-1,1,0,0};
	private static int dc[]= {0,0,-1,1};
	private static void move() {
		ArrayList<Info> C_map[][]=new ArrayList[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				C_map[i][j]=new ArrayList<>();
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//미생물 있는 곳이면
				if(map[i][j].num>0) {
					//이동방향 살피기
					int nr=i+dr[map[i][j].dir];
					int nc=j+dc[map[i][j].dir];
					//약 친 공간이면
					if(nr==0 || nc==0 || nr==N-1 || nc==N-1) {
						C_map[nr][nc].add(new Info(map[i][j].num/2,change(map[i][j].dir)));
					}else {
						C_map[nr][nc].add(new Info(map[i][j].num, map[i][j].dir));
					}
						
				}
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j].num=0; map[i][j].dir=0;
				if(C_map[i][j].size()==1) {
					map[i][j].dir=C_map[i][j].get(0).dir;
					map[i][j].num=C_map[i][j].get(0).num;
				}else if(C_map[i][j].size()>1) {
					int tmp=0; int newdir=0; int total=0;
					for(int s=0; s<C_map[i][j].size(); s++) {
						int n=C_map[i][j].get(s).num; int d=C_map[i][j].get(s).dir;
						if(tmp<n) {
							tmp=n; newdir=d;
						}
						total+=n;
					}
					map[i][j].dir=newdir; map[i][j].num=total;
				}
			}
		}
	}
	private static int change(int d) {
		if(d==0) return 1;
		else if(d==1) return 0;
		else if(d==2) return 3;
		else if(d==3) return 2;
		return 0;
	}
	private static class Info{
		int num, dir;

		public Info(int num, int dir) {
			super();
			this.num = num;
			this.dir = dir;
		}
		
	}
}
