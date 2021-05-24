package blackjack;

import java.util.*;


/** Class relative to the hand.
 * 
 * @param cards List of cards which compose the hand.
 * @param value Card value.
 * @param nCards Number of cards in the hand.
 * @param isBust Activate if the hand value is superior to 21.
 * @param isStanding Defines if the player decided to stand.
 * 
*/
public class Hand {
	
	protected ArrayList<Card> cards;
	
	protected int value;
	protected int nCards;
	protected boolean isBust;
	protected boolean isStanding;
	
	/** Creates a hand of cards.
	 * 
	 * @param cards List of cards which compose the hand.
	 * @param value Card value.
	 * @param nCards Number of cards in the hand.
	 * @param isBust Activate if the hand value is superior to 21.
	 * @param isStanding Defines if the player decided to stand.
	 * 
	*/
	public Hand() {
		cards = new ArrayList<Card>();
		value = 0;
		nCards = 0;
		isBust = false;
		isStanding = false;
	}
	
	/** Checks if the hand busts.
	 * 
	 * @return A boolean that determines if the hand busted.
	 * 
	*/
	public boolean isBust() {
		return isBust;
	}
	
	/** Sets the isStanding variable.
	 * 
	 * @return A boolean that determines if the player decided to stand.
	 * 
	*/
	public void setIsStanding(boolean bool) {
		isStanding = bool;
	}
	
	/** Checks if the hand stands.
	 * 
	 * @return A boolean that determines if the player decided to stand.
	 * 
	*/
	public boolean isStanding() {
		return isStanding;
	}
	
	/** Checks for a BlackJack.
	 * 
	 * @return A boolean that classifies the BlackJack.
	 * 
	*/
	public boolean checkBlackjack() {
		return (nCards == 2 && value == 21);
	}
	
	/** Gets Card value.
	 * 
	 * @return Value of the card.
	 * 
	*/
	public int getValue() {
		return value;
	}
	
	public ArrayList<Card> getCards(){
		ArrayList<Card> visible_cards = new ArrayList<Card>();
		for(Card c : cards)
			if(c.getIsUp())
				visible_cards.add(c);
		return visible_cards;
	}
	
	/** Gets number of cards.
	 * 
	 * @return Number of cards.
	 * 
	*/
	public int getNCards() {
		return nCards;
	}
	
	/** Adds a card.
	 * 
	 * @param card Represents a regular card.
	 * 
	*/
	public void addCard(Card card) {
		cards.add(card);
		if (nCards != 1)
			cards.get(nCards).setIsUp(true);
		++nCards;
		value += card.getIntValue();
		if(value > 21) {
			for(Card c : cards) {
				if(c.getValue().equals("A") && c.isSoft()) {
					value -= 10;
					c.setSoft(false);
					if(value <= 21) break;
				}
			}
		}
		if (value > 21) {
			isBust = true;
		}
	}
	
	/** Resets the hand.
	*/
	public void reset() {
		cards.clear();
		value = 0;
		nCards = 0;
		isBust = false;
		isStanding = false;
	}
	
	/** Gets first card 
	 * 
	 * @return cards.get(0) First card
	 * 
	 */
	public Card getFirst() {
		return cards.get(0);
	}
	
	/** Converts to String
	 * 
	 */
	@Override
	public String toString() {
		String out = new String();
		for (int i = 0; i < cards.size(); i++) {
			out += cards.get(i).toString();
			out += " ";
		}
		if (cards.get(1).getIsUp() == true)
			out += "(" + value + ")";
		return out;
	}
	/** Main method relative to the hand
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		Card c1 = new Card("10", 'D');
		c1.setIsUp(true);
		Card c2 = new Card("9", 'S');
		c2.setIsUp(true);
		Card c3 = new Card("A", 'C');
		c3.setIsUp(true);
		Hand h1 = new  Hand();
		h1.addCard(c1);
		h1.addCard(c2);
		h1.addCard(c3);
		System.out.println(h1.toString());
	}

	
}

