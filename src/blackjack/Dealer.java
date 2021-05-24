package blackjack;

/** Class relative to the Dealer.
 * 
 * @param shoe Stack of decks.
 * @param hand Represents the hand (of the dealer).
 * 
*/
public class Dealer {
	
	protected Shoe shoe;
	protected Hand hand;
	
//	private boolean isStanding;
	
	/** Creates a shoe for the dealer.
	 * 
	 * @param nDecks_in Number of decks that compose the shoe.
	 * 
	*/
	public Dealer(int nDecks_in) {
		shoe = new Shoe(nDecks_in);
		hand = new Hand();
//		isStanding = false;
	}
	
	/** Creates a shoe same t.
	 * 
	 * @param shoeFile_in Represents the shoe going in the game.
	 * 
	*/
	public Dealer(String shoeFile_in) {
		shoe = new Shoe(shoeFile_in);
		hand = new Hand();
	}

	/** Adds a card to the game.
	 * 
	 * @param card Represents a regular card.
	 * 
	*/
	public void addCard(Card card) {
		hand.addCard(card);
	}

//	public boolean hasBlackjack() {
//		return hand.checkBlackjack();
//	}
//
//	public int getHandValue() {
//		return hand.getValue();
//	}
	
	/** Shuffles the shoe.
	*/
	public void shuffle() {
		shoe.shuffle();
		shoe.resetNDealtCards();
	}
		
//	public void stand() {
//		isStanding = true;
//	}
	
	/** Deals cards from the shoe
	*/
	public Card dealCards() {
		return shoe.getCard();
	}
	
	/** Prints the dealer's hand and therefore making it visible
	*/
	public void printDealersHand(){
		String out = new String();
		out += "dealer's hand ";
		out += hand.toString();
		System.out.println(out);	
	}
	
	/** Sets the dealer's hand to a visible status
	*/
	public void setVisible() {
		hand.cards.get(1).setIsUp(true);
	}
	
	/** Clears the hand of the dealer.
	*/
	public void clearHand() {
		hand.reset();
	}
		
}
