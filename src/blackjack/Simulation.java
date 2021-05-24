package blackjack;

import cardCounting.BettingStrategy;
import cardCounting.PlayerStrategy;

/** Class relative to the simulation mode.
 * 
 * @param sNumber number of shuffles until the end of the simulation.
 * @param currSNumber Current number of shuffles in the present simulation
 * @param play_strat Selection of playing strategy
 * @param bet_strat Selection of betting strategy
 *
 */
public class Simulation implements GameMode {

	private final int sNumber;
	private int currSNumber;
	PlayerStrategy play_strat;
	BettingStrategy bet_strat;
	
	/** Initializes the parameters for the simulation
	 * 
	 * @param sNumber_in Stores the number of shuffles until the end of the simulation.
	 * @param play_s Stores the selection of the playing strategy
	 * @param bet_s Stores the selection of the betting strategy
	 */
	public Simulation (int sNumber_in, PlayerStrategy play_s, BettingStrategy bet_s) {
		sNumber = sNumber_in;
		currSNumber = -1;
		play_strat = play_s;
		bet_strat = bet_s;
	}
	
	/** Gets Playable command
	 * 
	 * @param p_hand Player's hand
	 * @param d_hand Dealer's hand
	 * @param bet Value of the bet
	 * @return <q> Quit
	 * @return play_start.getNextPlay(p_hand,d_hand,bet) Generates command for strategy.
	 * 
	 */
	@Override
	public String getPlayCommand(int nHands, PlayerHand p_hand, Hand d_hand, int bet) {
		String cmd;
		if(currSNumber > sNumber)
			 cmd = "q";
		else
			cmd = play_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		System.out.println("# " + cmd);
		return cmd;
	}

	/** Gets next bet command
	 * 
	 * @param <q> Used to quit 
	 * @param <b> Used to bet
	 * @param bet_start.getNextBet() Gets command for next bet
	 */
	@Override
	public String getBetCommand() {
		String cmd;
		if(currSNumber > sNumber)
			 cmd = "q";
		else
			cmd = "b " + bet_strat.getNextBet();
		System.out.println("# " + cmd);
		return cmd;
	}
	
	/** Increments the current sNumber
	 */
	public void incCurrSNumber() {
		++currSNumber;
	}
	
	/** Gets the play command
	 */
	@Override
	public String getPlayCommand() {
		return null;
	}
	
//	public static void main(String args[]){
//		GameMode inter = new Simulation(1, "oi");
//		System.out.println(inter.getCommand());
//	}
	
}