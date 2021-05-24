package blackjack;

/** Class relative to the statistics regarding the blackjack.
 * @param handsPlayed Record of played hands
 * @param blackjacks Tracks blackjacks
*/
public class Stats {
	
	protected int handsPlayed;
	protected int blackjacks;

	/** Initializes the statistics.
	*/
	public Stats() {
		handsPlayed = 0;
		blackjacks = 0;
	}
	
	/** Calculates the ratio of blackjacks per hand
	 * @return blackjacks/handsPlayed Ratio
	*/
	public double getBJavg() {
		return (double)blackjacks/handsPlayed;
	}
	
	/** Calculates the ratio of blackjacks per hand
	*/
	public void incHandsPlayed() {
		++handsPlayed;
	}
	
	/** Updates the number of Blackjacks
	*/
	public void incBlackjacks() {
		++blackjacks;
	}
}
