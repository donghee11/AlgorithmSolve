package March_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1일, 1달 , 3달, 1년
public class Solution_1952 {
	private static int arr[];
	private static int m[], ans; //달별로 이용할 횟수
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			arr=new int[4];
			m=new int[12];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++)
				arr[i]=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<12; i++)
				m[i]=Integer.parseInt(st.nextToken());
			ans=arr[3]; //일년 이용권을 사용하는 경우로 초기화 해놓기,
			recur(0,0);
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}//end of main
	
	//완탐으로 한다면?
	private static void recur(int cnt, int money) {
		if(cnt>=12) {
			ans=Math.min(money, ans);
			return;
		}
		
		//해당 cnt 달에서 취할 행동을 재귀로 구성
		recur(cnt+1, money+m[cnt]*arr[0]);
		recur(cnt+1, money+arr[1]);
		recur(cnt+3, money+arr[2]);
	}
}
