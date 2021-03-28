package March_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//톱니바퀴
public class Main_14891 {
	private static class Info{
		int idx, r;
		public Info(int idx, int r) {
			super();
			this.idx = idx;
			this.r = r;
		}
		
	}
	private static ArrayList<Info> list;
	private static int map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		map=new int[4][8];
		for(int i=0; i<4; i++) {
			String str=br.readLine();
			for(int j=0; j<8; j++)
				map[i][j]=str.charAt(j)-'0';
		}
		int T=Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken())-1;
			int r=Integer.parseInt(st.nextToken()); //n번 톱니바퀴를 r 방향으로 회전 시키기
			
			list=new ArrayList<>();
			list.add(new Info(n,r));
			//n번의 6번과 n-1의 2번의 극이 다른지 확인하고, n의 2번과 n+1의 6번이 같은지 확인하는 함수가 필요
			checkRotate(n,r);
			rotate();
			
			//list에 담긴애들 기준으로 실제로 돌리기
		}
		//다 돌리고 난 후에 점수매기기
		int result=0;
		for(int i=0; i<4; i++) {
			if(map[i][0]==1) result+=(int)Math.pow(2, i);
		}
		System.out.println(result);
	}
	private static void rotate() {
		for(int i=0; i<list.size(); i++) {
			int n=list.get(i).idx; int r=list.get(i).r;
			if(r==1) { //시계방향이라면 map[n]에 해당하는 애들을 모두 시계 방향으로회전시키자
				int temp=map[n][7];
				for(int j=7; j>0; j--) {
					map[n][j]=map[n][j-1];
				}
				map[n][0]=temp;
			}else { //반시계
				int temp=map[n][0];
				for(int j=0; j<7; j++) {
					map[n][j]=map[n][j+1];
				}
				map[n][7]=temp;
			}
		}
	}
	private static void checkRotate(int n, int rotate) {
		//n번을 기준으로 왼쪽에 있는 애들 check
		int idx=n-1;
		int r=(-1)*rotate;
		while(idx>=0) {
			//idx랑 idx+1를 비교한다
			if((map[idx][2] ^ map[idx+1][6]) == 0) //같은애라면 
				break;
			//다른애라면, 
			list.add(new Info(idx,r));
			idx--; r*=(-1);
		}
		//n번을 기준으로 오른쪽에 있는애들 check
		idx=n+1; r=(-1)*rotate;
		while(idx<4) {
			if((map[idx-1][2] ^ map[idx][6])==0) break;
			list.add(new Info(idx,r));
			idx++; r*=(-1);
		}
	}
}
