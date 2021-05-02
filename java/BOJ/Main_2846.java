package May_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2846 {
	private static int N;
	private static int map[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		map=new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			map[i]=Integer.parseInt(st.nextToken());
		}
		
		int max_len=0;
		int low=map[0]; int high=map[0];
		for(int i=1; i<N; i++) {
			//그 전에꺼보다 큰경우,
			if(map[i-1]<map[i]) {
				high=map[i];
				if(i==N-1) {
					//마지막 인덱스면
					max_len=Math.max(max_len, high-low);
				}
			}else {
				//갱신해야하는 경우
				//map[i-1]
				max_len=Math.max(max_len, high-low); //전 꺼 계산하고, low , high 갱신
				high=low=map[i];
			}
		}
		//
		System.out.println(max_len);
	}
}
