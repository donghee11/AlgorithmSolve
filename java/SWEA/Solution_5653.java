package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//줄기세포 
public class Solution_5653 {
	private static int N, M,K;
	private static ArrayList<Virus> list;
	private static int map[][], C_map[][];
	private static ArrayList<Virus> virus;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			list=new ArrayList<>();
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			map=new int[600][600]; //최대치로 잡아보자
			virus=new ArrayList<>();
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					int x=Integer.parseInt(st.nextToken());
					if(x>0) {
						map[300+i][300+j]=x;
						list.add(new Virus(300+i,300+j,x,x,false));
					}
				}
			}
			for(int t=1; t<=K; t++) {
				checkVirus();
			}
			
			//살아있는 줄기세포 체크하기
			sb.append("#").append(tc).append(" ").append(list.size()).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}

	//모든 경우를 탐색하면서 본다면?
	private static void checkVirus() {
		//동시 번식 체크해줘야함..
		//크기 큰 애들부터 sorting해주자!
		Collections.sort(list);
		int size=list.size();
		for(int i=0; i<size; i++) {
			//바이러스가 비활성상태라면
			Virus v=list.get(i);
			if(!v.active) {
				v.time-=1; //시간을 하나 줄이기
				if(v.time==0) v.active=true; //활성화시켜준다.
			}else {
				//이미 활성화 된 상태라면, 이동시켜야함!
				spreadVirus(i);
				//얘는 남은 시간이 -1 되어야지!
				v.time+=1;
				if(v.time==v.k) {
					map[v.r][v.c]=-1;
					list.remove(i);
					i--;
					size--;
				}
			}
			
		}
		
		
	}
	private static int dr[]= {1,-1,0,0};
	private static int dc[]= {0,0,1,-1};
	private static ArrayList<Virus> tmp;
	private static int time[][];
	private static void spreadVirus(int index) {
		//index에 해당하는 바이러스 처리해주기
//		time=new int[600][600];
//		tmp=new ArrayList<>();
		Virus v=list.get(index);
		int r=v.r; int c=v.c; int k=v.k;
		for(int i=0; i<4; i++) {
			int nr=r+dr[i]; int nc=c+dc[i];
			if(map[nr][nc]>0 || map[nr][nc]==-1) continue; //이미 바이러스 있는 경우 pass
			//아니라면 리스트에 넣어주기,
			list.add(new Virus(nr,nc,k,k,false));
			map[nr][nc]=k;
		}
	}
	
	private static class Virus implements Comparable<Virus>{
		int r, c, k, time;
		boolean active;
		public Virus(int r, int c, int k, int time,boolean active) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
			this.time=time;
			this.active = active;
		}
		@Override
		public int compareTo(Virus o) {
			return o.k-this.k; //크기 순으로 정렬해서 분열시키기
		}
		
	}
}
