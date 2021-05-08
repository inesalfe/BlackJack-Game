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
	
//	public void reset() {
//		super.reset();
//		bet = 0;
//		isOpening = false;
//		isSplit = false;
//		isDoubleD = false;
//		isPair = false;
//		isSurrender = false;
//	}
	
	public int getBet() {
		return bet;
	}
	
	public void doubleBet() {
		bet *= 2;
	}
	
	public boolean isSurrender() {
		return isSurrender;
	}
	
	public void setIsSurrender() {
		isSurrender = true;
	}
	
	public boolean isOpening() {
		return isOpening;
	}
	
	public void setIsOpening(boolean bool) {
		isOpening = bool;
	}
	
	public boolean isPair() {
		return isPair;	
	}
	
	public boolean isDouble() {
		return isDoubleD;
	}
	
	public void setIsDoubleD() {
		isDoubleD = true;
	}
	
	public boolean isSplit() {
		return isSplit;
	}
	
	public void setBet(int bet_in) {
		bet = bet_in;
	}
	
	public void addCard(Card card) {
		super.addCard(card);
		card.setIsUp(true);
		if (nCards == 2) {
			if (cards.get(0).equals(cards.get(1)))
				isPair = true;			
		}
		else
			isPair = false;
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
		Card c2 = new Card("A", 'H');
		Card c3 = new Card("5", 'C');
		Card c4 = new Card("6", 'S');
		Card c5 = new Card("10", 'S');
		Card c6 = new Card("K", 'D');
		Card c7 = new Card("2", 'D');
		PlayerHand h1 = new PlayerHand(10, true, false);
		PlayerHand h2 = new PlayerHand(10, true, false);
		PlayerHand h3 = new PlayerHand(10, true, false);
		h1.addCard(c1);
		h1.addCard(c2);
		h2.addCard(c3);
		h2.addCard(c4);
		h3.addCard(c5);
		h3.addCard(c6);
		//h3.addCard(c7);
		System.out.println(h1.isPair());
		System.out.println(h2.isPair());
		System.out.println(h3.isPair());
	}
	
}
