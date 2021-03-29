import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2629 {
	private static int N, M; //추의 갯수, 확인하고자 하는 구슬의 무게
	private static int arr[]; //추의 무게들 담는 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine()); //추의 갯수
		StringTokenizer st=new StringTokenizer(br.readLine());
		arr=new int[N];
		int max_num=0; //계산할 수 있는 구슬의 무게의 최대치 = 추의 무게들의 합
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
			max_num+=arr[i];
		}
		boolean flag[]=new boolean[max_num+1]; //인덱스에 해당하는 숫자를 만들수있으면 true로 바꿀 배열
		for(int i=0; i<N; i++) {
			ArrayList<Integer> list=new ArrayList<>(); //새롭게 무게를 잴 수 있는 애들을 update할 인덱스배열
			list.add(arr[i]);
			for(int j=1; j<=max_num-arr[i]; j++) {
				if(flag[j]) { //이전까지 해당 무게를 재는게 가능했다면,
					//j에 새롭게 넣을 arr[i]무게를 더하거나 빼는 값이 모두 가능,
					if(!flag[j+arr[i]]) list.add(j+arr[i]);
					if(!flag[Math.abs(j-arr[i])]) list.add(Math.abs(j-arr[i]));
				}
			}
			//list 사이즈가 0 보다 크면, 해당 flag값을 true로 바꿔주기
			for(int j=0; j<list.size(); j++)
				flag[list.get(j)]=true;
		}
	
		StringBuilder sb=new StringBuilder();
		M=Integer.parseInt(br.readLine()); //확인하고자 하는 구슬 갯수
		st=new StringTokenizer(br.readLine());
		for(int m=0; m<M; m++) {
			int w=Integer.parseInt(st.nextToken());
			if(w>max_num) {
				sb.append("N").append(" ");
				continue;
			}
			if(flag[w]) sb.append("Y").append(" ");
			else sb.append("N").append(" ");
		}
		System.out.println(sb.toString());
		
	}
}


