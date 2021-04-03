package April_01;

class Solution_3 {
	static int ans;
	private static int count;
	private static int need;
	private static boolean visit[];
    public int solution(int[][] needs, int r) {
        int answer = 0;
        //로봇 한대는 부품 한종류만 처리할수 있고, 
        //종류에 따라 부품이 다르다
        //r대로 최대한 다양한 완제품
        count=needs.length; //완제품 수
        need=needs[0].length; //부품 수
        visit=new boolean[need];
        comb(0,r,0,needs);
        
        
        answer=ans;
        return answer;
    }
    
    //comb로 모든 조합 만들어보기
    private static void comb(int idx, int r,int cnt, int [][]needs) {
    	if(cnt==r) {
    		//모든 부품을 골랐으면 로봇을 만들기
    		//visit이 true인 애들 중에서 만들수 있는 완제품 살펴보기
    		//모든 완제품을 돌기?
    		int tmp=0;
    		for(int i=0; i<count; i++) {
    			boolean flag=true;
    			for(int j=0; j<need; j++) {
    				if(needs[i][j]==0) continue; //필요없는 부품이니까
    				//필요있는 부품이라면
    				if(!visit[j]) {
    					flag=false;
    					break; //필요한데 선택 안했으면 다음 부품
    				}
    				//여기까지왔으면 될 수 있다
    			}
    			if(flag) tmp++;
    		}
    		ans=Math.max(ans, tmp);
    	}
    	
    	for(int i=idx; i<need; i++) {
    		visit[i]=true;
    		comb(i+1, r, cnt+1, needs);
    		visit[i]=false;
    	}
    	
    	
    }
}
