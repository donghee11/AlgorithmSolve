package March_01;

import java.util.Scanner;


public class Main_13458 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt(); 
		int arr[]=new int[N];
		for(int i=0; i<N; i++) {
			arr[i]=sc.nextInt();
		}
		int B=sc.nextInt();
		int C=sc.nextInt();
		long ans=0;
		for(int i=0; i<N; i++) {
			if(arr[i]<=B) {
				ans++; 
				continue;
			} //총감독관이 관리할 수 있는 학생수보다 적거나 같으면, 총감독관 한명으로 가능
			//B보다 큰경우는 , 무조건 총감독관은 한명 배치하고 남은 학생들 중 
			ans+=1;
			arr[i]-=B;
			if(arr[i]%C==0) ans+=(arr[i]/C);
			else ans+=(arr[i]/C+1);
		}
		System.out.println(ans);
	}
}
