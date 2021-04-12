package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_5644_1 {
	private static int M, A, answer;
	private static int a[], b[];
	private static ArrayList<Integer> map[][];
	private static Info bc[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine());
			M=Integer.parseInt(st.nextToken()); //알아볼 이동 시간
			A=Integer.parseInt(st.nextToken()); //갯수
			a=new int[M+1]; b=new int[M+1];
			a[0]=0; b[0]=0;
			st=new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				a[i]=Integer.parseInt(st.nextToken()); // 이동안함,상,우,좌,하
			}
			st=new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++)
				b[i]=Integer.parseInt(st.nextToken());
			
			bc=new Info[A+1];
			for(int i=1; i<=A; i++) {
				st=new StringTokenizer(br.readLine());
				int c=Integer.parseInt(st.nextToken())-1;
				int r=Integer.parseInt(st.nextToken())-1;
				int range=Integer.parseInt(st.nextToken());
				int p=Integer.parseInt(st.nextToken());
				bc[i]=new Info(r,c,range,p);
			}//end of input
			//입력 다 했으니까 이제 map에 체크해주기
			map=new ArrayList[10][10];
			for(int i=0; i<10; i++)
				for(int j=0; j<10; j++)
					map[i][j]=new ArrayList<>();
			name();
			int a_r=0, a_c=0; int b_r=9, b_c=9;
			answer=0;
			for(int m=0; m<=M; m++) {
				a_r+=dr[a[m]];
				a_c+=dc[a[m]];
				b_r+=dr[b[m]];
				b_c+=dc[b[m]];
//				System.out.println(a_r+" "+a_c+" "+b_r+" "+b_c);
				move(a_r, a_c, b_r, b_c);
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}
	private static int dr[]= {0,-1,0,1,0};
	private static int dc[]= {0,0,1,0,-1};
	private static void move(int a_r, int a_c, int b_r, int b_c) {
		ArrayList<Pair> a=new ArrayList<>();
		ArrayList<Pair> b=new ArrayList<>();
		for(int i=0; i<map[a_r][a_c].size(); i++) {
			a.add(new Pair(map[a_r][a_c].get(i), bc[map[a_r][a_c].get(i)].p));
		}
		
		for(int i=0; i<map[b_r][b_c].size(); i++) {
			b.add(new Pair(map[b_r][b_c].get(i), bc[map[b_r][b_c].get(i)].p));
		}
		Collections.sort(a);
		Collections.sort(b);
		
		if(a.size()==0 || b.size()==0) {
			if(a.size()!=0) answer+=a.get(0).power;
			else if(b.size()!=0) answer+=b.get(0).power;
		}else {
			//무조건 사이즈가 1이상인것들
			if(a.get(0).idx==b.get(0).idx) {
				if(a.size()==1 && b.size()==1) {
					answer+=a.get(0).power; //나눠가져야하니까
				}else if(a.size()==1) {
					answer+=a.get(0).power;
					answer+=b.get(1).power;
				}else if(b.size()==1) {
					answer+=b.get(0).power;
					answer+=a.get(1).power;
				}else {
					int tmp1=a.get(1).power;
					int tmp2=b.get(1).power;
					int now=tmp1>tmp2?tmp1:tmp2;
					answer+=(a.get(0).power+now);
				}
			}else {
				answer+=a.get(0).power;
				answer+=b.get(0).power;
			}
		}
		
		
		
		
	}
	
	private static class Pair implements Comparable<Pair>{
		int idx, power;

		public Pair(int idx, int power) {
			super();
			this.idx = idx;
			this.power = power;
		}

		@Override
		public int compareTo(Pair o) {
			return o.power-this.power;
		}
		
	}
	private static void name(){
		//이름 체크해주기!
		for(int a=1; a<=A; a++) {
			int r=bc[a].r; int c=bc[a].c; int range=bc[a].range;
			//처음에 윗쪽
			int cnt=0;
			for(int i=range; i>=0; i--) {
				for(int j=((-1)*cnt); j<=cnt; j++) {
					if(r-i>=0 && c+j>=0 && c+j<10 && r-i<10) {
						map[r-i][c+j].add(a);
					}
				}
				cnt++;
			}
			//아랫쪽 =
			cnt=0;
			for(int i=range; i>=1; i--) {
				for(int j=((-1)*cnt); j<=cnt; j++) {
					if(r+i>=0 && r+i<10 && c+j>=0 && c+j<10) {
						map[r+i][c+j].add(a);
					}
				}
				cnt++;
			}
		}
	}
	private static class Info{
		int r, c; //시작점
		int range; //범위
		int p; //성능
		public Info(int r, int c, int range, int p) {
			super();
			this.r = r;
			this.c = c;
			this.range = range;
			this.p = p;
		}
		
	}
}
