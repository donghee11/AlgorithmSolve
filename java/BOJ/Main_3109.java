package Feb_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//빵집
public class Main_3109 {
	private static char map[][];
	private static boolean visit[][];
	private static int R, C; 
	private static int ans;
	private static boolean flag;
	private static int dx[]= {-1,0,1};
	private static int dy[]= {1,1,1}; //오른쪽 대각선 위, 옆, 대각선 아래
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map=new char[R][];
		visit=new boolean[R][C];
		for(int i=0; i<R; i++) {
			map[i]=br.readLine().toCharArray();
		}//end of input
		
		//모든행에서 DFS를 반복할 것.
		for(int i=0; i<R; i++) {
			flag=false;
			visit[i][0]=true;
			DFS(i,0);
		}
		System.out.println(ans);
	
	}//end of main

	private static void DFS(int x, int y) {
		//거기가 정답이 맞든 아니든 visit는 무조건 표시
		visit[x][y]=true;
		//종료조건 열에 가있을 때,
		if(y==C-1) {
			//이미 여기에 도달했으면 다른 dfs를 못오게.
			flag=true;
			ans++; return;
		}
		for(int i=0; i<3; i++) {//3방향 탐색 시작
			int nx=x+dx[i]; int ny=y+dy[i];
			if(nx>=0 && ny>=0 && nx<R && ny<C && !visit[nx][ny] && map[nx][ny]!='x') {//범위만족, 방문x, 건물x
				if(flag) return; //이미 해결된애
				DFS(nx,ny);
				
			}
		}
	}
	
	
}///end of class 
