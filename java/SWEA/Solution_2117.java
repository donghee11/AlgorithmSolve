package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2117 {
	private static int N, M;
	private static int map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			map=new int[N][N];
			for(int i=0;i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}//end of input
			maxNum=1;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					visit=new boolean[N][N];
					visit[i][j]=true;
					findMaxHouse(i,j);
				}
			}
			sb.append("#").append(tc).append(" ").append(maxNum).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}//end of main
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static boolean visit[][];
	private static void findMaxHouse(int x, int y) {
		//(r,c) 에서 depth1부터 나가기
		Queue<int[]>q=new LinkedList<>();
		q.add(new int[] {x,y});
		int cnt=0; //처음엔 1개
		int k=1;
		if(map[x][y]==1) cnt=1;
		int tmp_answer=calc(cnt, k);
		while(!q.isEmpty()) {
			int qsize=q.size();
			k++;
			while(qsize-->0) {
				int[]p=q.poll();
				int r=p[0]; int c=p[1];
				for(int i=0; i<4; i++) {
					int nr=r+dr[i]; int nc=c+dc[i];
					if(nr>=0 && nc>=0 && nr<N && nc<N && !visit[nr][nc]) {
						visit[nr][nc]=true;
						q.add(new int[] {nr,nc});
						if(map[nr][nc]==1) {
							cnt++; //집이 있는곳이면 cnt++;
						}
					}
				}
			}
			tmp_answer=calc(cnt,k);
			if(tmp_answer>=0) {
				maxNum=Math.max(maxNum, cnt);
			}
		}
	}
	
	private static int maxNum;
	private static int calc(int cnt, int k) {
		return M*cnt-(k*k+(k-1)*(k-1));
	}
}

