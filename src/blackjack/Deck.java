package blackjack;

import java.util.*;

public class Deck {

	protected ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
		for (int i = 0; i < 52; i++)
			deck.add(new Card(i));
	}
	
//	public ArrayList<Card> getDeck() {
//		return deck;
//	}

	@Override
	public String toString() {
		String out = new String();
		for (int i = 0; i < deck.size(); i++) {
			out += deck.get(i).toString();
			out += " ";
		}
		return out;
	}
	
}
