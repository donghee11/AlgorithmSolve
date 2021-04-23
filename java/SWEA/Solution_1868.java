package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_1868 {
	private static int N, totalCnt, answer, cnt;
	private static char map[][];
	private static int[][] map2;
	private static boolean[][] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());

			totalCnt=0;
			cnt=0;
			map=new char[N][N];
			map2=new int[N][N];
			visit=new boolean[N][N];
			for(int i=0; i<N; i++) {
				String str=br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j]=str.charAt(j);
				}

			}//end of input
			//주변에 지뢰없는 곳부터 bfs
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]=='*') {
						map2[i][j]=-1;
						checkBlank(i,j);
					}else totalCnt++;
				}
			}
			answer=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map2[i][j]==0 && !visit[i][j]) {
						answer++;
						bfs(i,j);
					}
				}
			}
			answer+=(totalCnt-cnt);
			//* : 지뢰있는곳, .: 지뢰 없는 곳
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	
	private static void bfs(int x, int y) {
		Queue<int[]> q=new LinkedList<>();
		q.add(new int[] {x,y});
		visit[x][y]=true;
		cnt++;
		while(!q.isEmpty()) {
			int[]p=q.poll();
			int r=p[0]; int c=p[1];
			for(int i=0; i<8; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(nr>=0 && nc>=0 && nr<N && nc<N && !visit[nr][nc] && map[nr][nc]!='*') {
					cnt++;
					visit[nr][nc]=true;
					if(map2[nr][nc]==0) {
						q.add(new int[] {nr,nc});
					}
				}
			}
		}
	}


	private static void checkBlank(int r, int c) {

		for(int i=0; i<8; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(map[nr][nc]=='.') {
				map2[nr][nc]++;
			}
		}
	}

	//8방
	private static int []dr= {-1,-1,-1,0,0,1,1,1};
	private static int []dc= {-1,0,1,-1,1,-1,0,1};
	

}
