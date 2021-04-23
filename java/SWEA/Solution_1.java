package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1 {
	private static int N;
	
	private static int a[][];
	private static Area map[][];
	private static int support[][]; //
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		for(int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			a=new int[N][N];
			C_map=new Area[N][N];
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					a[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//병력 정보
			map=new Area[N][N];
			for(int i=0; i<N;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					//map에는, 그 지역의 번호와 현재 병력인원들이 들어가 있다.
					map[i][j]=new Area(a[i][j], Integer.parseInt(st.nextToken()));
				}
			}
			support=new int[N][N];
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					support[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int totalCnt=3; //처음에는 3개,
			boolean flag[]=new boolean[4];
//			int time=1;
ex:			while(true) {
				//지역이 2개이상 남아있는 동안, 총 3번의 턴을 실행한다.
				for(int i=1; i<=3; i++) {
					if(flag[i]) continue; //그 지역이 사라진 후라면 더이상 진행 X true,,
					else {
						//그 나라의 존재유무,
						boolean check=false;
						for(int r=0; r<N; r++) {
							for(int c=0; c<N; c++) {
								if(map[r][c].num==i) {
									check=true;
									break;
								}
								
							}
						}
						if(!check) {
							flag[i]=true; 
							totalCnt--;
							if(totalCnt==1) break ex;
							continue; //해당 국가 없음,제외
						}
					}
					//그 나라 존재하면,
//					System.out.println(i+"번째 국가 시작");
					fight(i);
//					System.out.println("fight 후");
//					print();
					support(i);
//					System.out.println("support 후");
//					print();
					add(i);
//					System.out.println("add 후");
//					print();
				}
				
			}
			sb.append("#").append(tc).append(" ").append(finalSol()).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int finalSol() {
		int answer=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].num!=0) answer+=map[i][j].cnt;
			}
		}
		return answer;
	}
	private static void add(int n) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j].cnt+=support[i][j];
			}
		}
	}
	private static void support(int n) {
		//인접한 지역중 다른 나라의 지역이 없는 경우와 있는 경우
		copy(C_map,map);
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(C_map[i][j].num==n) {
					//자기를 중심으로
					if(checkOther(i,j,n)) {
						//다른 나라와 인접한 지역이 있으면
						//인접한 자기네 나라의 5배 초과할 경우만,
						startSecondSupport(i,j,n);
					}else {
						//자기나라만 있는 경우
						//모든 지역으로 1/5 지원
						startSupport(i,j,n);
					}
				}
			}
		}
	}
	private static void startSecondSupport(int r, int c, int n) {
		//자기랑 인접한 자기 나라면서, 자기보다 5배 작을때,
		int now=C_map[r][c].cnt;
		int total=0;
		for(int i=0; i<4; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(C_map[nr][nc].num==n && 5*C_map[nr][nc].cnt<C_map[r][c].cnt) {
				total+=now/5;
				map[nr][nc].cnt+=now/5;
			}
		}
		map[r][c].cnt-=total;
	}
	private static void startSupport(int r, int c, int n) {
		int now=C_map[r][c].cnt;
		int total=0;
		for(int i=0; i<4; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(C_map[nr][nc].num!=0) {
				map[nr][nc].cnt+=now/5;
				total+=now/5;
			}
		}
		map[r][c].cnt-=total;
	}
	private static boolean checkOther(int r, int c, int n) {
		for(int i=0; i<4; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(C_map[nr][nc].num!=n && C_map[nr][nc].num!=0) {
				//다른나라가 있으면
				return true;
			}
		}
		return false;
	}
	private static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j].cnt+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static Area[][]C_map;
	private static void fight(int n) {
		//n지역의 싸움이 시작된다!
		copy(C_map,map);
		//c_map으로 check하고, 실제 움직임은 map에서 진행하기
		//완전탐색
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//자기자신의 지역이나, 0인지역은 pass하고 , 다른 지역을 탐색
				if(C_map[i][j].num==0 || C_map[i][j].num==n) continue;
				
				//여기까지 온 다른 나라의 인접한 4곳을 살펴봐야겠지?
				if(check(i,j,n)) { //(i,j)의 인접한 4곳중에 나라n인 나라와 5배차이나는지,
					calculateFight(i,j,n);
				}
			}
		}
	}
	private static void calculateFight(int r, int c, int n) {
		//num이 n인애들의 4분의 1씩 보내기
		int total=0;
		for(int i=0; i<4; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(C_map[nr][nc].num==n) {
				//움직이는 것만 map에서하기
				int tmp=(C_map[nr][nc].cnt)/4;
				map[nr][nc].cnt-=tmp;
				total+=tmp;
			}
		}
		//막판에 r,c 자리에 total만큼 빼면서 n으로 자리 바뀜,
		map[r][c].num=n;
		map[r][c].cnt=(total-map[r][c].cnt);
	}
	private static int []dr= {1,-1,0,0};
	private static int []dc= {0,0,1,-1};
	
	private static boolean check(int r, int c, int n) {
		//4방탐색
		int sum=C_map[r][c].cnt; //상대나라팀의 합,
		int mySum=0;
		for(int i=0; i<4; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
			if(C_map[nr][nc].num==n) {
				mySum+=C_map[nr][nc].cnt;
			}
		}
		//확인,
		if(sum*5<mySum) return true;
		return false;
	}

	private static void copy(Area[][] c_map, Area[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				c_map[i][j]=new Area(map[i][j].num, map[i][j].cnt);
			}
		}
	}

	private static class Area{
		int num; //지역 번호
		int cnt; //사람 수 
		public Area(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}
		
	}
}
