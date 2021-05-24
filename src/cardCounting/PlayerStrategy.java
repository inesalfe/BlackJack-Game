package cardCounting;

import blackjack.Hand;
import blackjack.PlayerHand;

/** Class which refers to the player's advisable strategy
 * 
 * @param max_bet Maximum value for the bet.
 * @param DDmin Minimum possible value for a Double down.
 * @param DDmax Maximum possible value for a Double down.
 * @param canSurrender Flag which indicates if it is possible to surrender.
 * @param canDouble Flag which indicates if it possible to double the bet
 * @param canSplit Flag which indicates if it possible to split
 *
 */
public abstract class PlayerStrategy {

	protected int max_bet;
	protected int DDmin;
	protected int DDmax;
	protected boolean canSurrender;
	protected boolean canDouble;
	protected boolean canSplit;
	protected boolean canInsure;
	
	/** Calculates the best strategy
	 * 
	 * @param max_bet_in Maximum value for the bet
	 * @param DDmin_in Minimum possible value for a Double down.
	 * @param DDmax_in Maximum possible value for a Double down.
	 */
	public PlayerStrategy(int max_bet_in, int DDmin_in, int DDmax_in) {
		max_bet = max_bet_in;
		DDmin = DDmin_in;
		DDmax = DDmax_in;
	}
	
	/** Calculates the next play based on the defined strategy
	 * 
	 * @param nHands Number of hands.
	 * @param p_hand p_hand Player's hand.
	 * @param d_Hand d_hand Dealer's hand.
	 * @param bet Value of the bet.
	 * 
	 */
	public abstract String getNextPlay(int nHands, PlayerHand p_hand, Hand d_Hand, int bet);
	
//	public static void main(String args[]){
//		GameMode inter = new Interative();
//		System.out.println(inter.getCommand());
//		GameMode debug = new Debug("cmd-file.txt");
//		while(true)
//			System.out.println(debug.getCommand());
//	}
}