package March_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14500 {
	private static int N, M, map[][];
	private static int dx[]= {1,-1,0,0};
	private static int dy[]= {0,0,1,-1};
	private static boolean visit[][];
	private static boolean dir[];
	private static int answer=-1;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		dir=new boolean[4];
		visit=new boolean[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}//end of input,
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visit[i][j]=true;
				DFS(i,j,1, map[i][j]);
				visit[i][j]=false;
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				search(i,j,0,map[i][j]);
			}
		}
		System.out.println(answer);
		
	}//end of main
  
  
	//ㅗ 모양처리해주기,
	private static void search(int x, int y, int cnt, int ans) {
		//주어진 가운데 지점을 중심으로 한방향을 제외한 나머지 3 방향
		if(cnt==3) {
			answer=Math.max(ans, answer);
			return;
		}
		for(int i=0; i<4; i++) {
			if(dir[i]) continue;
			//갈수 있는지부터 확인,
			int nx=x+dx[i]; int ny=y+dy[i];
			if(nx>=0 && ny>=0 && nx<N && ny<M) {
				dir[i]=true;
				search(x,y,cnt+1,ans+map[nx][ny]);
				dir[i]=false;
			}
		}
	}
	private static void DFS(int x, int y, int cnt, int ans) {
		//종료조건
		if(cnt==4) {
			answer=Math.max(answer, ans);
			return;
		}
		
		//내가 갈수있는 4방을 모두 가보기,
		for(int i=0; i<4; i++) {
			int nx=x+dx[i]; int ny=y+dy[i];
			if(nx>=0 && ny>=0 && nx<N && ny<M && !visit[nx][ny]) {
				visit[nx][ny]=true;
				DFS(nx,ny,cnt+1,ans+map[nx][ny]);
				visit[nx][ny]=false;
			}
		}
	}
}	
