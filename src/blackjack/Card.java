package blackjack;

public class Card {

	private String value;
	private char suit;
	private int intValue;
	private boolean isUp;
	private boolean isSoft;
	
	public Card(int n) {
		
		isUp = false;
		
		// Variable that determines selection of the suit
		int suit_sel = n/13 + 1;
		/*
			- suit_sel = 1 -> Spades
			- suit_sel = 2 -> Hearts
			- suit_sel = 3 -> Clubs
			- suit_sel = 4 -> Diamonds
		*/
		
		// Variable that determines the value of each card
		intValue = n%13;
		if (intValue > 10) {
			
			if (intValue == 11) 
				value = "J";
			
			if (intValue == 12) 
				value = "Q";
			
			if (intValue == 0) 
				value = "K";
		
			intValue = 10;
		}
		
		else
			value = Integer.toString(n%13);
		
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
	
	public String getValue() {
		return value;
	}
	
	public char getSuit() {
		return suit;
	}
	
	public int getIntValue() {
		return intValue;
	}
	
	public boolean getIsUp() {
		return isUp;
	}
	
	public void setSoft(boolean flag) {
		isSoft = flag;
	}
	
	public boolean isSoft() {
		return isSoft;
	}
	
	public void setIsUp(boolean visibility) {
		isUp = visibility;
	}
	
	@Override
	public String toString() {
		if (isUp == true)
			return value + "" + suit;
		else return "X";
	}

	public static void main(String args[]){
		Card c1 = new Card("10", 'D');
		c1.setIsUp(true);
		System.out.println(c1.toString());
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
