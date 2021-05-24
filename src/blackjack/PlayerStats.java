package blackjack;


/** Class destined to saving the player statics regarding several games.
 * 
 * @param wins Number of wins. 
 * @param losses Number of losses.
 * @param pushes Number of pushes. 
 * @param initBalance Initial balance.
 * 
*/
public class PlayerStats extends Stats {
	private int wins;
	private int losses;
	private int pushes;
	private int initBalance;

	/** Assigns values to the stats
	 * 
	 * @param initbalance Stores the value for the initial balance of the player.
	 * 
	*/
	public PlayerStats(int init_balance) {
		super();
		initBalance = init_balance;
		wins = 0;
		losses = 0;
		pushes = 0;
	}
	
	/** Class destined to saving the player statics regarding several games.
	 * 
	 * @param r Used to update the status regarding the records.
	 * 
	*/
	public void incWLP(int r) {
		if(r == 1) ++wins;
		if(r == -1) ++losses;
		if(r == 0) ++pushes;
	}

	/** Tracks the record of the player.
	 * 
	 * @param r Used to update the status regarding the records.
	 * 
	*/
	public double getWLPavg(int r) {
		if(r == 1) return (double)wins/handsPlayed;
		if(r == -1) return (double)losses/handsPlayed;
		if(r == 0) return (double)pushes/handsPlayed;
		return 0;
	}
	
	/** Calculates the percentage gain based on the initial balance and current balance.
	 * 
	 * @param cur_balance Current balance which indicates the available money.
	 * 
	*/
	public double percentageOfGain(double cur_balance) {
		return cur_balance/initBalance;
	}

}