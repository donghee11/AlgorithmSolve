package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17143 {
	private static int R,C,M;
	private static int answer; //낚시왕이 잡은 상어 크기의 합
	private static int map[][]; //맵에는 어떤 상어가 존재하는지만
	private static Shark shark[];
	//1~M, 0은 빈칸
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken()); //상어의수
		map=new int[R][C];
		shark=new Shark[M+1];
		for(int i=1; i<=M; i++) {
			//s 속력, d 이동방향(위, 아래, 오른쪽, 왼쪽)
			//
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken())-1;
			int z=Integer.parseInt(st.nextToken()); //크기
			shark[i]=new Shark(r,c,s,d,z, true);
			map[r][c]=i;
			//비교해야하는건 i번째 shark와의 z 비교해야함
		}//상어의 정보
		for(int i=0; i<C; i++) {
			catchShark(i);
			moveShark(); //상어가 이동한다.
		}
		System.out.println(answer);
	}
	
	private static void moveShark() {
		//모든상어 움직이기
		//맵 이동할 때는 C_map에다가 이동을 시켜서, 결과 저장하기
		int[][]C_map=new int[R][C]; //처음엔 다 0!
		
		for(int i=1; i<=M; i++) {
			if(!shark[i].flag) continue; //상어가 죽어있으면 패스
			
			int r=shark[i].r; int c=shark[i].c;
			int d=shark[i].d; int s=shark[i].s;
			int z=shark[i].z;
			
			//이동하게 될 위치
			int nr=r,nc=c;
			if(d==0) {//상
				s%=(2*R-2);
				int len=r;
				if(s<=2*len) {
					nr-=s;
					if(nr<0) {
						nr*=(-1);
						shark[i].d=1;
					}
				}else {
					s-=2*len; //자기자신위치에서 시작하는셈,
					nr+=s; shark[i].d=1;
					if(nr>=R) {
						shark[i].d=0;
						nr=R-1-Math.abs(nr-R+1); 
					}
				}
				//nc는 그대로임..
			}else if(d==1) {//하
				s%=(2*R-2);
				int len=R-1-r;
				if(s<=2*len) {
					nr+=s;
					if(nr>=R) {
						//더 이동한 횟수는 (nr-R)
						nr=R-1-Math.abs(nr-R+1);
						shark[i].d=0;
					}
				}else {
					s-=2*len;
					nr-=s;
					shark[i].d=0;
					if(nr<0) {
						shark[i].d=1;
						nr*=(-1);
					}
				}
			}else if(d==3) { //좌
				s%=(2*C-2);
				int len=c;
				//nc움직이기
				if(s<=2*len) {
					nc-=s;
					if(nc<0) {
						nc*=(-1);
						shark[i].d=2;
					}
				}else {
					s-=2*len;
					nc+=s;
					shark[i].d=2;
					if(nc>=C) {
						shark[i].d=3;
						nc=C-1-Math.abs(nc-C+1);
					}
				}
			}else if(d==2) { //우
				s%=(2*C-2);
				int len=C-1-c;
				if(s<=2*len) {
					nc+=s;
					if(nc>=C) {
						nc=C-1-Math.abs(nc-C+1);
						shark[i].d=3;
					}
				}else {
					s-=2*len;
					nc-=s;
					shark[i].d=3;
					if(nc<0) {
						nc*=(-1);
						shark[i].d=2;
					}
				}
			}
//			System.out.println(i+" "+nr+" "+nc+" "+shark[i].d);
			//여기에 새롭게 이동한 물고기를 c_map에 두자!
			if(C_map[nr][nc]==0) {
				C_map[nr][nc]=i;
				shark[i].r=nr; shark[i].c=nc;
			}else { //다른 상어있으면 크기비교
				if(shark[C_map[nr][nc]].z>shark[i].z) {
					//원래 있던애가 크기가 더 크면
					shark[i].flag=false; //지금 상어는 죽어버린다
				}else {
					//새로 들어올 상어가 크기가 더크면
					//전꺼 죽이고 이번껄로 넣기
					shark[C_map[nr][nc]].flag=false;
					C_map[nr][nc]=i;
					shark[i].r=nr; shark[i].c=nc;
				}
				
			}
		}
		
		//모든 상어의 이동이 끝났다면,
		//C_map에서 빈칸 아닌것들 옮겨주기
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				map[i][j]=C_map[i][j];
			}
		}

	}


	private static void catchShark(int c) {
		//현재 낚시꾼은 위치가 c이다,
		//여기에서 가장 가까운, 상어를 잡아서 없애기,
		
		for(int i=0; i<R; i++) {
			if(map[i][c]>0) {
				//이 인덱스에 해당하는 상어를 잡고 break
				answer+=shark[map[i][c]].z;
//				System.out.println(answer);
				shark[map[i][c]].flag=false;
				map[i][c]=0;
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
			this.flag=flag;
		}
		
	}
}
