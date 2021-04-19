package April_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_2477 {
	private static int N, M, K, A, B;
	private static Place_A p1[];
	private static Place_B p2[];
	private static Person person[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			B=Integer.parseInt(st.nextToken());
			p1=new Place_A[N+1];
			p2=new Place_B[M+1];
			
			st=new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				p1[i]=new Place_A(Integer.parseInt(st.nextToken()), false);
			}
			
			st=new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				p2[i]=new Place_B(Integer.parseInt(st.nextToken()), false);
			}
			person=new Person[K+1];
			st=new StringTokenizer(br.readLine());
			for(int i=1; i<=K; i++) {
				person[i]=new Person(i,Integer.parseInt(st.nextToken()),0,0);
			}//end of input
			moveToPlaceA();
			moveToPlaceB();
			sb.append("#").append(tc).append(" ").append(Solution()).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static int Solution() {
		int answer=0;
		for(int k=1; k<=K; k++) {
			if(person[k].a==A && person[k].b==B) {
				answer+=k;
			}
		}
		answer=answer==0?-1:answer;
		return answer;
	}
	private static PriorityQueue<Person> q;
	private static PriorityQueue<Person> waiting;
	private static void moveToPlaceA() {
		int now=N; //현재 가능한 창고의 수!
		int time=0;
		int nowCnt=1; //처리해야할 사람의 인덱스
		q=new PriorityQueue<>();
		waiting=new PriorityQueue<>();
		while(true) {
			while(!q.isEmpty()) {
				Person p=q.poll();
				if(p.endtime==time) {
					p1[p.a].flag=false;
					now++;	
				}else {
					//달라! 다른순간 그냥 다시 넣고 브레이크
					q.add(p);
					break;
				}
			}
			while(now>0 && nowCnt<=K) {
				if(time>=person[nowCnt].time) { //창구가 남아있고, 손님이온이후면,
					
					int x=checkPlaceA(); //x창구로 보내야해
					now--;
					p1[x].flag=true; //사람 있음 표시
					person[nowCnt].a=x;
					person[nowCnt].endtime=time+p1[x].time; //이때 얘가 끝나
					q.add(person[nowCnt]);
					waiting.add(person[nowCnt]);
					nowCnt+=1;
				}else break;
			}
			time++;
			
			if(nowCnt==K+1) return;
		}
		
	}
	
	private static void moveToPlaceB() {
		//여기에 waitingQueue에 있는 애들 처리해준다.

		Person p=waiting.poll();
		int idx=p.idx;
		int time=p.endtime;
		int nowCnt=1; //처리한 사람 수 
		int now=M;
		p2[1].flag=true;
		person[idx].endtime2=time+p2[1].time;
		person[idx].b=1;
		now--;
		q=new PriorityQueue<>();
		Queue<int[]>tmp=new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0]) return o1[1]-o2[1];
				return o1[0]-o2[0];
			}
		});
		tmp.add(new int[] {person[idx].endtime2, person[idx].a, idx});
		
		while(true) {
			
			while(!tmp.isEmpty()) {
				int[]ttt=tmp.poll();
				if(ttt[0]==time) {
					p2[person[ttt[2]].b].flag=false;
					now++;
					
				}else {
					tmp.add(new int[] {ttt[0],ttt[1],ttt[2]});
					break;
				}
			}
			
			
			while(now>0 && !waiting.isEmpty()) {
				Person pp=waiting.poll();
				if(time>=pp.endtime) { //창구가 남아있고, 손님이온이후면,
					//비어있는 창구중 제일 빠른놈
					idx=pp.idx;
					int x=checkPlaceB(); //x창구로 보내야해
					now--;
					p2[x].flag=true; //사람 있음 표시
					person[idx].b=x;
					person[idx].endtime2=time+p2[x].time; //이때 얘가 끝나
					tmp.add(new int[] {person[idx].endtime2,person[idx].a, idx});
					nowCnt+=1;
				}else {
					waiting.add(pp); //아니면 다시 너허야함
					break;
				}
			}
			time++;
			if(nowCnt==K) return;
			
			
		}
	}
	
	private static int checkPlaceB() {
		for(int i=1; i<=M; i++) {
			if(!p2[i].flag) return i;
		}
		return -1; //없어요
	}

	private static int checkPlaceA() {
		for(int i=1; i<=N; i++) {
			if(!p1[i].flag) return i;
		}
		return -1; 
	}



	private static class Person implements Comparable<Person>{
		int idx;
		int time, a,b, endtime; //들어온 시간, 이용한 접수창고와 정비창구, 접수시간 나오는 시간
		int endtime2;
		public Person(int idx,int time, int a, int b) {
			super();
			this.idx=idx;
			this.time = time;
			this.a = a;
			this.b = b;
			this.endtime=-1;
			this.endtime2=-1;
		}
		@Override
		public int compareTo(Person o) {
			//이사람의 도착시간을 순서대로,
			//도착시간이 같으면, 그 사람의 접수 창구번호가 작은 순서대로
			if(this.endtime==o.endtime)
				return this.a-o.a; //같으면, 접수창구번호
			return this.endtime-o.endtime;
		}
		
	}
	
	private static class Place_A{
		//접수창고
		int time;
		boolean flag; //true면 사람이 존재함
		public Place_A(int time, boolean flag) {
			super();
			this.time = time;
			this.flag = flag;
		}
		
	}
	
	
	private static class Place_B{
		//정비창고
		int time;
		boolean flag;
		public Place_B(int time, boolean flag) {
			super();
			this.time = time;
			this.flag = flag;
		}
		
	}
}
