package blackjack;

public class PlayerHand extends Hand {
	
	private int bet;
	private boolean isOpening;
	private boolean isPair;
	private boolean isDoubleD;
	private boolean isSplit;
	
	public PlayerHand(int bet_in, boolean opening, boolean split) {
		super();
		bet = bet_in;
		isOpening = opening;
		isSplit = split;
		isDoubleD = false;
		isPair = false;
	}
	
	public int getBet() {
		return bet;
	}
	
	public void doubleBet() {
		bet *= 2;
	}
	
	public boolean getIsOpening() {
		return isOpening;
		
	}
	
	public boolean getIsPair() {
		return isPair;
		
	}
	
	public boolean getIsDouble() {
		return isDoubleD;
		
	}
	
	public boolean getIsSplit() {
		return isSplit;
		
	}
	
	public void setBet(int bet_in) {
		bet = bet_in;
	}
	
}
