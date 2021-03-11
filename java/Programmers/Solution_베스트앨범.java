package Feb_03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Solution_베스트앨범 {
	public static void main(String[] args) {
		String[] genres= {"A","B","C","D"};
		int[] plays= {1,2,3,1};
		int [] answer=solution(genres, plays);
		for(int i=0; i<answer.length; i++)
			System.out.println(answer[i]);
		
	}
	private static class Music{
		String genre; int play, idx;
		public Music(String genre, int play, int idx) {
			// TODO Auto-generated constructor stub
			this.genre=genre; this.play=play; this.idx=idx;
		}
	}
	public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        ///////////////////////////////////////////
        ArrayList<Integer> arr=new ArrayList<>();
        //모든 장르는 재생된 횟수가 다르기 때문에, play를 키 값으로 정하자
        HashMap<String, Integer> map=new HashMap<>();
        //map에서 장르를 기준으로 제일 많은 것들
        for(int i=0; i<genres.length; i++) {
        	map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
        }
        //map을 내림차순 정렬하기
        List<String> list=new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<String>() {
        	@Override
        	public int compare(String o1, String o2) {
        		return map.get(o2)-map.get(o1);
        	}
		});
        
        Music m[]=new Music[genres.length];
        for(int i=0; i<genres.length; i++) {
        	m[i]=new Music(genres[i], plays[i], i);
        }
        
        for(int j=0; j<list.size(); j++) {
        	List<Music> tmplist=new ArrayList<>();
	        for(int i=0; i<m.length; i++) {
	        	if(m[i].genre.equals(list.get(j))) {
	        		tmplist.add(m[i]);
	        	}
	        }
	        
	        Collections.sort(tmplist, new Comparator<Music>() {
	        	@Override
	        	public int compare(Music o1, Music o2) {
	        		if(o2.play==o1.play)
	        			return o1.idx-o2.idx;
	        		return o2.play-o1.play;
	        	}
	        });
	        //tmplist는 play횟수에 맞게 정렬된 상태
	        
	        for(int i=0; i<tmplist.size() && i<2; i++)
	        	arr.add(tmplist.get(i).idx);
        }
        answer=new int[arr.size()];
        for(int i=0; i<arr.size(); i++)
        	answer[i]=arr.get(i);
        ///////////////////////////////////////////
        return answer;
    }

}
