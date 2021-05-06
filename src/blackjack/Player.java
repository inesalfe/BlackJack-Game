package blackjack;

import java.util.*;

public class Player {
	
	protected ArrayList<PlayerHand> hands;
	
	private int balance;
	private int bet; // review: Is it really needed?
	private int nHands;
	
	public Player(int initialBet_in, int balance_in) {
		balance = balance_in;
		bet = initialBet_in;
		nHands = 1;
		hands = new ArrayList<PlayerHand>();
		hands.add(new PlayerHand(bet, true, false));
	}
	
	public void hit(int i) {
		hands.get(i).setIsOpening(false);
	}

//	public boolean getIsBust(int i) {
//		return hands.get(i).isBust();
//	}
	
	public void addCard(int i, Card card) {
		hands.get(i).addCard(card);
	}

	public int getNHands() {
		return nHands;
	}
	
	public void stand(int i) {
		hands.get(i).setIsStanding(true);
	}
	
	public void surrender(int i) {
		hands.get(i).setIsSurrender(true);
	}
	
//	public boolean getIsStanding(int i) {
//		return hands.get(i).isStanding();
//	}
	
	public void split(int i) {
		nHands++;
		balance -= bet;
		hands.add(i+1, new PlayerHand(bet, false, true));
		hands.get(i+1).addCard(hands.get(i).cards.get(0));
		hands.add(i+2, new PlayerHand(bet, false, true));
		hands.get(i+2).addCard(hands.get(i).cards.get(1));
		hands.remove(i);
		printPlayersHand(i);
		printPlayersHand(i+1);
	}
	
	public void doubleD(int i) {
		balance -= bet;
		hands.get(i).setBet(2*bet);
	}
	
	public void placeBet(int newBet) {
		bet = newBet;
		balance -= bet;
		hands.get(0).setBet(newBet);
	}
	
	public void clearHands() {
		hands.clear();
		nHands = 1;
		hands.add(new PlayerHand(bet, true, false));
	}
	
//	public int getHandValue(int i) {
//		return hands.get(i).getValue();
//	}

//	public boolean hasBlackjack(int i) {
//		return hands.get(i).checkBlackjack();
//	}

//	public void resetHands(int newBet) {
//		bet = newBet;
//		hands.add(new PlayerHand(newBet, true, false));
//	}
	
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
}
