package April_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//게리맨더링
public class Main_17779 {
	private static int map[][],C_map[][];
	private static int N, answer,sum;
	private static int p[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N=Integer.parseInt(br.readLine());
		answer=Integer.MAX_VALUE;
		map=new int[N+1][N+1];
		C_map=new int[N+1][N+1];
		p=new int[6]; //1~5번 선거구 까지 사람 수
		////0행, 0열 사용 XXX
		
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				sum+=map[i][j];
			}
		}
		
		//인구가 가장 많은 선거구 - 가장 적은 선거구의 최솟값
		//(r,c) 정하고 --> 그에 따른 x,y정하기
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=N; c++) {
				//기준점이 (r,c)
				//d1, d2정하기
				for(int d2=1; d2<=N-c; d2++) {
					for(int d1=1; (d1<=N-r-d2) && (d1<=c-1); d1++) {
						calculate(r,c,d1,d2);
					}
				}
			}
		}
		System.out.println(answer);
	}
	private static void calculate(int r, int c, int d1, int d2) {
		//기준점을가지고 1,2,3,4번 선거구 계산하기
		init();
		//5번 선거구부터 계산
		//(r,c)부터 
		for(int i=0; i<=d1; i++) {
				C_map[r+i][c-i]=5;

		}
		for(int i=0; i<=d2; i++) {
				C_map[r+i][c+i]=5;
		}
		for(int i=0; i<=d2; i++) {
				C_map[r+d1+i][c-d1+i]=5;
		}
		for(int i=0; i<=d1; i++) {
				C_map[r+d2+i][c+d2-i]=5;
		}
		
		//**1번 선거구
		for(int i=1; i<r+d1; i++) {
			for(int j=1; j<=c; j++) {
				if(C_map[i][j]==5) break;
				p[1]+=map[i][j];
				C_map[i][j]=1;
			}
		}
		
		//**2번 선거구
		for(int i=1; i<=r+d2; i++) {
			for(int j=N; j>=c+1; j--) {
				if(C_map[i][j]==5) break;
				p[2]+=map[i][j];
				C_map[i][j]=2;
			}
		}
		//**3번
		for(int i=r+d1; i<=N; i++)
			for(int j=1; j<c-d1+d2; j++) {
				if(C_map[i][j]==5) break;
				p[3]+=map[i][j];
				C_map[i][j]=3;
			}
		
		for(int i=r+d2+1; i<=N; i++) {
			for(int j=N; j>=c-d1+d2; j--) {
				if(C_map[i][j]==5) break;
				p[4]+=map[i][j];
				C_map[i][j]=4;
			}
		
		}
		
		p[5]=(sum-p[1]-p[2]-p[3]-p[4]);
//		System.out.println(p[1]+" "+p[2]+" "+p[3]+" "+p[4]+" "+p[5]);
		//계산끝났으면 answer구하기
		int min=Integer.MAX_VALUE;
		int max=0;
		for(int i=1; i<=5; i++) {
			min=Math.min(min, p[i]);
			max=Math.max(p[i], max);
		}
//		print();
		answer=Math.min(max-min, answer);
	}
	private static void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(C_map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private static void init() {
		for(int i=1; i<=5; i++)
			p[i]=0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++)
				C_map[i][j]=0;
		}
	}
	
	
	
	
	
	
	
	
}
