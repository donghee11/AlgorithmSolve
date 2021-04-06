package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//미세먼지 안녕!
public class Main_17144 {
	private static int R, C, T; //R*C 쿠기, T초가 지난후계산
	private static int map[][];
	private static int end;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		end=0; //공기청정기의 2번째 행, 열은 무조건 0열
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		map=new int[R][C];
		for(int i=0; i<R; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) end=i;
			}
		}
		//공기청정기는 [end-1][0]과 [end][0]에 위치
		//미세먼지 확산 + 정화
		for(int i=0; i<T; i++) {
			spread();
			Cleanone(end-1);
			Cleantwo(end);
		}
		
		//모두 끝난후에 계산,
		int answer=0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				answer+=map[i][j];
			}
		}
		System.out.println(answer+2);
	}
	
	private static void print() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void Cleantwo(int r) {
		//시계방향
		//위로
		for(int i=r+1; i<R-1; i++) {
			map[i][0]=map[i+1][0];
		}
		//왼쪽
		for(int i=0; i<C-1; i++) {
			map[R-1][i]=map[R-1][i+1];
		}
		//아래쪽
		for(int i=R-1; i>=r+1; i--) {
			map[i][C-1]=map[i-1][C-1];
		}
		//오른쪽
		for(int i=C-1; i>=2; i--) {
			map[r][i]=map[r][i-1];
		}
		map[r][1]=0;
	}
	private static void Cleanone(int r) {
		//밑으로
		for(int i=r-1; i>=1; i--) {
			map[i][0]=map[i-1][0];
		}
		//왼쪽으로
		for(int i=0; i<C-1; i++) {
			map[0][i]=map[0][i+1];
		}
		//위로
		for(int i=0; i<r; i++) {
			map[i][C-1]=map[i+1][C-1];
		}
		//오른쪽으로
		for(int i=C-1; i>=2; i--) {
			map[r][i]=map[r][i-1];
		}
		map[r][1]=0;
	}
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	//모든 점에서 미세먼지가 확산이 일어난다.
	private static void spread() {
		//확산이 일어나는 부분은 C_map에서 관리하기,
		int C_map[][]=new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]==-1 || map[i][j]==0) continue; //공기청정기나 일반 빈칸은 확산안일어나고
				//확산이 일어나야되는 칸이면 4방향을 조사,
				int cnt=0; //갯수세기
				int tmp=map[i][j]/5;
				for(int d=0; d<4; d++) {
					int nr=i+dr[d]; int nc=j+dc[d];
					if(nr>=0 && nc>=0 && nr<R && nc<C && map[nr][nc]!=-1) {
						C_map[nr][nc]+=tmp; cnt++;
					}
				}
				//확산이 모두 끝난후, 이자리에 남은 미세먼지 양 계산
				C_map[i][j]+=map[i][j]-(tmp*cnt);
			}
		}
		//모든 점에 대해 확산이 일어났으면, 다시 map에 돌려놓기
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				map[i][j]=C_map[i][j];
		map[end-1][0]=-1;
		map[end][0]=-1;
	}
}
