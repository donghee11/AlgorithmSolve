package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2 {
	private static int [][]map;
	private static int N;
	private static int answer1, answer2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		for(int tc=1; tc<=10; tc++) {
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}//end of input
			
			//모든 열을 이동시켜야한다,
			for(int i=0; i<N; i++) {
				move(i);
			}
//			print();
			//그 map의 상태에서,
			//map을 반시계 방향으로 회전시키기,
			answer1=getAnswer();
			rotate();
			for(int i=0;i<N; i++) {
				move(i);
			}
			answer2=getAnswer();
			sb.append("#").append(tc).append(" ").append(answer1).append(" ").append(answer2).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}
	private static int getAnswer() {
		int answer=0;
		for(int j=0; j<N; j++) {
			if(map[N-1][j]==1) answer++;
		}
		return answer;
	}
	private static void rotate() {
		int [][]C_map=new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				C_map[i][j]=map[N-1-j][i];
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j]=C_map[i][j];
			}
		}
	}
	private static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void move(int c) {
		//c열을 움직여야한다.
		//제일 첫번째 행부터 보겠지?
		if(map[0][c]==0) return; //충돌이 시작 조차 하지않아서,
		
		//여기온경우는 일단, map에 처음에 충돌량이 있다.
		int size=1; //블록 한개,
		double power=1;
		//순서대로 체크를한다.
		for(int i=1; i<N; i++) {
			if(map[i][c]==0) {
//				System.out.println(i+" "+size);
				map[i][c]=1; map[i-size][c]=0;
//				print();
				power*=1.9;
			}else if(map[i][c]==1) {
				//블록을 만났으면, 현재 power랑 이 블록의 크기랑 비교해야함,
				int x=check(i,c); //x개만큼 블록이있다.
				if(power<=x) return; //더이상 진행 xxxx 
				power+=x; size+=x;
				i+=x-1;
			}
		}
	}
	private static int check(int r, int c) {
		//(r,c)에서 연속된 1의 갯수
		int cnt=0;
		for(int i=r; i<N; i++) {
			if(map[i][c]==1) cnt++;
			else break;
		}
		return cnt;
	}
}


