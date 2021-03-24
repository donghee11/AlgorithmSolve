package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//마법사 상어와 토네이도
public class Main_20057 {
	private static int dr[]= {0,1,0,-1};
	private static int dc[]= {-1,0,1,0};
	
	private static int dr1[][]={ {-2,-1,-1,-1,0,1,1,1,2,0},
			{0,1,0,-1,2,1,0,-1,0,1}, {-2,-1,-1,-1,0,1,1,1,2,0}, {0,-1,0,1,-2,-1,0,1,0,-1}};

	
	private static int dc1[][]= {{0,-1,0,1,-2,-1,0,1,0,-1},
			{-2,-1,-1,-1,0,1,1,1,2,0}, {0,1,0,-1,2,1,0,-1,0,1}, {-2,-1,-1,-1,0,1,1,1,2,0}
	};
	private static int per[]= {2,10,7,1,5,10,7,1,2};
	
	private static int N, map[][], total;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				total+=map[i][j];
			}
		}//end of input
		
		//토네이도는 가운데 칸 부터, 시작점:N/2,N/2
		int r=N/2; int c=N/2;
		int cnt=1; //이동횟수
		int dir=0; //움직일방향
		int change=0;
		int answer=0;
ex:		while(true) {
			//토네이도가 이동한 새로운 위치에(nr,nc)에 모래가 있으면(0이아니면) y위치를 기준으로 흩뿌리기 시작,
			change++;
			for(int i=0; i<cnt; i++) {
				int tmp=0;
				r+=dr[dir];
				c+=dc[dir]; //이동된 좌표: (r,c)
				if(c<0 && r==0) break ex;
				int x=map[r][c];
				map[r][c]=0;
				if(x!=0) {
					for(int j=0; j<9; j++) {
						int nr=r+dr1[dir][j];
						int nc=c+dc1[dir][j]; //이동해야할 좌표는 여기
						int result=(int)(x*(double)per[j]/100);
						tmp+=result;
						if(nr>=0 && nc>=0 && nr<N && nc<N) { //범위만족하고
							map[nr][nc]+=result;
						}else { //범위나가는 경우
							answer+=result;
						}
					}
					int rr=r+dr1[dir][9];
					int cc=c+dc1[dir][9];
					
					if(rr>=0 && cc>=0 && rr<N && cc<N) {
						map[rr][cc]+=(x-tmp);
					}else {
						answer+=(x-tmp);
					}
					
					
				}
			}
			
			dir=(dir<3)?dir+1:0;
			if(change==2) {
				change=0; cnt++;
			}
		}//end of while
		System.out.println(answer);
	}
	
}
