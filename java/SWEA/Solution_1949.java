package March_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949 {
	private static int N, K , map[][]; 
	private static boolean visit[][];
	private static int dx[]= {1,-1,0,0};
	private static int dy[]= {0,0,1,-1};
	private static int ans;
	private static int max_val=Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			ans=1;
			max_val=-1;
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken()); //최대 K 높이 까지 깎을 수 있다
			map=new int[N][N];
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					max_val=Math.max(max_val, map[i][j]); //map중에서 최댓값 기억해놓기
				}
			}
			
			//무조건 max_val부터 등산로는 시작한다. 깎을 필요 없으니까!
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==max_val) {
						visit=new boolean[N][N];
						visit[i][j]=true;
						DFS(i,j,1, true); //(i,j)부터 bfs 시작하기
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}//end of main
	private static void DFS(int x, int y, int cnt, boolean flag) {
		//종료조건
		
		
		for(int i=0; i<4; i++) {
			int nx=x+dx[i]; int ny=y+dy[i];
			if(nx>=0 && ny>=0 && nx<N && ny<N && !visit[nx][ny]) {
				//조건 상관없이 그냥 dfs 돌릴수 있는경우
				if(map[nx][ny]<map[x][y]) {
					visit[nx][ny]=true;
					DFS(nx,ny,cnt+1,flag);
					visit[nx][ny]=false;
				}else { //flag의 여부에 따라서 멈추거나 계속 돌리거나
					if(flag && map[nx][ny]-K<map[x][y] && map[x][y]>0) { //바뀐적 없으면서 , K 로 줄일수있는애면
						visit[nx][ny]=true; 
						int tmp=map[nx][ny];
						map[nx][ny]=map[x][y]-1;
						DFS(nx,ny,cnt+1,false);
						map[nx][ny]=tmp;
						visit[nx][ny]=false;
					}else { //얘는 그냥 여기서 끝내야되는경우, 더이상 돌면 안된다.
						ans=Math.max(cnt, ans);
					}
					
				}
			}
		}//모든 방향 조사 끝
		//다 끝나고 여기까지 온 경우 ?
//		ans=Math.max(cnt, ans);
	}
	
}//end of class
