package blackjack;

/** Class relative to the card and its attributes.
 * 
 * @param suit Represents the suit of the card (spades, clubs, diamonds, hearts).
 * @param intValue Value associated with each number or figure.
 * @param isUp Evaluates if the card is turned up and therefore visible.
 * @param isUp Evaluates if the Ace values 1 or 11 points.
 * 
*/
public class Card {
	

	private String value;
	private char suit;
	private int intValue;
	private boolean isUp;
	private boolean isSoft;
	
	/** Used to create regular cards.
	 * 
	 * @param n Index of the card 
	 * 
	*/
	public Card(int n) {
		
		isUp = false;
		
		// Variable that determines selection of the suit
		int suit_sel = n/13 + 1;
		
		//	- suit_sel = 1 -> Spades / suit_sel = 2 -> Hearts
		//	- suit_sel = 3 -> Clubs  / suit_sel = 4 -> Diamonds
				
		// Variable that determines the value of each card
		intValue = n%13 + 1;
		if (intValue > 10) {
			
			if (intValue == 11) 
				value = "J";
			
			if (intValue == 12) 
				value = "Q";
			
			if (intValue == 13) 
				value = "K";
		
			intValue = 10;
		}
		
		else
			value = Integer.toString(intValue);
		
		if (value.equals("1")) {
			value = "A";
			intValue = 11;
		}
		
		// Covers all cards that have the same value
		
		switch(suit_sel) {
			
			case 1:
				suit = 'S';
				break;

			case 2:
				suit = 'H';
				break;
				
			case 3:
				suit = 'C';
				break;
				
			default:
				suit = 'D';
		}

		isSoft = (value.equals("A"));
	}
	
	/** Used to create cards.
	 * 
	 * @param value_in Stores the value.
	 * @param suit_in Stores the suit for each card.
	 * 
	*/
	public Card(String value_in, char suit_in) {
		isUp = false;
		isSoft = value_in.equals("A");
		value = value_in;
		suit = suit_in;
		if (value_in.equals("J") || value_in.equals("Q") || value_in.equals("K"))
			intValue = 10;
		else if (value_in.equals("A"))
			intValue = 11;
		else
			intValue = Integer.parseInt(value_in);
	}
	
	/** Gets the value of the card.
	 * 
	 * @return value Value of the card
	 * 
	*/
	public String getValue() {
		return value;
	}
	
	/** Gets the suit of the card.
	 * 
	 * @return suit Suit of the card.
	 * 
	*/
	public char getSuit() {
		return suit;
	}
	
	/** Gets the value which corresponds to each number or figure.
	 *  
	 * @return intValue Value associated with number or figure.
	 * 
	*/
	public int getIntValue() {
		return intValue;
	}
	
	/** Gets the variable which determines if the card is face up. 
	 * 
	 * @return isUp Evaluates if the card is turned up and therefore visible.
	 * 
	*/
	public boolean getIsUp() {
		return isUp;
	}
	
	/** Sets a flag isSoft.
	 * 
	 * @param flag Verifies the state of the card.
	 * @return isSoft Flag that determines if the ace value is soft.
	 * 
	*/
	public void setSoft(boolean flag) {
		isSoft = flag;
	}
	
	/** Gets the variable isSoft, which determines the value of the Ace.
	 * 
	 * @return isUp Evaluates if the card is turned up and therefore visible.
	 * 
	*/
	public boolean isSoft() {
		return isSoft;
	} 
	
	/** Sets the variable isUp.
	 * 
	 * @param visibility Boolean which stores whether or not the card is faced up.
	 * @return isUp state of the card.
	 * 
	*/
	public void setIsUp(boolean visibility) {
		isUp = visibility;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intValue;
		result = prime * result + (isSoft ? 1231 : 1237);
		result = prime * result + (isUp ? 1231 : 1237);
		result = prime * result + suit;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (value.equals("A") && other.value.equals("A"))
			return true;
		if (intValue == other.intValue)
			return true;
		return false;
	}

	/** Turns the result into a string
	 */
	@Override
	public String toString() {
		if (isUp == true)
			return value + "" + suit;
		else return "X";
	}

	/** Main method.
	 *  
	 * @param args.
	 * 
	 */
	public static void main(String args[]){
		Card c1 = new Card("10", 'D');
		System.out.println(c1.toString());
		Card c5 = new Card(12);
		c5.setIsUp(true);
		System.out.println(c5.toString());
		Card c3 = new Card("A", 'C');
		c3.setIsUp(true);
		System.out.println(c3.toString());
		Card c2 = new Card("K", 'H');
		c2.setIsUp(true);
		System.out.println(c2.toString());
		Card c4 = new Card("2", 'S');
		c4.setIsUp(true);
		System.out.println(c4.toString());
	}
	
}