package blackjack;

public class Stats {
	
	protected int handsPlayed;
	protected int blackjacks;
		
	public Stats() {
		handsPlayed = 0;
		blackjacks = 0;
	}
	
	public double getBJavg() {
		return (double)blackjacks/handsPlayed;
	}
	
	public void incHandsPlayed() {
		++handsPlayed;
	}
	
	public void incBlackjacks() {
		++blackjacks;
	}
}
