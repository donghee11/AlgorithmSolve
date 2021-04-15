package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//탈줄범 검거
public class Solution_1953 {
	private static int N,M,R,C,L;
	private static int pipe[][]= {{}, {0,1,2,3}, {0,1},{2,3},{0,3},{1,3},{1,2},{0,2} };
	private static Queue<int[]> q;
	private static int map[][];
	private static boolean visit[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			map=new int[N][M];
			visit=new boolean[N][M];
			visit[R][C]=true;
			q=new LinkedList<>();
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(i==R && j==C) q.add(new int[]{i,j,map[i][j]});
				}
			}//end of input
			sb.append("#").append(tc).append(" ").append(bfs()).append("\n");
			
		}//end of tc
		System.out.println(sb.toString());
	}
	
	private static int dr[]= {-1,1,0,0};
	private static int dc[]= {0,0,-1,1}; //상, 하, 좌, 우
	private static int bfs() {
		int time=0;
		int cnt=1; //갯수
		boolean check=false;
		while(!q.isEmpty()) {
			int qsize=q.size();
			time++;
			if(time>=L) break;
			check=false;
			while(qsize-->0) {
				int[]p=q.poll();
				int r=p[0]; int c=p[1]; int d=p[2];
//				System.out.println(r+" "+c);
				for(int i=0; i<pipe[d].length; i++) {
					int nr=r+dr[pipe[d][i]]; int nc=c+dc[pipe[d][i]];
//					System.out.println(nr+" "+nc+" "+pipe[d][i]);
					if(nr>=0 && nc>=0 && nr<N && nc<M && !visit[nr][nc]) {
						int x=map[nr][nc];
						int tmp=changeDir(pipe[d][i]);
//						System.out.println(x+" "+tmp);
						for(int j=0; j<pipe[map[nr][nc]].length; j++) {
							if(tmp==pipe[x][j]) {
								visit[nr][nc]=true;
//								System.out.println(nr+" "+nc);
								q.add(new int[] {nr,nc,x});
								cnt++;
								break;
							}
						}
					}
				}
				
			}
//			System.out.println("**"+time+"초끝");
//			if(time>=L-1) break; 
			
		}
		return cnt;
	}
	private static int changeDir(int i) {
		switch(i) {
		case 0: return 1;
		case 1: return 0;
		case 2: return 3;
		case 3: return 2;
		}
		return 0;
	}

	
	
	
}
