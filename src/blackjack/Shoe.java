package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Shoe {
	
	private ArrayList<Card> shoe;
	private int nDecks; // Ver se vale a pena manter esta variável
	private int nDealtCards;
	
	public Shoe(int nDecks_in) {
		nDecks = nDecks_in;
		nDealtCards = 0;
		shoe = new ArrayList<Card>();
		for (int j = 0; j < nDecks; j++) {
			Deck deck = new Deck();
			shoe.addAll(deck.deck);
		}
	}
	
	public Shoe(String shoeFile_in) {
		nDealtCards = 0;
		shoe = new ArrayList<Card>();
		ArrayList<String> cards = new ArrayList<String>();		
		Scanner scanner;
		try {
			scanner = new Scanner(new File(shoeFile_in));
			String line = new String();
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				cards.addAll(Arrays.asList(line.split(" ", 0)));
			}
			scanner.close();
			char suit;
			String value;
			String temp;
			for(int i = 0; i < cards.size(); i++) {
				temp = cards.get(i);
				suit = temp.charAt(temp.length()-1);
				value = temp.substring(0, temp.length()-1);
				shoe.add(new Card(value, suit));
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void shuffle() {
		Collections.shuffle(shoe);
	}

	public Card getCard() {
		Card card = shoe.get(0);
		shoe.add(card);
		shoe.remove(0);
		++nDealtCards;
		return card;
	}
	
	public int getNDealtCards() {
		return nDealtCards;
	}
	
	public void resetNDealtCards() {
		nDealtCards = 0;
	}
	
	@Override
	public String toString() {
		String out = new String();
		for (int i = 0; i < shoe.size(); i++) {
			out += shoe.get(i).toString();
			out += '\n';
		}
		return out;
	}

	public static void main(String args[]){
		Shoe shoe1 = new Shoe("shoe-file.txt");
		System.out.println(shoe1.toString());
	}	

}
