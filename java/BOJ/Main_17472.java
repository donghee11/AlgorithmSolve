package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//고려해야할 것 : 1. 섬이름 붙여주기 2. 섬들사이에 최소 거리 갱신시켜주기 3. 그 중에서 MST 구해주기
//섬의 이름을 1~cnt라고 하자, 총 cnt개 있는것

public class Main_17472 {
	private static int N,M;
	private static int map[][];
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static int result=0;
	private static boolean[][] visit;
	private static int cnt=0;
	private static ArrayList<Info> list[]; 
	private static class Info implements Comparable<Info>{
		int node, w;

		public Info(int node, int w) {
			super();
			this.node = node;
			this.w = w;
		}

		@Override
		public int compareTo(Info o) {
			return new Integer(this.w).compareTo(o.w);
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}//end of input
		visit=new boolean[N][M];
		//섬에 이름 붙여주기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visit[i][j] && map[i][j]==1) { //땅이면 섬 이름 붙여주기
					cnt++;
					visit[i][j]=true;
					map[i][j]=cnt;
					labelIsland(i,j);
				}
			}
		}
		list=new ArrayList[cnt+1];
		for(int i=1; i<=cnt; i++) {
			list[i]=new ArrayList<>();
		}
		
		//i번째 list에 모든 섬들과의 관계 저장해놓기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=0) { //섬이라면, 다리를 세우자
					makeBridge(i,j);
				}
			}
		}
		//list를 가지고 w에 따라서 mst구하기
		//pq 사용
		mst();
		
	}//end of main
	private static void mst() {
		PriorityQueue<Info> pq=new PriorityQueue<>();
		pq.offer(new Info(1,0)); //1번노드 부터 시작,
		boolean select[]=new boolean[cnt+1];
		int dist[]=new int[cnt+1];
		for(int i=1; i<=cnt; i++) {
			dist[i]=Integer.MAX_VALUE; //처음에 dist값은 모두 최댓값으로 설정해놓고,
		}
		dist[1]=0; //임의의 첫번째 정점은 dist값을 1로 설정해놓기
		while(!pq.isEmpty()) {
			Info cur=pq.poll(); //이미 가중치 순서대로 정렬되어있으니까,
			int node=cur.node;
			int w=cur.w;
			if(select[node]) continue; 
			select[node]=true;
			result+=w;
			//node가 추가됨으로서. dist[i]를 갱신시켜야한다.
			for(Info next:list[node]) { //해당 node와 연결되어있는 애 
				if(!select[next.node] && dist[next.node]>next.w) {
					dist[next.node]=next.w; //update
					pq.offer(new Info(next.node,next.w));
				}
			}
		}
		boolean flag=true;
		for(int i=1; i<=cnt; i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				flag=false;
			}
		}
		if(flag) System.out.println(result);
		else System.out.println(-1);
	}
	
	//가능한 곳에 모두 섬 놔주기,
	private static void makeBridge(int r, int c) {
		for(int i=0; i<4; i++) {
			int nr=r; int nc=c;
			//해당방향으로 자기자신이 아닌 섬 발견될 경우
			int dist=0;
			while(true) {
				nr+=dr[i]; nc+=dc[i];
				if(nr<0 || nc<0 || nr>=N || nc>=M) break;
				if(map[nr][nc]==map[r][c]) break;
				dist++;
				if(map[nr][nc]>=1) { //자기자신이 아닌 섬 방문하면,
					if(dist>=3) {
						list[map[r][c]].add(new Info(map[nr][nc], dist-1));
					}
					break;
				}
			}
		}
	}
	
	//섬 이름 붙여주기 1~cnt까지
	private static void labelIsland(int x, int y) {
		Queue<int[]> q=new LinkedList<>();
		q.offer(new int[] {x,y});
		while(!q.isEmpty()) {
			int[]p=q.poll();
			int r=p[0]; int c=p[1];
			for(int i=0; i<4; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(nr>=0 && nc>=0 && nr<N && nc<M && !visit[nr][nc] && map[nr][nc]==1) {
					visit[nr][nc]=true;
					map[nr][nc]=cnt;
					q.offer(new int[] {nr,nc});
				}
			}
		}
	}
}
