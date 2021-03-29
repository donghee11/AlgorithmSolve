package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499 {
	private static int N, M, x,y,K; //세로, 가로, 주사위놓을곳, 명령의갯수
	private static int map[][];
	private static int dr[]= {0,0,-1,1};
	private static int dc[]= {1,-1,0,0,};
	private static int ud[];
	private static int lr[];
	private static int up;
	private static int right;
	private static int back;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		x=Integer.parseInt(st.nextToken());
		y=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		int dia[]=new int[7]; //1~6까지 쓴다.
		lr=new int[4]; ud=new int[4];
		lr[0]=1; lr[1]=3; lr[2]=6; lr[3]=4;
		ud[0]=1; ud[1]=5; ud[2]=6; ud[3]=2;
		int r=x; int c=y;
		for(int k=0; k<K; k++) {
			int dir=Integer.parseInt(st.nextToken())-1;
			//이동하려는 좌표
			r+=dr[dir]; c+=dc[dir];
			if(r<0 || c<0 || r>=N || c>=M) {
				//못가면 이동 못함
				r-=dr[dir]; c-=dc[dir];
				continue; //범위 넘어가면 무시,
			}
			//주사위의 현재 면 구하기,
			rotate(dir);
			//2가지경우가 있다. 0인경우와 0이 아닌경우
			if(map[r][c]==0) {
				int badak=findOpposite(ud[0]);
				map[r][c]=dia[badak];
				sb.append(dia[ud[0]]).append("\n");
			}else { //0이 아니면
				int tmp=map[r][c];
				int badak=findOpposite(ud[0]);
				dia[badak]=tmp;
				map[r][c]=0;
				sb.append(dia[ud[0]]).append("\n");
			}
		}//end of input
		System.out.println(sb.toString());
	}
	//주사위의 반댓면 구하는 함수
	
	private static void rotate(int dir) {
		if(dir==0) {//동쪽회전
			int tmp=lr[3];
			for(int i=3; i>=1; i--) {
				lr[i]=lr[i-1];
			}
			lr[0]=tmp;
			//ud도 바꿔야함
			ud[0]=lr[0];
			ud[2]=lr[2];
			
		}else if(dir==1) { //서쪽회전
			int tmp=lr[0];
			for(int i=0; i<3; i++) {
				lr[i]=lr[i+1];
			}
			lr[3]=tmp;
			ud[0]=lr[0];
			ud[2]=lr[2];
		}else if(dir==2) {//북쪽회전
			int tmp=ud[0];
			for(int i=0; i<3; i++)
				ud[i]=ud[i+1];
			ud[3]=tmp;
			lr[0]=ud[0]; lr[2]=ud[2];
		}else {
			int tmp=ud[3];
			for(int i=3; i>=1; i--) {
				ud[i]=ud[i-1];
			}
			ud[0]=tmp;
			lr[0]=ud[0]; lr[2]=ud[2];
		}
	}
	private static int findOpposite(int n) {
		switch(n) {
		case 1:
			return 6;
		case 2:
			return 5;
		case 3: 
			return 4;
		case 4:
			return 3;
		case 5:
			return 2;
		case 6:
			return 1;
		default:
			return 0;
			
		}
	}
}
