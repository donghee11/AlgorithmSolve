package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//경비원
public class Main_2564 {
	static int N, M, X, answer; //가로 길이, 세로 길이
	static ArrayList<int[]> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		//상점의 갯수 
		list=new ArrayList<>();
		X=Integer.parseInt(br.readLine());
		for(int i=0; i<=X; i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			switch(a) {
			case 1:
				list.add(new int[] {b,M});
				break;
			case 2:
				list.add(new int[] {b,0});
				break;
			case 3:
				list.add(new int[] {0,M-b});
				break;
			case 4:
				list.add(new int[] {N,M-b});
				break;
			}
		}
		int r=list.get(X)[0]; int c=list.get(X)[1];
		for(int i=0; i<X; i++) {
			int nr=list.get(i)[0]; int nc=list.get(i)[1]; //상점의 위치
			//만약 반대편 행이라면
			if(Math.abs(nc-c)==M) {
				int tmp1=nr+r+M;
				int tmp2=(N-nr)+(N-r)+M;
				tmp1=tmp1>tmp2?tmp2:tmp1;
				answer+=tmp1;
			}else if(Math.abs(nr-r)==N) {
				//반대편 열이라면
				int tmp1=nc+c+N;
				int tmp2=(M-nc)+(M-c)+N;
				tmp1=tmp1>tmp2?tmp2:tmp1;
				answer+=tmp1;
			}else {
				answer+=Math.abs(nr-r)+Math.abs(nc-c);
			}
		}
		System.out.println(answer);
	}
}
