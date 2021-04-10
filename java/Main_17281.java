package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//단계가 2개의 단계로 이루어짐
//순서를 정하고, --> 후에 n이닝 동안 계산진행
public class Main_17281 {
	private static int map[][];
	private static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringTokenizer st;
		map=new int[N][10];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1; j<=9; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		count=new int[10]; //1~9자리에 해당 타율 칠 사람 고르기
		count[4]=1; //1번타자는 무조건 4번 타수
		visit=new boolean[10];
		permu(0,1);
		System.out.println(answer);
	}
	private static int count[],answer;
	private static boolean visit[];
	private static boolean first, second,third;
	private static void permu(int cnt, int idx) {
		if(idx==4) {
			permu(cnt+1,idx+1);
			return;
		}
		if(cnt==9) {
			//다 골랐으면! 계산
			
			answer=Math.max(answer, calc());

			return;
		}
		
		//순열
		for(int i=2; i<=9; i++) {
			if(visit[i]) continue;
			visit[i]=true;
			count[idx]=i;
			permu(cnt+1, idx+1);
			visit[i]=false;
		}
	}
	private static int total;
	private static int out;
	private static int calc() {
		//카운트에 순서에 맞게
		//count[1]번부터 시작!
		int now=0;
		total=0;
		out = 0;
		int start=1;
		while(true) { //N이닝, 총 9*N번 시행
			if(now==N) return total;
			for(int i=start; i<=9; i++) {
				//map[now][visit[i]]값에 따라서,
				find(map[now][count[i]]);
				if(out==3) {
					//아웃 3개되면
					out=0; first=false; second=false; third=false;
					start=(i+1<=9)? i+1:1;
					now+=1;
					break;
				}
				///아웃도 아니고, 끝도아니면, 
				if(i==9) {
					start=1;
				}
			}
			
		}
	}
	
	private static void find(int r) {
		//위치찾기
		switch(r) {
		case 1: //안타
			if(third) total+=1;
			third=second;
			second=first;
			first=true;
			break;
		case 2: //이루수
			if(third) total+=1;
			if(second) total+=1;
			third=first; second=true;
			first=false;
			break;
		case 3:
			if(third) total+=1;
			if(second) total+=1;
			if(first) total+=1;
			third=true;
			second=false; first=false;
			break;
		case 4:
			if(third) total+=1;
			if(second) total+=1;
			if(first) total+=1;
			total+=1;
			first=false; second=false; third=false;
			break;
		case 0: 
			out+=1;
			break;
		default:
			break;
		}
	}
}
