package blackjack;

public class PlayerHand extends Hand {
	
	private int bet;
	private boolean isOpening;
	private boolean isPair;
	private boolean isDoubleD;
	private boolean isSplit;
	private boolean isSurrender;
	
	public PlayerHand(int bet_in, boolean opening, boolean split) {
		super();
		bet = bet_in;
		isOpening = opening;
		isSplit = split;
		isDoubleD = false;
		isPair = false;
		isSurrender = false;
	}
	
	public int getBet() {
		return bet;
	}
	
	public void doubleBet() {
		bet *= 2;
	}
	
	public boolean getIsSurrender() {
		return isSurrender;
	}
	
	public void setIsSurrender(boolean bool) {
		isSurrender = bool;
	}
	
	public boolean getIsOpening() {
		return isOpening;
	}
	
	public void setIsOpening(boolean bool) {
		isOpening = bool;
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
	
	public void addCard(Card card) {
		super.addCard(card);
		card.setIsUp(true);
	}
	
	@Override
	public String toString() {
		String out = new String();
		for (int i = 0; i < cards.size(); i++) {
			out += cards.get(i).toString();
			out += " ";
		}
		out += "(" + value + ")";
		return out;
	}

	public static void main(String args[]){
		Card c1 = new Card("A", 'D');
		Card c3 = new Card("5", 'C');
		Card c2 = new Card("A", 'H');
		Card c4 = new Card("6", 'S');
		PlayerHand h1 = new PlayerHand(10, true, false);
		h1.addCard(c1);
		h1.addCard(c2);
		h1.addCard(c3);
		System.out.println(h1);
		h1.addCard(c4);
		System.out.println(h1);
	}
	
}
