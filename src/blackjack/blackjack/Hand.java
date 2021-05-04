package blackjack;

import java.util.*;

public class Hand {
	
	private ArrayList<Card> cards;
	
	private int value;
	private int nCards;
	
	public Hand() {
		cards = new ArrayList<Card>();
		value = 0;
		nCards = 0;
	}
	
	public boolean checkBust() {
		return value > 21;
		
	}
	
	public boolean checkBlackjack() {
		return (nCards == 2 && value == 21);
		
	}
	
	public int getValue() {
		return value;
		
	}
	
	public int getNCards() {
		return nCards;
		
	}
	
	public void addCard(Card card) {
		cards.add(card);
		++nCards;
		value += card.getIntValue();
	}
	
}
