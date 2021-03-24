
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600 {
	private static class Pair{
		int x,y,cnt, kcnt;
		public Pair(int x, int y, int cnt, int kcnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt=cnt;
			this.kcnt=kcnt;
		}
		
	}
	private static int K, W, H;
	private static int map[][];
	//chess판 움직임
	private static int dr1[]= {-2,-1,1,2,2,1,-1,-2};
	private static int dc1[]= {1,2,2,1,-1,-2,-2,-1};
	//일반 인접칸 움직임
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static boolean visit[][][];
	//private static boolean visit2[][];
	private static int answer;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		
		K=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken()); //행,
		map=new int[H][W];
		visit=new boolean[H][W][K+1];
		//visit2=new boolean[W][H];
		for(int i=0; i<H; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		if(answer==Integer.MAX_VALUE) answer=-1;
		System.out.println(answer);
	}//end of main
	private static void bfs() {
		answer=Integer.MAX_VALUE;
		Queue<Pair>q=new LinkedList<>();
		q.offer(new Pair(0,0,0,K));
		visit[0][0][K]=true;
		while(!q.isEmpty()) {
			Pair p=q.poll();
			int r=p.x; int c=p.y; int cnt=p.cnt; int kcnt=p.kcnt;
			if(r==H-1 && c==W-1) {
				answer=Math.min(answer, cnt);
			}
			//8방향 조사 
			if(kcnt>0) {
				for(int i=0; i<8; i++) {
					int nr=r+dr1[i]; int nc=c+dc1[i];
					if(nr>=0 && nc>=0 && nr<H && nc<W && !visit[nr][nc][kcnt-1]) {
						if(map[nr][nc]==0) {
							visit[nr][nc][kcnt-1]=true;
							q.offer(new Pair(nr,nc,cnt+1, kcnt-1));
						}
					}
				}
			}
			//4방향조사
			for(int i=0; i<4; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(nr>=0 && nc>=0 && nr<H && nc<W && !visit[nr][nc][kcnt]) {
					if(map[nr][nc]==0) {
						visit[nr][nc][kcnt]=true;
						q.offer(new Pair(nr,nc,cnt+1, kcnt));
					}
				}
			}
			
		}
	}
}
