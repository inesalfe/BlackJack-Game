package blackjack;

import java.io.*;

public class Game {
	
	private String Mode;
	private int maxBet;
	private int minBet;
	private int intShuffle;
	private String shoeFile;
	private String cmdFile;
	private int balance;
	private int sNumble;
	private String strategy;
	
	private boolean isReady;
	private boolean shuffling;
	
	private Player player;
	private Dealer dealer;
	
	public Game(String Mode_in, int maxBet_in, int minBet_in, int intShuffle_in, String shoeFile_in, String cmdFile_in, int balance_in, int sNumble, String strategy_in) {
		
	}
	
	// RICARDO - Se no iníco de cada jogada o valor do intShuffle for atingido, fazer shuffle
	// Oki. Deixa-apara nããme esquecer
	
	public void play() {
		// Wait for player bet (command b <bet_value>)
		// 		If any other command comes, ignore it or print an error or...
		// User bets: ready to deal (flag)
		// Wait for d command
		// Call deal();
		while(true) {
			if(shuffling) {
				// Shuffle shoe; Either do it through dealer (dealer needs to have a shuffle method)
				// or Game has to have a shoe object;
				System.out.println("shuffling the shoe...");
				shuffling = false;
			}
			InputStreamReader isr =	new InputStreamReader(System.in);	
			BufferedReader br =	new	BufferedReader(isr);	
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] toks = s.split(" ", 0);
			if(toks[0].length() == 1) {
				if(toks[0].charAt(0) == 'b') {
					if(isReady) {
						//bet already placed
					} else {
						player.placeBet(Integer.parseInt(toks[1]));
						isReady = true;
					}
				}else if(toks[0].charAt(0) == 'd') {
					if(isReady) {
						deal();
						isReady = false;
					} else {
						// place a bet first
					}
					
				}
			}
			
		}
		

	}
	
	public void deal() { // for 1 player; try to generalize later
		// dealer deals cards to all players
		for(int i = 0; i < 2; ++i) {
			player.hands.get(0).addCard(dealer.dealCards());
		}
		
		for(int i = 0; i < 2; ++i) {
			dealer.hand.addCard(dealer.dealCards());
		}
		
		// print players' and dealer's hands;
		
		// Ciclo para cada player...
		while(true) {
			// get command from console or file
			
		}
		
		
	}

	public void printPlayersHand(int i){
		String out = new String();
		out += "player's hand ";
		if (i != -1) {
			out += "[" + i + "] ";
			out += player.hands.get(i).toString();
		}
		else {
			out += player.hands.get(i).toString();
		}
		System.out.println(out);	
	}

	public void printDealersHand(){
		String out = new String();
		out += "dealer's hand ";
		out += dealer.hand.toString();
		System.out.println(out);	
	}
	
	public static void main(String args[]){
	}

}
