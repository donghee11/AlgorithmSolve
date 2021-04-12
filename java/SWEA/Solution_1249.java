package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1249 {
	private static int N, map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());

		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			for(int i=0; i<N; i++) {
				String str=br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j]=str.charAt(j)-'0';
				}
			}
			int answer=bfs();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static int bfs() {
		// TODO Auto-generated method stub
		//(0,0)에서부터 bfs
		Queue<int[]> q=new LinkedList<>();
		q.add(new int[] {0,0});
		int [][]dp=new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)
				dp[i][j]=Integer.MAX_VALUE;
		}
		dp[0][0]=0;
		while(!q.isEmpty()) {
			int[]p=q.poll();
			int r=p[0]; int c=p[1];
			for(int i=0; i<4; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				//4방 탐색
				if(nr>=0 && nc>=0 && nr<N && nc<N) {
					if(map[nr][nc]+dp[r][c]<dp[nr][nc]) {
						//원래 값보다 작은걸 만족하는 경우라면
						dp[nr][nc]=map[nr][nc]+dp[r][c];
						q.add(new int[] {nr,nc});
					}
				}
			}
		}
		return dp[N-1][N-1];
	}
}
