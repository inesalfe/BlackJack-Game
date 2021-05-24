package cardCounting;

import blackjack.Card;

/** Class relative to the standard strategy
 * 
 * @param next_bet Used to store the updates on the next bet
 *
 */
public class StandardStrategy extends BettingStrategy {
	
	int next_bet;
	
	/** Sets the next bet to
	 * 
	 * @param min_bet_in
	 * @param max_bet_in
	 */
	public StandardStrategy(int min_bet_in, int max_bet_in) {
		super(min_bet_in, max_bet_in);
		next_bet = min_bet_in;
	}

	/** Gets the next value for the bet
	 */
	public int getNextBet() {
		return next_bet;
	}
	
	/** Updates the value for the bet
	 * 
	 * @param result 
	 */
	public void updateBet(int result) {
		if(result == 1)
			next_bet = (curr_bet + min_bet <= max_bet) ? curr_bet + min_bet : max_bet;
		else if (result == -1)
			next_bet = (curr_bet - min_bet >= min_bet) ? curr_bet - min_bet : min_bet;
		else
			next_bet = curr_bet;
		curr_bet = next_bet;
	}

	/** Updates auxiliary variable count
	 * 
	 * @param c Regular card
	 * 
	 */
	@Override
	public void updateCount(Card c) {	
		return;
	}
		
}