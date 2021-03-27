package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
//3 1
//1 2 3 4 5 6 7 8
//9 10 11 12 13 14 15 16
//17 18 19 20 21 22 23 24
//25 26 27 28 29 30 31 32
//33 34 35 36 37 38 39 40
//41 42 43 44 45 46 47 48
//49 50 51 52 53 54 55 56
//57 58 59 60 61 62 63 64
//마법사 상어와 파이어스톰
//구현해야할 순서: 부분 격자에서 시계방향으로 90도 회전시키기, 
//남아있는 얼음의 양과, 가장 큰 덩어리가 차지하는 칸의갯수 출력하기
public class Main_20058_2 {
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
				max=Math.max(max, dfs(i,j));
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
	
	//덩어리찾기-dfs로 구현한다면?
	private static int dfs(int x, int y) {
		visit[x][y]=true;
		int cnt=1;
		for(int i=0; i<4; i++) {
			int nr=x+dr[i]; int nc=y+dc[i];
			if(nr>=0 && nc>=0 && nr<l && nc<l && map[nr][nc]>0 && !visit[nr][nc]) {
				visit[nr][nc]=true;
				cnt+=dfs(nr,nc);
			}
		}
		return cnt;
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
	private static void rotate(int r, int c, int step) {
		int squreCnt=step/2; //돌려야하는 사각형의 갯수
		for(int n=0; n<squreCnt; n++) {
			int start_r=r+n;
			int start_c=c+n;
			int end_r=r+step-n-1;
			int end_c=c+step-n-1;
			
			//보관해둘 부분,
			int idx=0;
			int[]temp=new int[step-1];
			for(int i=start_r; i<end_r; i++) { //임시로 저장하는 부분
				temp[idx++]=map[i][start_c];
			}
			idx=start_r;
			for(int i=start_c; i<end_c; i++) {
				map[idx++][start_c]=map[end_r][i];
			}
			idx=start_c;
			for(int i=end_r; i>start_r; i--) {
				map[end_r][idx++]=map[i][end_c];
			}
			
			idx=end_r;
			for(int i=end_c; i>start_c; i--) {
				map[idx--][end_c]=map[start_r][i];
			}
			idx=0;
			for(int i=end_c; i>start_c; i--) {
				map[start_r][i]=temp[idx++];
			}
		}
	}
	
	
	
}
