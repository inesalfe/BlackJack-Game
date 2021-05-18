package blackjack;

import java.util.*;

public class Player {
	
	protected ArrayList<PlayerHand> hands;
	
	private float balance;
	private int bet; // review: Is it really needed?
	private int nHands;
	
	private boolean isSplitting;
	private boolean isInsuring;
	
	public Player(int initialBet_in, int balance_in) {
		balance = balance_in;
		bet = initialBet_in;
		nHands = 1;
		hands = new ArrayList<PlayerHand>();
		hands.add(new PlayerHand(bet, true, false));
	}
	
	public void hit(int i) {
		hands.get(i).setIsOpening(false);
		hands.get(i).setHitting(true);
	}
	
	public void addCard(int i, Card card) {
		hands.get(i).addCard(card);
	}

	public int getNHands() {
		return nHands;
	}
	
	public void stand(int i) {
		hands.get(i).setIsOpening(false); // Vejam se concordam com esta linha
		hands.get(i).setIsStanding(true);
	}
	
	public void surrender(int i) {
		hands.get(i).setIsOpening(false); // Vejam se concordam com esta linha
		hands.get(i).setIsSurrender();
	}
	
	public void insurance() {
		hands.get(0).setIsOpening(false); // Vejam se concordam com esta linha
		isInsuring = true;
		balance -= bet;
	}
	
	public boolean isInsuring() {
		return isInsuring;
	}
	
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
	
	public void doubleD(int i) {
		hands.get(i).setIsOpening(false); // Vejam se concordam com esta linha
		balance -= bet;
		hands.get(i).setBet(2*bet);
		hands.get(i).setIsDoubleD();
		hands.get(i).setHitting(true);
	}
	
	public void placeBet(int newBet) {
		bet = newBet;
		balance -= bet;
		hands.get(0).setBet(newBet);
	}
	
	public void clearHands() {
		hands.clear();
		nHands = 1;
		isInsuring = false;
		hands.add(new PlayerHand(bet, true, false));
	}

	public float getBalance() {
		return balance;
	}
	
	public void updateBalance(float update) {
		balance += update;
	}
	
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

	public boolean isHittingHand(int i) {
		return hands.get(i).isHitting();
	}

	public boolean isStandingHand(int i) {
		return hands.get(i).isStanding();
	}

	public boolean isSurrendingHand(int i) {
		return hands.get(i).isSurrender();
	}

	public boolean isSplitting() {
		return isSplitting;
	}

	public void setIsSplitting(boolean b) {
		this.isSplitting = b;
	}

	public boolean isDoubleDHand(int i) {
		return hands.get(i).isDouble();
	}

}
