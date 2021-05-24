package cardCounting;

import blackjack.Card;

/** Class which refers to the best possible way to calculate the next bet.
 * 
 * @param count Auxiliary counter.
 * @param curr_bet Current value of the bet.
 * @param min_bet_in Minimum value for the bet.
 * @param max_bet_in Maximum value for the bet.
 */
public abstract class BettingStrategy {

	protected int count;
	protected int curr_bet;
	protected int min_bet;
	protected int max_bet;
	
	/** Initializes the bet parameters.
	 * 
	 * @param min_bet_in Minimum value for the bet.
	 * @param max_bet_in Maximum value for the bet.
	 * 
	 */
	public BettingStrategy(int min_bet_in, int max_bet_in) {
		curr_bet = min_bet_in;
		min_bet = min_bet_in;
		max_bet = max_bet_in;
		count = 0;
	}
	
	/** Sets the value for the bet.
	 * 
	 * @param bet_in Value of the bet.
	 * 
	 */
	public void setBet(int bet_in) {
		curr_bet = bet_in;
	}
	
	public abstract int getNextBet();
	
	public abstract void updateCount(Card card);
	
	/** Resets the auxiliar counter
	 */
	public void resetCount() {
		count = 0;
	}
	
//	public static void main(String args[]){
//		GameMode inter = new Interative();
//		System.out.println(inter.getCommand());
//		GameMode debug = new Debug("cmd-file.txt");
//		while(true)
//			System.out.println(debug.getCommand());
//	}

}