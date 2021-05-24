package blackjack;

import java.util.*;

/** Class relative to the Player
 * 
 * @param balance Current balance of the player.
 * @param bet Bet that is set by the player.
 * @param nHands Number of hands (can vary due to split).
 * @param isSplitting Flag that checks if a split was made.
 * @param isInsuring Activates if a player decided to make insurance.
 * 
 */
public class Player {
	
	protected ArrayList<PlayerHand> hands;
	
	private float balance;
	private int bet; // review: Is it really needed?
	private int nHands;
	
	private boolean isSplitting;
	private boolean isInsuring;
	
	/** Initializes the player's actions.
	 * 
	 * @param initialBet_in Initial bet going in the game.
	 * @param balance_in Player's balance.
	 * 
	 */
	public Player(int initialBet_in, int balance_in) {
		balance = balance_in;
		bet = initialBet_in;
		nHands = 1;
		hands = new ArrayList<PlayerHand>();
		hands.add(new PlayerHand(bet, true, false));
	}
	
	/** Performs hit action.
	 * 
	 * @param i index of the hand.
	 * 
	 */
	public void hit(int i) {
		hands.get(i).setIsOpening(false);
		hands.get(i).setHitting(true);
	}
	
	/** Adds a card t the hand.
	 * 
	 * @param i Card's index.
	 * @param card Regular card.
	 * 
	 */
	public void addCard(int i, Card card) {
		hands.get(i).addCard(card);
	}

	/** Gets number of hands.
	 * 
	 * @return nHands Number of hands.
	 * 
	 */
	public int getNHands() {
		return nHands;
	}
	
	/** Performs the stand action.
	 * 
	 * @param i Card's index.
	 * 
	 */
	public void stand(int i) {
		hands.get(i).setIsOpening(false); // Vejam se concordam com esta linha
		hands.get(i).setIsStanding(true);
	}
	
	/** Performs the surrender action.
	 * 
	 * @param i Card's index
	 * 
	 */
	public void surrender(int i) {
		hands.get(i).setIsOpening(false);
		hands.get(i).setIsSurrender();
	}
	
	/** Performs the insurance action
	 */
	public void insurance() {
		hands.get(0).setIsOpening(false);
		isInsuring = true;
		balance -= bet;
	}
	
	/** Checks if the player decided to insure.
	 * 
	 * @return isInsuring Flag which evaluates the decision to ensure.
	 * 
	 */
	public boolean isInsuring() {
		return isInsuring;
	}
	
	/** Performs the split action.
	 * 
	 * @param i Index of the card.
	 * 
	 */
	public void split(int i) {
		hands.get(i).setIsOpening(false); // Vejam se concordam com esta linha
		isSplitting = false;
		nHands++;
		balance -= bet;
		hands.add(i+1, new PlayerHand(bet, false, true));
		hands.get(i+1).addCard(hands.get(i).cards.get(0));
		hands.add(i+2, new PlayerHand(bet, false, true));
		hands.get(i+2).addCard(hands.get(i).cards.get(1));
		hands.remove(i);
	}
	
	/** Peforms the double down action.
	 * 
	 * @param i Index of the card.
	 * 
	 */
	public void doubleD(int i) {
		hands.get(i).setIsOpening(false); // Vejam se concordam com esta linha
		balance -= bet;
		hands.get(i).setBet(2*bet);
		hands.get(i).setIsDoubleD();
		hands.get(i).setHitting(true);
	}
	
	/** Places a nwe value for the bet.
	 * 
	 * @param newBet New value for the bet.
	 * 
	 */
	public void placeBet(int newBet) {
		bet = newBet;
		balance -= bet;
		hands.get(0).setBet(newBet);
	}
	
	/** Resets the hands
	 */
	public void clearHands() {
		hands.clear();
		nHands = 1;
		isInsuring = false;
		hands.add(new PlayerHand(bet, true, false));
	}

	/** Gets the current value for the balance.
	 * 
	 * @return balance Current value of money possessed by the player.
	 * 
	 */
	public float getBalance() {
		return balance;
	}
	
	/** Updates the balance value 
	 * 
	 * @param update Change in balance
	 */
	public void updateBalance(float update) {
		balance += update;
	}
	
	/** Displays the player's hand
	 * 
	 * @param i Card's index
	 */
	public void printPlayersHand(int i){
		String out = new String();
		out += "player's hand ";
		if (i != -1) {
			out += "[" + (i+1) + "] ";
			out += hands.get(i).toString();
		}
		else {
			out += hands.get(0).toString();
		}
		System.out.println(out);	
	}

	/** Checks if the player decided to hit
	 * 
	 * @param i Card's index
	 * @return hands.get(i).isHitting Decision to hit
	 */
	public boolean isHittingHand(int i) {
		return hands.get(i).isHitting();
	}

	/** Checks if the player decided to stand
	 * 
	 * @param i Card's index
	 * @return hands.get(i).isStanding Decision to stand
	 */
	public boolean isStandingHand(int i) {
		return hands.get(i).isStanding();
	}

	/** Checks if the player decided to surrender
	 * 
	 * @param i Card's index
	 * @return hands.get(i).isSurrender Decision to surrender
	 */
	public boolean isSurrendingHand(int i) {
		return hands.get(i).isSurrender();
	}

	/** Checks for a split.
	 * 
	 * @return isSplitting Result of the decision to split.
	 * 
	 */
	public boolean isSplitting() {
		return isSplitting;
	}

	/** Gets value of the bet
	 * 
	 * @return bet Value of the bet
	 */
	public int getBet() {
		return bet;
	}

	/** Sets the decision to split
	 * 
	 * @param b Decision to split
	 */
	public void setIsSplitting(boolean b) {
		this.isSplitting = b;
	}

	/**	Checks for a double down
	 * 
	 * @param i Card's index
	 * @return hands.get(i).isDouble Decision to surrender
	 */
	public boolean isDoubleDHand(int i) {
		return hands.get(i).isDouble();
	}

}
