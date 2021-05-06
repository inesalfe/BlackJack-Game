package blackjack;

import java.util.*;

public class Hand {
	
	protected ArrayList<Card> cards;
	
	protected int value;
	protected int nCards;
	private boolean isBust;
	private boolean isStanding;
	
	public Hand() {
		cards = new ArrayList<Card>();
		value = 0;
		nCards = 0;
		isBust = false;
		isStanding = false;
	}
	
	public boolean isBust() {
		return isBust;
	}

	public void setIsStanding(boolean bool) {
		isStanding = bool;
	}

	public boolean getIsStanding() {
		return isStanding;
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
		if (nCards != 1)
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
		if (value > 21) {
			isBust = true;
		}
	}
	
	@Override
	public String toString() {
		String out = new String();
		for (int i = 0; i < cards.size(); i++) {
			out += cards.get(i).toString();
			out += " ";
		}
		if (cards.get(1).getIsUp() == true)
			out += "(" + value + ")";
		return out;
	}

	public static void main(String args[]){
		Card c1 = new Card("10", 'D');
		c1.setIsUp(true);
		Card c2 = new Card("9", 'S');
		c2.setIsUp(true);
		Card c3 = new Card("A", 'C');
		c3.setIsUp(true);
		Hand h1 = new  Hand();
		h1.addCard(c1);
		h1.addCard(c2);
		h1.addCard(c3);
		System.out.println(h1.toString());
	}
	
	public void reset() {
		cards.clear();
		value = 0;
		nCards = 0;
		isBust = false;
		isStanding = false;
	}
	
}
