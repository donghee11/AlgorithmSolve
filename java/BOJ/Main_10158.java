package Feb_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 개미 
 * 가로 w, 세로 h 왼쪽아래:(0,0), 오른쪽 위(w,h)
 */
public class Main_10158 {
	private static int w, h, t;
	private static int p,q; //현재좌표
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		st=new StringTokenizer(br.readLine());
		w=Integer.parseInt(st.nextToken());
		h=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		p=Integer.parseInt(st.nextToken());
		q=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(br.readLine()); //end of input
		int x=(p+t)%(2*w);
		int y=(q+t)%(2*h);
		if(x>=w) p=(2*w)-x;
		else p=x;
		if(y>=h) q=(2*h)-y;
		else q=y;
		
		sb.append(p).append(" ").append(q);
		System.out.println(sb.toString());
	}
	
}
