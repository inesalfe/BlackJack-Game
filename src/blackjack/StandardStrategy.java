package blackjack;

public class StandardStrategy extends BettingStrategy {
	
	int next_bet;
	
	public StandardStrategy(int min_bet_in, int max_bet_in) {
		super(min_bet_in, max_bet_in);
		next_bet = min_bet_in;
	}

	public int getNextBet() {
		return next_bet;
	}
	
	public void updateBet(int result) {
		if(result == 1)
			next_bet = (curr_bet + min_bet <= max_bet) ? curr_bet + min_bet : max_bet;
		else if (result == -1)
			next_bet = (curr_bet - min_bet >= min_bet) ? curr_bet - min_bet : min_bet;
		else
			next_bet = curr_bet;
		curr_bet = next_bet;
	}

	@Override
	public void updateCount(Card c) {	
		return;
	}
		
}