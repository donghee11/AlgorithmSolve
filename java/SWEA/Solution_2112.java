package April_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_보호필름 {
	static int D, W, K;
	static int map[][];
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		for(int tc=1; tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			map=new int[D][W];
			for(int i=0; i<D; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==0) map[i][j]=2;
				}
			}
//			print(map);
			answer=Integer.MAX_VALUE;
			//k개 이상이 되어야하고, 변경횟수를 0개에서 최대 D개까지
			for(int k=0; k<=K; k++) {
				visit=new int[D];
				comb(k, 0, 0);
				//돌고왔더니 answer변해있으면 그만
				if(answer!=Integer.MAX_VALUE) break;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}//end of tc
		System.out.println(sb.toString());
	}
	//cnt는 현재까지 바꾼 횟수
	private static int visit[];
	private static void comb(int k, int cnt, int idx) {
		if(cnt==k) {
			//각 w 마다 확인하기
			int C_map[][]=new int[D][W];
			C_map=copyMap(C_map,map);
			if(checkBlock(C_map)) {
				answer=k;
				return;
			}
			return;
		}
		
		for(int i=idx; i<D; i++) {
			visit[i]=1;
			comb(k,cnt+1,i+1);
			visit[i]=0;
			
			visit[i]=2;
			comb(k,cnt+1,i+1);
			visit[i]=0;
		}
	}
	
	private static boolean checkBlock(int [][]map) {
		//visit에 true인 칸들을 모두 num으로 변경한다
//		int C_map[][]=new int[D][W];
//		C_map=copyMap(C_map,map);
		for(int i=0; i<D; i++)
			if(visit[i]!=0){
				//해당 i행에 대해서, 
				for(int j=0; j<W; j++)
					map[i][j]=visit[i];
			}
//		print(map);
		//check
		for(int i=0; i<W; i++) {
			//i열에 대해서
			int tmp=map[0][i];
			int cnt=1; int tmp_cnt=1;
			for(int j=1; j<D; j++) {
				if(map[j][i]!=tmp){
					//이전까지 cnt와 비교해서 큰 값 채택하고, cnt는 1로바꿈
					cnt=Math.max(cnt, tmp_cnt);
					//이미 cnt가 K 이상 만족하면 break해도됨
					if(cnt>=K) break;
					//다른순간에 남은 갯수가 k개가 안되면 이것도 return 시켜버리기
					if(D-K<j) return false;
					tmp_cnt=1;
					tmp=map[j][i];
				}else{
					//같은 순간
					tmp_cnt++;
				}
			}
			cnt=Math.max(cnt, tmp_cnt);
			//돌던중에 하나라도 K 만족안하면 그냥 false
			if(cnt<K) return false;
		}
		//여기까지 오면 만족한다
		return true;
	}
	
	private static void print(int[][]C_map) {
		for(int i=0; i<D; i++) {
			for(int j=0; j<W; j++) {
				System.out.print(C_map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	private static int[][] copyMap(int [][]C_map, int[][]map) {
		for(int i=0; i<D; i++) {
			for(int j=0; j<W; j++) {
				C_map[i][j]=map[i][j];
			}
		}
		return C_map;
	}
}
