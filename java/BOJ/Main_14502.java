package Feb_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소
 * 빈칸, 벽으로 이루어짐. 바이러스는 상하좌우. 새로 세울 수 있는 벽은 3개. 1:벽, 2:바이러스
 * 안전영역이 최대가 되도록=0갯수
 */
public class Main_14502 {
	private static int N;
	private static int M;
	private static int [][]map, c_map; //원본배열, 카피배열
	private static int dx[]= {1,-1,0,0};
	private static int dy[]= {0,0,1,-1};
	private static boolean visit[][];
	private static Queue<Pair> q;
	
	private static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		c_map=new int[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0) cnt++;
			}
		}//end of input
		comb(0);
		System.out.println(answer);
	}
	private static int answer=0;
	
//	//map의 배열을 c_map으로 복사하는 func
	private static void copy(int [][]map, int [][]c_map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				c_map[i][j]=map[i][j]; 
			}
		} 
	}
	
	private static void comb(int cnt) {
		//벽을 3개 세우면 종료
		if(cnt==3) {
			//여기서 map의 0의 갯수 구하기
			copy(map,c_map); //bfs는 c_map으로
			answer=Math.max(BFS(), answer);
			return;
		}
		
		//아직 세울 벽이 남아있으면, 탐색해서 벽을 세우기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					map[i][j]=1; //벽 만들어주고 다시 재귀
					comb(cnt+1);
					map[i][j]=0; //벽 다시부시기
				}
			}
		}
	}
	private static int BFS() {
		//C_map을 가지고 BFS 진행,
		q=new LinkedList<Pair>();
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(c_map[i][j]==2)
					q.offer(new Pair(i,j));
		int tmpcnt=cnt-3; //원래 0갯수에서 벽세운 3개 빼기
		while(!q.isEmpty()) {
			Pair p=q.poll();
			int x=p.x; int y=p.y;
			for(int i=0; i<4; i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
				if(nx>=0 && ny>=0 && nx<N && ny<M && c_map[nx][ny]==0) {
					c_map[nx][ny]=2; //바이러스, visit 함수도 필요없다. 0인 애들만 앞으로 방문할 거니깐.
					q.offer(new Pair(nx,ny));
					//얘는 0을 바꾼거니까 전체 갯수에서 카운팅 다운,
					tmpcnt--;
				}
			}
		}
//		System.out.println(tmpcnt);
		return tmpcnt;
	}
	
	private static class Pair{
		int x, y;
		public Pair(int x, int y) {
			this.x=x; this.y=y;
		}
	}
}
