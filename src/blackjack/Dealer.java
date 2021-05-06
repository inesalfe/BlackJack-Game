package blackjack;

public class Dealer {
	
	private Shoe shoe;
	private Hand hand;
	
	private boolean isStanding;
	
	public Dealer(int nDecks_in) {
		shoe = new Shoe(nDecks_in);
		hand = new Hand();
		isStanding = false;
	}

	public Dealer(String shoeFile_in) {
		shoe = new Shoe(shoeFile_in);
		hand = new Hand();
	}

	public void addCard(Card card) {
		hand.addCard(card);
	}

	public boolean hasBlackjack() {
		return hand.checkBlackjack();
	}

	public int getHandValue() {
		return hand.getValue();
	}
	
	public void shuffle() {
		shoe.shuffle();
	}
	
	public void hit() {
		hand.addCard(dealCards());
	}
	
	public void stand() {
		isStanding = true;
	}
	
	public Card dealCards() {
		return shoe.getCard();
	}
	
	public void printDealersHand(){
		String out = new String();
		out += "dealer's hand ";
		out += hand.toString();
		System.out.println(out);	
	}
	
	public void setVisible() {
		hand.cards.get(1).setIsUp(true); // Isto está horrível, não vai ficar assims
	}
	
	public void clearHand() {
		hand.reset();
	}
	
}
