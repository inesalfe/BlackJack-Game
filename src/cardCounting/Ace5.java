package cardCounting;

import blackjack.Card;

/** Creates hands of cards.
 * 
 * @param min_bet_in Represents the minimum value for the bet.
 * @param max_bet_in Represents the maximum value for the bet.
 * @param count updates the counting based on the value of cards.
 * 
*/
public class Ace5 extends BettingStrategy {
	
	/** Initializes the Ace5 card counting strategy.
	 * 
	 * @param min_bet_in Represents the minimum value for the bet.
	 * @param max_bet_in Represents the maximum value for the bet.
	 * 
	*/
	
	public Ace5(int min_bet_in, int max_bet_in) {
		super(min_bet_in, max_bet_in);
	}
		
	/** Updates the counting variable based on a card
	 * 
	 * @param card Represents the present card.
	 * 
	*/
	public void updateCount(Card card) {
		if (card.getValue().equals("A"))
			count--;
		else if (card.getValue().equals("5"))
			count++;
	}
	
	/** Calculates the next bet that should be made based on the count.
	 * 
	 * @param curr_bet Current bet in place.
	 * @return min_bet Minimum bet if the count does not obey the condition.
	 * @return curr_bet Updates the current bet in place.
	 *
	*/
	public int getNextBet() {
		if (count >= 2)
			return (2*curr_bet > max_bet) ? max_bet : 2*curr_bet;
		else
			return min_bet;
	}
	
}
