package blackjack;

import java.util.*;

public class Player {
	
	ArrayList<PlayerHand> hands;
	
	private int balance;
	private int bet; // review: Is it really needed?
	private boolean isHitting;
	private boolean isBust;
	private boolean isStanding;
	private boolean isSurrender;
	private boolean isBlackjack;
	
	public Player(int initialBet_in, int balance_in) {
		balance = balance_in;
		bet = initialBet_in;
		isHitting = false;
		isBust = false;
		isStanding = false;
		isSurrender = false;
		isBlackjack = false;
		hands = new ArrayList<PlayerHand>();
		hands.add(new PlayerHand(bet, true, false));
	}
	
	public void hit () {
		isHitting = true;
	}
	
	public void stand() {
		isStanding = true;
	}
	
	public void surrender() {
		isSurrender = true;
	}
	
	public void split() {
		hands.add(new PlayerHand(bet, false, true));
		hands.add(new PlayerHand(bet, false, true));
		hands.remove(0);
	}
	
	public void doubleD() {
		hands.get(0).setBet(2*bet);
	}
	
	public void placeBet(int newBet) {
		bet = newBet;
		hands.get(0).setBet(newBet);
	}
	
	public void clearHands() {
		hands.clear();
	}

	public void resetHands(int newBet) {
		bet = newBet;
		hands.add(new PlayerHand(newBet, true, false));
	}
	
	public boolean getIsHitting () {
		return isHitting;
	}
	
	public boolean getIsStanding () {
		return isStanding;
	}
	
	public boolean getIsBust () {
		return isBust;
	}
	
	public void updateBalance(int newBalance) {
		balance = newBalance;
	}
	
}
