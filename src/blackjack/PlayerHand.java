package blackjack;

/** Class relative to a usable hand by a player.
 * 
 * @param bet Money used to make the bet for the game.
 * @param isOpening Evaluates if the hand is visible or not.
 * @param isSplit Checks if a split was made during the game.
 * @param isDoubleD Used to check for a Double down bet.
 * @param isPair Activates if both cards in the hand have the same value.
 * @param isSurrender Activates if the player wants to surrender the game.
 * 
*/
public class PlayerHand extends Hand {
	
	private int bet;
	private boolean isOpening;
	private boolean isPair;
	private boolean isDoubleD;
	private boolean isSplit;
	private boolean isSurrender;
	private boolean isHitting;
	
	/** Creates a usable Hand for the player.
	 * 
	 * @param bet_in Bet that is placed at the beginning.
	 * @param opening Evaluates if the hand is visible or not.
	 * @param split Checks for a split.
	 * 
	*/
	public PlayerHand(int bet_in, boolean opening, boolean split) {
		super();
		bet = bet_in;
		isOpening = opening;
		isSplit = split;
		isDoubleD = false;
		isPair = false;
		isSurrender = false;
	}
	
	/** Gets the value of the bet.
	 * 
	 * @return bet Value of the bet.
	 * 
	*/
	public int getBet() {
		return bet;
	}
	
	/** Doubles the current bet
	*/
	public void doubleBet() {
		bet *= 2;
	}
	
	/** Flag which indicates that the player decided to surrender.
	 * 
	 * @return isSurrender Decision to surrender.
	 * 
	*/
	public boolean isSurrender() {
		return isSurrender;
	}
	
	/** Activates if the player decides to surrender.
	 * 
	*/
	public void setIsSurrender() {
		isSurrender = true;
	}
	
	/** Flag which indicates if the player's hand is open or not.
	 * 
	 * @return isOpening State of the hand (open or not).
	 * 
	*/
	public boolean isOpening() {
		return isOpening;
	}
	
	/** Activates if the hand is open.
	*/
	public void setIsOpening(boolean bool) {
		isOpening = bool;
	}
	
	/** Checks if the card in the hand have the same value.
	 * 
	 * @return isPair Flag which indicates if the cards have the same value.
	 * 
	*/
	public boolean isPair() {
		return isPair;	
	}
	
	/** Checks if there was a double down.
	*/
	public boolean isDouble() {
		return isDoubleD;
	}
	
	/** Checks if a double down was made.
	 * 
	 * @return isDoubleD Double down.
	 * 
	*/
	public void setIsDoubleD() {
		isDoubleD = true;
	}
	
	/** Checks if there was a split.
	 * 
	 * @return isSplit Flag for a split.
	 * 
	*/
	public boolean isSplit() {
		return isSplit;
	}
	
	/** Sets the bet the player wants to make.
	 * 
	 * @return isDoubleD Double down.
	 * 
	*/
	public void setBet(int bet_in) {
		bet = bet_in;
	}
	
	/** Adds a card to the hand.
	 * 
	 * @param card Represents a regular card.
	 * 
	*/
	public void addCard(Card card) {
		super.addCard(card);
		card.setIsUp(true);
		isPair = false;
		if (nCards == 2) {
			if (cards.get(0).equals(cards.get(1)))
				isPair = true;			
		}
	}
	
	/** Converts to string
	 */
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

	/** Main method for the Player's Hand
	 *  
	 * @param args
	 */
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
		h3.addCard(c7);
		System.out.println(h1.isPair());
		System.out.println(h2.isPair());
		System.out.println(h3.isPair());
	}
	
	/** Used to check for hits.
	 * 
	 * @return isHitting Flag which indicates if an hit is being made.
	 * 
	*/
	public boolean isHitting() {
		return isHitting;
	}
	
	/** Used for hits.
	 * 
	 * @return isHitting Flag which indicates if an hit is being made.
	 * 
	*/
	public void setHitting(boolean isHitting) {
		this.isHitting = isHitting;
	}
	
}
