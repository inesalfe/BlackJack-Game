package cardCounting;

import blackjack.Card;
import blackjack.Hand;
import blackjack.PlayerHand;

/** Class relative to the card and its attributes.
 * 
 * @param max_bet Maximum value for the bet. 
 * @param DDmin Minimum value for a Double Down.
 * @param DDmax Maximum value for a Double Down.
 * @param canSurrender Evaluates if it is still possible to surrender in the game.
 * @param canDouble Evaluates if it is still possible to double down in the game.
 * 
*/
public class Basic extends PlayerStrategy {
	
	public Basic (int max_bet_in, int DDmin_in, int DDmax_in) {
		super(max_bet_in, DDmin_in, DDmax_in);
	}
	
	
	// Talvez meter o is pair na player hand
	/** Calculates the next play
	* 
	* @param p_hand Represents the player's hand.
	* @param d_hand Represents the dealer's hand.
	* @param bet Represents the bet going in the game.
	* 
	*/
	public String getNextPlay(int nHands, PlayerHand p_hand, Hand d_hand, int bet) {
		canSurrender = true;
		canSplit = (nHands < 4);
		canDouble = ((p_hand.getValue() >= DDmin && p_hand.getValue() <= DDmax) && (2*bet <= max_bet));
		if(p_hand.getNCards() != 2 ) {
			canSurrender = false;
			canDouble = false;
		}
		if (p_hand.isPair()) {
			return pairs(p_hand, d_hand.getFirst(), bet);
		}
		boolean hard = true;
		for (Card card: p_hand.getCards()) {
			if (card.getValue().equals("A") && card.isSoft()) {
				hard = false;
				break;
			}
		}
		if (hard == true)
			return hard(p_hand.getValue(), d_hand.getFirst(), bet);
		else
			return soft(p_hand.getValue(), d_hand.getFirst().getIntValue(), bet);		
	}

	/** Calculates action when the value of the ace is 1.
	 * 
	 * @param p_hand_value Total value assigned to the player's hand.
	 * @param d_card Represents a dealer's card.
	 * @param bet Represents the bet.
	 * @return <h> Hit.
	 * @return <s> Stand.
	 * @return <2> Double the bet.
	 * @return <u> Surrender
	 * 
	 */
	public String hard(int p_hand_value, Card d_card, int bet) {
		int d_card_value = d_card.getIntValue();
		if (p_hand_value >= 5 && p_hand_value <= 8)
			return "h";
		else if (d_card.getValue().equals("A") && p_hand_value <= 15)
			return "h";
		else if (p_hand_value == 9 && (d_card_value == 2 || (d_card_value <=10 && d_card_value >= 7)))
			return "h";
		else if ((p_hand_value >= 12 && p_hand_value <= 14) && (d_card_value >= 7 && d_card_value <= 10))
			return "h";
		else if ((p_hand_value == 15) && (d_card_value >= 7 && d_card_value <= 9))
			return "h";
		else if ((p_hand_value == 16) && (d_card_value == 7 || d_card_value == 8))
			return "h";
		else if (p_hand_value == 10 && d_card_value == 10)
			return "h";
		else if (p_hand_value >= 17 && p_hand_value <= 21)
			return "s";
		else if ((p_hand_value >= 13 && p_hand_value <= 16) && (d_card_value <= 6))
			return "s";
		else if ((p_hand_value == 12) && (d_card_value >= 4 && d_card_value <= 6))
			return "s";
		else if (p_hand_value >= 9 && p_hand_value <= 11) {
			if (canDouble)
				return "2";
		}
		else if ((p_hand_value == 15 && d_card_value == 10) || (p_hand_value == 16 && d_card_value >= 9))
			if (canSurrender)
				return "u";			
		return "h";
	}
	
	/** Calculates action when the value of the ace is 11
	 * 
	 * @param p_hand_value Total value assigned to the player's hand
	 * @param d_card Represents a dealer's card
	 * @param bet Represents the bet
	 * @return <h> Hit
	 * @return <s> Stand
	 * @return <2> Double the bet
	 * @return <u>
	 * 
	 */
	public String soft(int p_hand_value, int d_card_value, int bet) {
		if ((p_hand_value >= 13 && p_hand_value <= 17) && (d_card_value >= 7 && d_card_value <= 11))
			return "h";
		else if ((p_hand_value == 18) && (d_card_value <= 11 && d_card_value >= 9))
			return "h";
		else if ((d_card_value == 2) && (p_hand_value <= 17 && p_hand_value >= 13))
			return "h";
		else if ((d_card_value == 3) && (p_hand_value <= 16 && p_hand_value >= 13))
			return "h"; 
		else if ((d_card_value == 4) && (p_hand_value <= 14 && p_hand_value >= 13))
			return "h";
		else if (p_hand_value >= 19 && p_hand_value <= 21)
			return "s";
		else if ((p_hand_value == 18) && (d_card_value == 2 || d_card_value == 7 || d_card_value == 8))
			return "s";
		else if ((p_hand_value == 18) && (d_card_value >= 3 && d_card_value <= 6)) {
			if (canDouble)
				return "2";
			else
				return "s";
		}
		else if (canDouble)
			return "2";
		return "h";
	}

	/** Checks for a possible pair of cards and then calculates the next action accordingly.
	 * 
	 * @param p_hand_value Total value assigned to the player's hand.
	 * @param d_card Represents a dealer's card
	 * @param bet Represents the value of the bet.
	 * @return h Hit.
	 * @return s Stand.
	 * @return 2 Double the bet.
	 * @return p Split.
	 * 
	 */
	public String pairs(PlayerHand p_hand, Card card, int bet) {
		int d_card_value = card.getIntValue();
		int p_card_value = p_hand.getFirst().getIntValue();
		if (p_card_value == 4)
			return "h";
		else if ((p_card_value == 2 || p_card_value == 3) && (d_card_value == 2 || d_card_value == 3 || (d_card_value >= 8 && d_card_value <= 11)))
			return "h";
		else if ((p_card_value == 6) && (d_card_value == 2 || (d_card_value >= 7 && d_card_value <= 11)))
			return "h";
		else if ((p_card_value == 5) && (d_card_value == 10 || d_card_value == 11))
			return "h";
		else if ((p_card_value == 7) && (d_card_value >= 8 && d_card_value <= 11))
			return "h";
		else if (p_card_value == 10)
			return "s";
		else if ((p_card_value == 9) && (d_card_value == 7 || d_card_value == 10 || d_card_value == 11))
			return "s";
		else if ((p_card_value == 5) && (d_card_value >= 2 && d_card_value <= 9)) {
			if (canDouble) 
				return "2";
			else
				return "h";	
		}
		if (canSplit)
			return "p";
		else return (p_hand.getCards().get(0).getValue().equals("A")) ? "s" : hard(p_hand.getValue(), card, bet);
	}
	
//	public static void main(String args[]) {
//		PlayerStrategy basic_strat = new Basic(10, 9, 11);
//		
//	}
	
}