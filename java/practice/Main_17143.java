package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17143 {
	private static int R, C, M, answer;
	private static Shark shark[];
	private static int map[][];
	private static int[][] C_map;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		shark=new Shark[M+1]; //상어는 1~M까지 있음
		map=new int[R][C];
		C_map=new int[R][C];
		//상어의 정보
		for(int m=1; m<=M; m++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int z=Integer.parseInt(st.nextToken());
			shark[m]=new Shark(r-1,c-1,s,d-1,z,true);
			map[r-1][c-1]=m; //상어의 인덱스 남겨놓기
		}
		
		for(int c=0; c<C; c++) {
			//낚시꾼이 C 열에 있을때
			catchFish(c); //c열에서 가장 가까운 r이 작은 물고기를 잡는다
			moveShark(); //상어가 이동한다
		}
		System.out.println(answer);
		
	}
	private static int dr[]= {-1,1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static void moveShark() {
		
		Init(); //C_map초기화
		
		//1번 상어부터 이동하기
		for(int i=1; i<=M; i++) {
			if(!shark[i].flag) continue; //죽었으면 pass
			
			int d=shark[i].d;
			//실질적으로 이동해야하는 거리
			int s=shark[i].s; //일일히 이동시키지 말고 줄이자
			if(d==0 || d==1) {
				//상하로 움직이는 경우에는 
				s%=(2*R-2);
			}else s%=(2*C-2);
			
			moveFunc(i,s);
		}
		
		//이동끝났으면 C_map에서 .map으로
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				map[i][j]=C_map[i][j];
	}
	private static void moveFunc(int x, int s) {
		//x 번째 상어를 s 만큼 움직이기
		int r=shark[x].r; int c=shark[x].c; int d=shark[x].d;
		int nr=r; int nc=c;
		for(int i=0; i<s; i++) {
			nr+=dr[d]; nc+=dc[d];
			//얘가 범위를 나가는경우면
			
			if(nr<0 || nc<0 || nr>=R || nc>=C) {
				nr-=dr[d]; nc-=dc[d];
				nr-=dr[d]; nc-=dc[d];
				d=changeDir(d);
				
			}
		}

		//C_map에 상어 있는지 확인하기
		if(C_map[nr][nc]>0) {
			int sh=C_map[nr][nc];
			if(shark[sh].z>shark[x].z) {
				//상어 변함없고 얘 죽이면됨
				shark[x].flag=false;
			}else {
				//다른 경우에는 
				C_map[nr][nc]=x;
				shark[x].r=nr; shark[x].c=nc; shark[x].d=d;
				shark[sh].flag=false;
			}
		}else {
			C_map[nr][nc]=x;
			shark[x].r=nr; shark[x].c=nc; shark[x].d=d;
		}
		
	}
	private static int  changeDir(int d) {
		switch(d) {
		case 0:
			return 1;
		case 1:
			return 0;
		case 2:
			return 3;
		case 3:
			return 2;
		}
		return 0;
	}
	private static void Init() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				C_map[i][j]=0;
			}
		}
	}
	
	private static void catchFish(int c) {
		for(int i=0; i<R; i++) {
			if(map[i][c]>0) {
				//그 위치에 상어가 있으면 잡고 끝내기
				answer+=shark[map[i][c]].z;
				shark[map[i][c]].flag=false;
				map[i][c]=0; //이제 상어 없다
				break;
			}
		}
	}

	private static class Shark{
		int r,c,s,d,z;
		boolean flag;
		public Shark(int r, int c, int s, int d, int z, boolean flag) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.flag = flag;
		}
		
	}
}
