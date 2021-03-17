package March_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236 {
	private static class Shark{
		int x,y,size;
		public Shark(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
		
	}
	private static class Fish{
		int x,y,size;
		public Fish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
		
	}
	private static int N, ans, cnt;
	private static int map[][];
	private static boolean visit[][];
	private static int dr[]= {-1,0,0,1};
	private static int dc[]= {0,-1,1,0};
	private static Queue<Fish> q;
	private static Shark shark;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					map[i][j]=0;
					shark=new Shark(i,j,2);
				}
			}
		}
		while(true) {
			if(!BFS()) break;
		}
		System.out.println(ans);
		//상어 위치부터 bfs 시작,
	}//end of main
	private static boolean BFS() {
		q=new LinkedList<>();
		ArrayList<Fish> list=new ArrayList<>();
		//상어 집어넣기, 현재 상어의 정보는 shark에 담겨있음
		q.offer(new Fish(shark.x, shark.y, 0));
		visit=new boolean[N][N];
		visit[shark.x][shark.y]=true;
		//fish는 위쪽, 같으면 왼쪽에 있는거 먹기
		while(!q.isEmpty()) {
			int size=q.size();
			//q 사이즈만큼 돌기,
			while(size-->0) {
				Fish f=q.poll();
				int r=f.x; int c=f.y; int s=f.size;
				for(int i=0; i<4; i++) {
					//자기보다 작은 물고기라면, 갈 수 있음
					int nr=r+dr[i]; int nc=c+dc[i];
					if(nr>=0 && nc>=0 && nr<N && nc<N && !visit[nr][nc]) {
						if(map[nr][nc]==0 || map[nr][nc]==shark.size) {
							q.offer(new Fish(nr,nc,s+1));
							visit[nr][nc]=true; //크기가 같거나, 빈칸이라면 이동만 시키기
						}else if(map[nr][nc]<shark.size) {
							//먹을수 있는 조건이라면
							list.add(new Fish(nr,nc,s+1));
							visit[nr][nc]=true;
						}
					}
				}
			}
			//list사이즈가 0이아니면
			if(list.size()>0) {
				//좌표순으로 정렬
				Collections.sort(list, new Comparator<Fish>() {

					@Override
					public int compare(Fish o1, Fish o2) {
						if(o1.x==o2.x) {
							return o1.y-o2.y;
						}
						return o1.x-o2.x;
					}
					
				});
				//정렬하고 나면 첫번쨰꺼,
				int x=list.get(0).x; int y=list.get(0).y;
				ans+=list.get(0).size;
				map[x][y]=0;
				shark.x=x; shark.y=y; cnt++;
				if(cnt==shark.size) {
					cnt=0; shark.size+=1;
				}
				
				return true;
			}
			
		}
		return false; //더이상 잡아먹을 수없을때
	}
}
