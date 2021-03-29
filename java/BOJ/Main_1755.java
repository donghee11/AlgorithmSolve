import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1755 {
	private static class Pair implements Comparable<Pair>{
		String a, b; //a는 10의자리수에 해당하는 알파벳, b는 1의 자리수에 해당하는 알파벳
		int num; //원래 숫자의 정보를 가지고 있어야 마지막에 출력가능,
		public Pair(String a, String b, int num) {
			super();
			this.a = a;
			this.b = b;
			this.num=num;
		}

		@Override
		public int compareTo(Pair o) { //10의자리수가 같다면, 1의 자리수를 비교
			if((this.a).equals(o.a)) return (this.b).compareTo(o.b);
			return (this.a).compareTo(o.a);
		}	
	}
	private static int M, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		ArrayList<Pair>list=new ArrayList<>();
		for(int i=M; i<=N; i++) {
			int num=i; //num을 쪼개기
			int a=num/10; //첫째자리
			if(a==0) {//한자리수라면
				list.add(new Pair(changeAlphabet(num%10),"a",num));
			}else { //두자리수
				list.add(new Pair(changeAlphabet(num/10),changeAlphabet(num%10),num));
			}
		}
		//sort
		Collections.sort(list);
		//10개씩 출력하기
		StringBuilder sb=new StringBuilder();
		for(int i=0, len=0; i<list.size(); i++) { //출력부분
			sb.append(list.get(i).num).append(" ");
			len++;
			//len이 10개가 꽉차면 줄바꿈하기
			if(len==10) {
				sb.append("\n"); len=0; //len초기화
			}
		}
		System.out.println(sb.toString());
		
	}
	private static String changeAlphabet(int a) { //a를 입력받을 때, 
		switch(a) {
		case 0:
			return "zero";
		case 1:
			return "one";
		case 2:
			return "two";
		case 3:
			return "three";
		case 4:
			return "four";
		case 5:
			return "five";
		case 6:
			return "six";
		case 7:
			return "seven";
		case 8:
			return "eight";
		case 9:
			return "nine";
		default: //한자리 일 때 생길수 있는 예외, 한자리일때 뒤에 -1을 보내서 알파벳이 제일 빠르게 설정해준다
			return "a";
		}
	}
}
