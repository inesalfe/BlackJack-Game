package blackjack;

public class Ace5 {

	private int count;
	private int min_bet;
	private int max_bet;
	
	public Ace5(int min_bet_in, int max_bet_in) {
		min_bet = min_bet_in;
		max_bet = max_bet_in;
		count = 0;
	}
		
	public void updateCount(Card card) {
		if (card.getValue().equals("A"))
			count--;
		else if (card.getValue().equals("5"))
			count++;
	}
	
	public int getNextBet(int curr_bet) {
		if (count >= 2)
			return 2*curr_bet > max_bet ? max_bet : 2*curr_bet;
		else
			return min_bet;
	}
	
}
