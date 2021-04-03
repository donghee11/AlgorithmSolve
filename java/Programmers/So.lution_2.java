package April_01;

class Solution_2 {
    public int solution(int[] gift_cards, int[] wants) {
        int answer = 0;
        //갯수 세기
        int[] giftcnt=new int[100000]; int[] wantcnt=new int[100000];
        for(int i=0; i<gift_cards.length; i++) {
        	giftcnt[gift_cards[i]-1]++;
        	wantcnt[wants[i]-1]++;
        }
        
        for(int i=0; i<100000; i++) {
        	if(giftcnt[i]==0 && wantcnt[i]==0) continue;
        	if(giftcnt[i]<wantcnt[i]) {
        		answer+=(wantcnt[i]-giftcnt[i]);
        	}
        }
        return answer;
    }
}
