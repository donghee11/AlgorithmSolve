package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//마법사 상어와 파이어스톰
//구현해야할 순서: 부분 격자에서 시계방향으로 90도 회전시키기, 
//남아있는 얼음의 양과, 가장 큰 덩어리가 차지하는 칸의갯수 출력하기
public class Main_20058 {
	private static int N, Q, map[][], l;
	private static ArrayList<int[]> list;
	private static int max=Integer.MIN_VALUE;
	private static boolean visit[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		Q=Integer.parseInt(st.nextToken());
		l=(int)Math.pow(2, N);
		map=new int[l][l];
		for(int i=0; i<l; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<l; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		st=new StringTokenizer(br.readLine());
		for(int q=0; q<Q; q++) {
			int step=Integer.parseInt(st.nextToken());
			step=(int)Math.pow(2, step);
			for(int i=0; i<l; i+=step) {
				for(int j=0; j<l;j+=step) {
					rotate(i,j,step);
				}
			}
			list=new ArrayList<>();
			for(int i=0; i<l; i++) {
				for(int j=0; j<l; j++) {
					if(map[i][j]==0) continue;
					find(i,j);
				}
			}
			removeIce();
		}//단계별로 입력받으면서 rotate 함수로 돌리기
		
		//최종 남은 얼음의 갯수
		visit=new boolean[l][l];
		int result=0;
		for(int i=0; i<l; i++) {
			for(int j=0; j<l; j++) {
				result+=map[i][j];
				if(map[i][j]==0 || visit[i][j]) continue; 
				visit[i][j]=true; 
				findMaxIce(i,j);
			}
		}
//		for(int i=0; i<l; i++) {
//			for(int j=0; j<l; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(result);
		if(max==Integer.MIN_VALUE) System.out.println(0);
		else System.out.println(max);
	}//end of main
	private static int dr[]= {1,0,-1,0};
	private static int dc[]= {0,1,0,-1};
	
	//덩어리찾기
	private static void findMaxIce(int x, int y) {
		int cnt=1;
		Queue<int[]>q=new LinkedList<>();
		q.offer(new int[] {x,y});
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int r=p[0]; int c=p[1];

			for(int i=0; i<4; i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(nr>=0 && nc>=0 && nc<l && nr<l && !visit[nr][nc] && map[nr][nc]>0) {
					q.offer(new int[] {nr,nc});
					visit[nr][nc]=true;
					cnt++;
				}
			}
		}
		
		max=Math.max(max, cnt);
	}
	
	
	
	private static void removeIce() {
		for(int i=0; i<list.size(); i++) {
			map[list.get(i)[0]][list.get(i)[1]]-=1;
		}
	}
	private static void find(int r, int c) {
		int count=0;
		for(int i=0; i<4; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr<0 || nc<0|| nr>=l || nc>=l) {
				count++; continue;
			}
			
			if(map[nr][nc]==0) count++;
			
		}
		if(count>=2) {
			//얼음줄여야돼,
			list.add(new int[] {r,c});
			return;
		}
	}
	
	private static void rotate(int x, int y, int step) {
		int r=x; int c=y;
		while(step>0) {
			//길이가 step인 상태
			for(int cnt=0; cnt<step-1; cnt++) {
			int tmp=map[r][c]; //(r,c) 위치 보관해두기
			//4방향
			int nr=r, nc=c;
			for(int i=0; i<4; i++) {
				while(true) {
					nr+=dr[i]; nc+=dc[i];
					if(nr==r && nc==c) { //임시로 저장해 둔 값,
						map[nr][nc+1]=tmp; break;
					}
					if(nr<r || nc<c || nr>=r+step || nc>=c+step) {
						//범위를 벗어나는 경우에는 방향을 전환해야한다,
						nr-=dr[i]; nc-=dc[i]; break;
					}
					//범위벗어나는 경우가 아니면, 값 덮어씌우기
					map[nr-dr[i]][nc-dc[i]]=map[nr][nc];
				}
			}
			}
			r+=1; c+=1;
			step-=2; //길이가 두개씩 줄어든다!
			
		}
	}
	
	
	
}
