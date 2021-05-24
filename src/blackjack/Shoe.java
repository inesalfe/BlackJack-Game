package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/** Class that characterizes the shoe
 * 
 * @param shoe Represents the shoe
 * @param nDecks Number of decks.
 * @param nDealtCards Number of dealt cards.
 *
 */
public class Shoe {
	
	private ArrayList<Card> shoe;
	private int nDecks;
	private int nDealtCards;
	
	/** Creates a complete deck
	 * 
	 * @param nDecks_in Number of decks that form a shoe.
	 * 
	 */
	public Shoe(int nDecks_in) {
		nDecks = nDecks_in;
		nDealtCards = 0;
		shoe = new ArrayList<Card>();
		for (int j = 0; j < nDecks; j++) {
			for (int i = 0; i < 52; i++)
				shoe.add(new Card(i));
		}
	}
	
	/** Creates a shoe based on the file input
	 * 
	 * @param shoeFile_in Name of the file which contains the shoe
	 * 
	 */
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
			System.out.println("Invalid shoe file: " + e);
			System.exit(0);
		}
		nDecks = cards.size()/52;
	}
	
	/** Gets number of decks.
	 * 
	 * @return nDecks Number of decks.
	 */
	public int getNDecks() {
		return nDecks;
	}
	
	/** Shuffles the shoe.
	 */
	public void shuffle() {
		Collections.shuffle(shoe);
		for(Card c : shoe) {
			c.setIsUp(false);
		}
	}

	/** Gets the card
	 * 
	 * @return card Represents a regular card.
	 * 
	 */
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