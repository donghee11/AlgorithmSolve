package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656 {
	private static int N, W, H,answer;
	private static int [][]map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			map=new int[H+1][W+1];
//			height=new int[W+1]; //각 행마다 몇개의 벽돌이 있는지 갯수세서 높이를 확인하자
			answer=Integer.MAX_VALUE;
			for(int i=1; i<=H; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=1; j<=W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}//end of input
			comb(0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			
		}//end of tc
		System.out.println(sb.toString());
	}
	private static void comb(int cnt) {
		if(cnt==N) {
			//다 뽑았으면 끝
			int ans=0;
			for(int i=1; i<=H; i++)
				for(int j=1; j<=W; j++)
					if(map[i][j]>0) ans++;
			
			answer=Math.min(answer, ans);
			return;
		}
		
		//모든 행을 조사하면서
		if(checkNoBlock()) {
			answer=0; return;
		}
		//한번의 턴마다 행중에 하나를 선택하기
		for(int i=1; i<=W; i++) {
			//해당 행에 벽돌이있으면
			checkCol(i, cnt);
		}
	}
	private static boolean checkNoBlock() {
		for(int i=1; i<=H; i++) {
			for(int j=1; j<=W; j++) {
				if(map[i][j]>0) return false;
			}
		}
		return true;
	}
	
	private static void checkCol(int c, int cnt) {
		for(int i=1; i<=H;i++) {
			if(map[i][c]>0) {
				//0보다 커지는 순간에 제거하기
				go(i,c,cnt); break;
			}
		}
		//여기까지오면 불가능한것
//		comb(cnt+1);
	}

	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static void go(int x, int y, int cc) {
		//해당 벽돌을 깬다고가정하면 해당 map칸수만큼  bfs
		int[][]C_map=new int[H+1][W+1];
		C_map=copy(C_map,map);
		Queue<int[]> q=new LinkedList<>();
		q.add(new int[] {x,y,map[x][y]});
		map[x][y]=0;
		while(!q.isEmpty()) {
			int[]p=q.poll();
			int r=p[0]; int c=p[1];
			int cnt=p[2]-1;
			for(int i=0; i<4; i++) {
				//한 방향에 대해서 크기-1만큼
				for(int j=1; j<=cnt; j++) {
					int nr=r+j*dr[i]; int nc=c+j*dc[i];
					if(nr>0 && nc>0 && nr<=H && nc<=W && map[nr][nc]>0) {
						q.add(new int[] {nr,nc, map[nr][nc]});
						map[nr][nc]=0;
					}
				}
			}
		}
		downBlock();
		comb(cc+1);
//		print();
		map=copy(map,C_map);
	}
	private static void downBlock() {
		//모든 행에 대해 다 아래로 내리기
		for(int i=1; i<=W; i++) {
			//i행 골랐으면
			for(int j=H; j>0; j--) {
				if(map[j][i]>=1) {
					//내릴 수 있을 때까지 내려보기
					int t=map[j][i];
					int tmp=j+1;
					while(tmp<=H && map[tmp][i]==0) {
						//0일때까지 계속내려
						tmp++;
					}
					map[j][i]=0;
					map[tmp-1][i]=t;
					
				}
			}
		}
	}
	private static int[][] copy(int[][] c_map, int[][] map2) {
		for(int i=1; i<=H; i++)
			for(int j=1; j<=W; j++)
				c_map[i][j]=map2[i][j];
		return c_map;
	}
	
	
}
