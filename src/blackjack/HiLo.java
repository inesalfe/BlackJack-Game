package blackjack;

/** Class relative to the card and its attributes.
 * 
 * @param suit Represents the suit of the card (spades, clubs, diamonds, hearts).
 * @param intValue Value associated with each number or figure.
 * @param isUp Evaluates if the card is turned up and therefore visible.
 * @param isUp Evaluates if the Ace values 1 or 11 points.
 * 
*/
public class HiLo extends PlayerStrategy {

	private int running_count;
	private float true_count;
	private int dealt_cards;
	private int nDecks;

	private PlayerStrategy basic_strat;
	
	public HiLo (int max_bet_in, int DDmin_in, int DDmax_in, int nDecks_in) {
		super(max_bet_in, DDmin_in, DDmax_in);
		running_count = 0;
		true_count = 0;
		dealt_cards = 0;
		nDecks = nDecks_in;
		basic_strat = new Basic(max_bet, DDmin, DDmax);
	}
	
	/** Updates the auxiliary variable count.
	 * 
	 * @return card Represents a regular card.
	 * 
	*/
	public void updateCounts(Card card) {
		dealt_cards++;
		if (card.getIntValue() >= 2 && card.getIntValue() <= 6)
			running_count++;
		else if (card.getIntValue() >= 10 || card.getIntValue() == 1)
			running_count--;
		int decks_left = (int) Math.round((52*nDecks-dealt_cards)/52.0);
		if(decks_left <= 0) decks_left = 1;
		true_count = Math.round((float)running_count/decks_left);
	}
	
	// Talvez meter o is pair na player hand
	/** Gets next advisable play.
	 * 
	 * @param nHands Number of hands
	 * @param p_hand Player's hand
	 * @param d_hand Dealer's hand
	 * @param bet Value of the bet
	 * 
	 */
	public String getNextPlay(int nHands, PlayerHand p_hand, Hand d_hand, int bet) {
		canSurrender = true;
		canDouble = ((p_hand.getValue() >= DDmin && p_hand.getValue() <= DDmax) && (2*bet <= max_bet));
		if(p_hand.getNCards() != 2 ) {
			canSurrender = false;
			canDouble = false;
		}
		canSplit = (nHands < 4);
		int p_hand_value = p_hand.getValue();
		int d_card_value = d_hand.getFirst().getIntValue();
		if(d_hand.getFirst().getValue().equals("A")) 
			if(true_count >= 3) 
				return "i";
		if(p_hand_value == 9) {
			if(d_card_value == 2) {
				if (true_count >= 1 && canDouble)
					return "2";
				else 
					return "h";
			}
			else if(d_card_value == 7) {
				if (true_count >= 3 && canDouble)
					return "2";
				else 
					return "h";
			}
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		}
		else if(p_hand_value == 10) {
			if(d_card_value == 10 || d_card_value == 11) {
				if (true_count >= 4 && canDouble)
					return "2";
				else 
					return "h";
			}
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		}
		else if(p_hand_value == 11) {
			if(d_card_value == 11) {
				if (true_count >= 1 && canDouble)
					return "2";
				else 
					return "h";
			}
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		}
		else if(p_hand_value == 12) {
			if(d_card_value == 2)
				return (true_count >= 3) ? "s" : "h";
			else if(d_card_value == 3)
				return (true_count >= 2) ? "s" : "h";
			else if(d_card_value == 4)
				return (true_count >= 0) ? "s" : "h";
			else if(d_card_value == 5)
				return (true_count >= -2) ? "s" : "h";
			else if(d_card_value == 6)
				return (true_count >= -1) ? "s" : "h";
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		} 
		else if(p_hand_value == 13) {
			if(d_card_value == 2)
				return (true_count >= -1) ? "s" : "h";
			if(d_card_value == 3)
				return (true_count >= -2) ? "s" : "h";
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		} // Fab 4
		else if(p_hand_value == 14) {
			if(d_card_value == 10)
				return (true_count >= 3 && canSurrender) ? "u" : basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		}
		else if(p_hand_value == 15) {
			if(d_card_value == 9)
				return (true_count >= 2 && canSurrender) ? "u" : basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
			else if(d_card_value == 10) { // Fab 4 + Illustrious 18
				if(true_count >= 4)
					return "s";
				else return (true_count > 0 && canSurrender) ? "u" : "h";
			}
			else if(d_card_value == 11)
				return (true_count >= 1 && canSurrender) ? "u" : basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		} // end of Fab 4
		else if(p_hand_value == 16) {
			if(d_card_value == 9)
				return (true_count >= 5) ? "s" : "h";
			else if(d_card_value == 10)
				return (true_count >= 0) ? "s" : "h";
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		} 
		else if(p_hand.isPair() && p_hand_value == 20) {
			if(d_card_value == 5)
				return (canSplit && true_count >= 5) ? "p" : "s";
			else if(d_card_value == 5)
				return (canSplit && true_count >= 4) ? "p" : "s";
			else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		}
		else return basic_strat.getNextPlay(nHands, p_hand, d_hand, bet);	
	}
	
	/** Resets the auxiliar counter
	*/
	public void resetCounts() {
		dealt_cards = 0;
		running_count = 0;
		true_count = 0;
	}
	
//	public static void main(String args[]) {
//		PlayerStrategy inter = new HL();
//	}
	
}