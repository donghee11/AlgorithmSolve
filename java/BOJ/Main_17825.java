package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//주사위 윷놀이
public class Main_17825 {
	private static int dice[], answer;
	private static Horse horse[];
	private static boolean[] visit1;
	private static boolean[] visit2;
	private static boolean[] visit3;
	private static boolean[] visit4;
	private static boolean[] visit5;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		dice=new int[10];
		for(int i=0; i<10; i++) {
			dice[i]=Integer.parseInt(st.nextToken());
		}
		horse=new Horse[4];
		for(int i=0; i<4; i++) {
			horse[i]=new Horse(1,-1,true);
		}
		//무조건 이동은 dice만큼 해야하는 상황,
		visit1=new boolean[20];
		visit2=new boolean[4];
		visit3=new boolean[3];
		visit4=new boolean[4];
		visit5=new boolean[4];
		dfs(0,0);
		System.out.println(answer);
		
	}
	
	private static void dfs(int cnt, int sum) {
		if(cnt==10) {
			answer=Math.max(answer, sum);
			return; //10번 다 돌았으면 끝!
		}
		
		for(int i=0; i<4; i++) {
			//총 4개의 말을 움직인다.
			if(!horse[i].flag) continue; //이미 죽은말이면 패스,
			//현재 라인을 따라서 dice[cnt]만큼 이동했을 때,
			int oldloc=horse[i].idx;
			int newloc=oldloc+dice[cnt];
			switch(horse[i].line) {
			case 1:
				if(newloc>=20) {
					horse[i].flag=false;
					visit1[oldloc]=false;
					dfs(cnt+1, sum);
					horse[i].flag=true;
					visit1[oldloc]=true;
				}else if(red[newloc]==10) {
					if(visit2[0]) break;
					horse[i].line=2;
					horse[i].idx=0;
					if(oldloc!=-1) visit1[oldloc]=false;
					visit2[0]=true;
					dfs(cnt+1, sum+10);
					horse[i].line=1;
					horse[i].idx=oldloc;
					if(oldloc!=-1) visit1[oldloc]=true;
					visit2[0]=false;
				}else if(red[newloc]==20) {
					if(visit3[0]) break;
					horse[i].line=3;
					horse[i].idx=0;
					if(oldloc!=-1) visit1[oldloc]=false;
					visit3[0]=true;
					dfs(cnt+1, sum+20);
					horse[i].line=1;
					horse[i].idx=oldloc;
					if(oldloc!=-1) visit1[oldloc]=true;
					visit3[0]=false;
				}else if(red[newloc]==30) {
					if(visit4[0]) break;
					horse[i].line=4;
					horse[i].idx=0;
					if(oldloc!=-1) visit1[oldloc]=false;
					visit4[0]=true;
					dfs(cnt+1, sum+30);
					horse[i].line=1;
					horse[i].idx=oldloc;
					if(oldloc!=-1) visit1[oldloc]=true;
					visit4[0]=false;
				}else if(red[newloc]==40) {
					//여기에는 visit5도 확인해야한다.
					if(visit1[newloc] || visit5[3]) break;
					horse[i].idx=newloc;
					visit1[oldloc]=false;
					visit1[newloc]=true;
					visit5[3]=true;
					dfs(cnt+1, sum+red[newloc]);
					horse[i].idx=oldloc;
					visit1[oldloc]=true;
					visit1[oldloc]=true;
					visit1[newloc]=false;
					visit5[3]=false;
					
				}else {
					if(visit1[newloc] )break;
					horse[i].idx=newloc;
					if(oldloc!=-1) visit1[oldloc]=false;
					visit1[newloc]=true;
					dfs(cnt+1, sum+red[newloc]);
					horse[i].idx=oldloc;
					if(oldloc!=-1) visit1[oldloc]=true;
					visit1[newloc]=false;
				}
				break;
				
			case 2:
				if(newloc<=3) {
					if(visit2[newloc]) break;
					horse[i].idx=newloc;
					visit2[oldloc]=false;
					visit2[newloc]=true;
					dfs(cnt+1, sum+blue2[newloc]);
					visit2[oldloc]=true;
					visit2[newloc]=false;
					horse[i].idx=oldloc;
				}else {
					newloc-=4;
					if(newloc>=4) {
						horse[i].flag=false;
						visit2[oldloc]=false;
						dfs(cnt+1, sum);
						visit2[oldloc]=true;
						horse[i].flag=true;
					}else {
						if(visit5[newloc]) break;
						horse[i].idx=newloc;
						horse[i].line=5;
						visit2[oldloc]=false;
						visit5[newloc]=true;
						dfs(cnt+1, sum+blue5[newloc]);
						horse[i].line=2;
						visit2[oldloc]=true;
						visit5[newloc]=false;
						horse[i].idx=oldloc;
					}
				}
				break;
			case 3:
				if(newloc<=2) {
					if(visit3[newloc]) break;
					horse[i].idx=newloc;
					visit3[oldloc]=false;
					visit3[newloc]=true;
					dfs(cnt+1, sum+blue3[newloc]);
					visit3[oldloc]=true;
					visit3[newloc]=false;
					horse[i].idx=oldloc;
				}else {
					newloc-=3;
					if(newloc>=4) {
						horse[i].flag=false;
						visit3[oldloc]=false;
						dfs(cnt+1, sum);
						visit3[oldloc]=true;
						horse[i].flag=true;
					}else {
						if(visit5[newloc]) break;
						horse[i].line=5;
						horse[i].idx=newloc;
						visit3[oldloc]=false;
						visit5[newloc]=true;
						dfs(cnt+1, sum+blue5[newloc]);
						horse[i].idx=oldloc;
						visit3[oldloc]=true;
						visit5[newloc]=false;
						horse[i].line=3;
					}
				}
				break;
			case 4:
				if(newloc<=3) {
					if(visit4[newloc]) break;
					horse[i].idx=newloc;
					visit4[oldloc]=false;
					visit4[newloc]=true;
					dfs(cnt+1, sum+blue4[newloc]);
					visit4[oldloc]=true;
					visit4[newloc]=false;
					horse[i].idx=oldloc;
				}else {
					newloc-=4;
					if(newloc>=4) {
						horse[i].flag=false;
						visit4[oldloc]=false;
						dfs(cnt+1, sum);
						visit4[oldloc]=true;
						horse[i].flag=true;
					}else {
						if(visit5[newloc]) break;
						horse[i].idx=newloc;
						visit4[oldloc]=false;
						visit5[newloc]=true;
						horse[i].line=5;
						dfs(cnt+1, sum+blue5[newloc]);
						horse[i].idx=oldloc;
						visit4[oldloc]=true;
						visit5[newloc]=false;
						horse[i].line=4;
					}
				}
				break;
			case 5:
				if(newloc<=3) {
					if(visit5[newloc]) break;
					horse[i].idx=newloc;
					visit5[newloc]=true;
					visit5[oldloc]=false;
					dfs(cnt+1, sum+blue5[newloc]);
					visit5[oldloc]=true;
					visit5[newloc]=false;
					horse[i].idx=oldloc;
				}else {
					horse[i].flag=false;
					visit5[oldloc]=false;
					dfs(cnt+1, sum);
					visit5[oldloc]=true;
					horse[i].flag=true;
				}
				break;
			}
				
		}
	}
	private static int red[]= {2,4,6,8,10,12,14,16,18,20,22,24,
			26,28,30,32,34,36,38,40};
	private static int blue2[]= {10,13,16,19};
	private static int blue3[]= {20,22,24};
	private static int blue4[]= {30,28,27,26};
	private static int blue5[]= {25,30,35,40};
	private static class Horse{
		int line, idx; //어느라인, 어느 위치에 존재하느지
		boolean flag;
		public Horse(int line, int idx, boolean flag) {
			super();
			this.line = line;
			this.idx = idx;
			this.flag = flag;
		}
		
	}
}
