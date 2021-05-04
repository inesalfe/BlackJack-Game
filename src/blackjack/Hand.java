package blackjack;

import java.util.*;

public class Hand {
	
	protected ArrayList<Card> cards;
	
	protected int value;
	protected int nCards;
	
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
		if (nCards == 0)
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
	}
	
	// Corrigir isto com value e etc.
	@Override
	public String toString() {
		String out = new String();
		for (int i = 0; i < cards.size(); i++) {
			out += cards.get(i).toString();
			out += " ";
		}
		return out;
	}

	public static void main(String args[]){
		Card c1 = new Card("10", 'D');
		c1.setIsUp(true);
		Card c3 = new Card("A", 'C');
		Hand h1 = new  Hand();
		h1.addCard(c1);
		h1.addCard(c3);
		System.out.println(h1.toString());
	}
	
}
