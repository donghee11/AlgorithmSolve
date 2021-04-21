package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//1~N, 
public class Main_2458 {
	private static int N, M, answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			boolean first[][]=new boolean[N+1][N+1];
			boolean second[][]=new boolean[N+1][N+1];
			answer=0;
			
			for(int i=0; i<M; i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				first[a][b]=true;
				second[b][a]=true;
			}//end of input
			//모든 학생에 대해서 조사하기,
			//자기보다 키 큰 학생 + 키 작은 학생 == N-1 이면 만족,
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(first[i][k] && first[k][j])
							first[i][j]=true;
					}
				}
			}
			
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(second[i][k] && second[k][j])
							second[i][j]=true;
					}
				}
			}
			for(int i=1; i<=N; i++) {
				boolean flag=true;
				for(int j=1; j<=N; j++) {
					if(i==j) continue;
					if(!first[i][j] && !second[i][j]) flag=false;
				}
				if(flag) answer++;
			}
			System.out.println(answer);
	}


}
