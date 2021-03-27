package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17472_다리만들기2 {
	private static int N,M;
	private static int map[][];
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static int cnt=0, idx;
	private static int adjMatrix[][];
	private static boolean[][] visit;
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
		
		idx=1;
		visit=new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visit[i][j] && map[i][j]==1) {
					visit[i][j]=true;
					checkNum(i,j);
					idx++;
					cnt++;
				}
			}
		}
		adjMatrix=new int[cnt+1][cnt+1];
		for(int i=0; i<=cnt; i++) {
			for(int j=0; j<=cnt; j++) {
				adjMatrix[i][j]=Integer.MAX_VALUE;
			}
		}
		//인접리스트
		for(int i=1; i<=cnt; i++) {
			calculate(i);
		}

		//MST
		boolean select[]=new boolean[cnt+1];
		int distance[]=new int[cnt+1];
		int result=0;
		int count=0;
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1]=0;

		while(true) {
			int min=Integer.MAX_VALUE;
			int now=1;
			for(int i=1; i<=cnt; i++) {
				if(!select[i] && distance[i]<min) {
					now=i;
					min=distance[i];
				}
			}
			if(select[1] && min==Integer.MAX_VALUE) { //첫번째 점 아니면서 min 갱신안된경우,
				result=-1; break;
			}
			select[now]=true;
			result+=min;
			count++;
			if(count==cnt) break;
			for(int i=1; i<=cnt; i++) {
				if(!select[i] && adjMatrix[now][i]>0 && distance[i]>adjMatrix[now][i]) {
					distance[i]=adjMatrix[now][i];
				}
			}
		}
		System.out.println(result);
		
	}

	private static void calculate(int x) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==x) { 
					for(int k=0; k<4; k++) {
						int nr=i+dr[k]; int nc=j+dc[k];
						int count=0;
						while(true) {
							if(nr<0 || nc<0 || nr>=N || nc>=M) break;
							if(map[nr][nc]==x) break;
							if(map[nr][nc]!=0) {
								if(count>=2) {
									adjMatrix[x][map[nr][nc]]=Math.min(count,adjMatrix[x][map[nr][nc]]);
								}
								break;
							}
							count++;
							nr+=dr[k]; nc+=dc[k];
							
						}
					}
				}
			}
		}
		
	}
	private static void checkNum(int x, int y) { //섬의갯수
		Queue<Pair> q=new LinkedList<>();
		q.offer(new Pair(x,y));
		map[x][y]=idx;
		while(!q.isEmpty()) {
			Pair p=q.poll();
			int r=p.x;
			int c=p.y;
			for(int i=0; i<4; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(nr>=0 && nc>=0 && nr<N && nc<M && map[nr][nc]==1 && !visit[nr][nc]) {
					visit[nr][nc]=true;
					map[nr][nc]=idx;
					q.offer(new Pair(nr,nc));
				}
			}
		}
		
	}
	
	private static class Pair{
		int x,y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}
