package blackjack;

public class Game {
	
	private GameMode mode;
	
	private int minBet;
	private int maxBet;
	private int nDecks;
	private int intShuffle;

	private boolean isReady = false;
	private boolean shuffling;
	
	private Player player;
	private Dealer dealer;
	
	// Interative && Simulation
	public Game(char Mode_in, int minBet_in, int maxBet_in, int balance_in, int nDecks_in, int intShuffle_in, int sNumber_in, String strategy_in) {
		if (Mode_in == 'i')
			mode = new Interative();
		else
			mode = new Simulation(sNumber_in, strategy_in);
		minBet = minBet_in;
		maxBet = maxBet_in;
		nDecks = nDecks_in;
		intShuffle = intShuffle_in;
	
		shuffling = true;
		
		player = new Player(minBet, balance_in);
		dealer = new Dealer(nDecks_in);
	}

	// Debug
	public Game(char Mode_in, int minBet_in, int maxBet_in, int balance_in, String shoeFile_in, String cmdFile_in) {
		mode = new Debug(shoeFile_in, cmdFile_in);
		minBet = minBet_in;
		maxBet = maxBet_in;
	
		shuffling = false;

		player = new Player(minBet, balance_in);
		dealer = new Dealer(shoeFile_in);
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
			// reset hands and stuff
			player.clearHands();
			dealer.clearHand();
			if(dealer.shoe.getNDealtCards() >= Math.ceil(intShuffle/100.0f * nDecks * 52)) {
				shuffling = true;
			}
			if(shuffling) {
				// Shuffle shoe; Either do it through dealer (dealer needs to have a shuffle method)
				// or Game has to have a shoe object;
				System.out.println("shuffling the shoe...");
				dealer.shuffle();
				shuffling = false;

			}
			String s = mode.getCommand();
			String[] toks = s.split(" ", 0);
			if(toks[0].charAt(0) == 'b') {
				if(isReady) {
					System.out.println(s + ": illegal command");
				} else {
					int bet = (toks.length == 1) ? minBet : Integer.parseInt(toks[1]);
					if (bet < minBet || bet > maxBet) {
						System.out.println(s + ": illegal command");
						continue;
					}
					player.placeBet(bet);
					System.out.println("player is betting " + bet);
					isReady = true;							
				}
			}else if(toks[0].charAt(0) == 'd') {
				if(isReady) {
					deal();
					isReady = false;
				} else {
					System.out.println(toks[0] + ": illegal command");
				}
			} else if(toks[0].charAt(0) == '$') {
				System.out.println("Player's current balance is " + player.getBalance());		
			} else if(toks[0].charAt(0) == 'q') {
				System.out.println("bye");
				System.exit(0);				
			} else
				continue;			
		}
	}
	
	
	public char getCommand(String line) {
		if (line.length() <= 2) {
			if (line.equals("h"))
				return 'h';
			else if (line.equals("s"))
				return 's';
			else if (line.equals("i"))
				return 'i';
			else if (line.equals("u"))
				return 'u';
			else if (line.equals("p"))
				return 'p';
			else if (line.equals("2"))
				return '2';
			else if (line.equals("ad"))
				return 'a';
			else if (line.equals("st"))
				return 't';
			else if (line.equals("$"))
				return '$';
			else if (line.equals("q"))
				return 'q';
		}
		return '\0';
	}
	
	public void deal() { // for 1 player; try to generalize later
		// dealer deals cards to all players
		for(int i = 0; i < 2; ++i) {
			player.addCard(0, dealer.dealCards());
		}
		for(int i = 0; i < 2; ++i) {
			dealer.addCard(dealer.dealCards());
		}
		dealer.printDealersHand();
		player.printPlayersHand(-1);
		
		// Ciclo para cada player...
		String line;
		String hand_index;
		int print_index;
		
		for(int i = 0; i < player.getNHands(); ++i) {
			print_index = -1;
			hand_index = "";
			// If the player has more than one hand (already splitted)
			if(player.getNHands() > 1) {
				print_index = i;
				hand_index =  " [" + (i+1) + "] ";
				if (i == 0)
					System.out.println("playing 1st hand...");
				else if (i == 1)
					System.out.println("playing 2nd hand...");
				else if (i == 2)
					System.out.println("playing 3rd hand...");
				else
					System.out.println("playing 4th hand...");
				player.printPlayersHand(print_index);
			}

			
			while(true) {
				line = mode.getCommand(); // get command from console or file
				char cmd = getCommand(line);
				if(cmd == 'h') {
					if (player.hands.get(i).isBust() || player.hands.get(i).isStanding() || player.hands.get(i).isSurrender() || (player.hands.get(i).isDouble() && (player.hands.get(i).getNCards() >= 3)) || (player.hands.get(i).isSplit() && (player.hands.get(i).getNCards() >= 2) && (player.hands.get(i).cards.get(0).getValue().equals("A"))))
						System.out.println(line + ": illegal command");
					else
						player.hit(i);
				}
				else if(cmd == 's') {
					player.stand(i);
				} 
				else if(cmd == 'i') { // TODO
					if (player.hands.get(i).isOpening())
						System.out.println("player is insuring");
					else
						System.out.println(line + ": illegal command");
				}
				else if(cmd == 'u') {
					if (player.hands.get(i).getNCards()==2)
						player.surrender(i);
					else
						System.out.println(line + ": illegal command");
				}
				else if(cmd == 'p') {
					if (player.hands.get(i).isPair() && (player.getNHands() <= 3))
						player.setIsSplitting(true);
					else
						System.out.println(line + ": illegal command");
				}
				else if(cmd == '2') {
					if (player.hands.get(i).getNCards() == 2 && player.hands.get(i).getValue()>8 && player.hands.get(i).getValue()<12)
						player.doubleD(i);
					else
						System.out.println(line + ": illegal command");					
				} 
				else if(cmd == 'a') { //TODO
				} else if(cmd == 't') { //TODO
				} else if(cmd == '$') {
					System.out.println("Player's current balance is " + player.getBalance());
				} else if(cmd == 'q') {
					System.out.println("bye");
					System.exit(0);
				} else
					continue;
				
		
				if(player.isHittingHand(i)) { // isto devia ser uma flag do player ou da hand? Se calhar player, mas está na hand
					if(!player.isDoubleDHand(i)) System.out.println("player hits");
					player.addCard(i, dealer.dealCards());
					player.printPlayersHand(print_index);
					player.hands.get(i).setHitting(false); // make this a player method
					if(player.hands.get(i).isBust()) {
						System.out.println("player busts" + hand_index);
						break;
					}
					if(player.isDoubleDHand(i)) {
						player.stand(i); // Not really needed, just for completeness
						break;					
					}
				} else if(player.isStandingHand(i)) {
					System.out.println("player stands" + hand_index);
					break;
				} else if(player.isSurrendingHand(i)) {
					System.out.println("player is surrendering");
					break;
				} else if(player.isSplitting()) {
					System.out.println("player is splitting");
					player.split(i);
					player.addCard(i, dealer.dealCards());
					player.addCard(i+1, dealer.dealCards());
					i--;
					break;
				}
				
//				if(cmd == 'h') {
//					if (player.hands.get(i).isBust() || player.hands.get(i).isStanding() || player.hands.get(i).isSurrender() || (player.hands.get(i).isDouble() && (player.hands.get(i).getNCards() >= 3)) || (player.hands.get(i).isSplit() && (player.hands.get(i).getNCards() >= 2) && (player.hands.get(i).cards.get(0).getValue().equals("A"))))
//						System.out.println(line + ": illegal command");
//					else {
//						System.out.println("player hits");
//						player.hit(i);
//						player.addCard(i, dealer.dealCards());
//						player.printPlayersHand(print_index);
//						if(player.hands.get(i).isBust()) {
//							System.out.println("player busts" + hand_index);
//							break;
//						}						
//					}
//				} 
//				else if(cmd == 's') {
//					player.stand(i);
//					System.out.println("player stands" + hand_index);
//					break;						
//				} 
//				else if(cmd == 'i') {
//					if (player.hands.get(i).isOpening()) {
//						System.out.println("player is insuring");
//					}
//					else {
//						System.out.println(line + ": illegal command");
//					}
//				} 
//				else if(cmd == 'u') {
//					if (player.hands.get(i).getNCards()==2) {
//						player.surrender(i);
//						System.out.println("player is surrendering");
//						break;
//					}
//					else {
//						System.out.println(line + ": illegal command");
//					}
//				} 
//				else if(cmd == 'p') {
//					if (player.hands.get(i).isPair() && (player.getNHands() <= 3)) {
//						player.split(i);
//						System.out.println("player is splitting");
//						player.addCard(i, dealer.dealCards());
//						player.addCard(i+1, dealer.dealCards());
//						i--;
//						break;
//					}
//					else {
//						System.out.println(line + ": illegal command");
//					}
//				} 
//				else if(cmd == '2') {
//					if (player.hands.get(i).getNCards() == 2 && player.hands.get(i).getValue()>8 && player.hands.get(i).getValue()<12) {
//						player.doubleD(i);
//					}
//					else {
//						System.out.println(line + ": illegal command");
//					}						
//				} 
//				else if(cmd == 'a') {
//					
//				} else if(cmd == 't') {
//					
//				} else if(cmd == '$') {
//					System.out.println("Player's current balance is " + player.getBalance());
//				} else if(cmd == 'q') {
//					System.out.println("bye");
//					System.exit(0);
//				} else
//					System.out.println(line + ": illegal command");
			}
		}
		// Dealer's turn
		dealer.setVisible();
		while(true) {
			dealer.printDealersHand();
			int dHandValue = dealer.hand.getValue();
			if(dHandValue > 21) {
				System.out.println("dealer busts");
				break;
			}
			if(dHandValue < 17) {
				dealer.hit();
				System.out.println("dealer hits");
			} else {
				dealer.stand();
				System.out.println("dealer stands");
				break;
			}
		}
		
		if (dealer.hand.checkBlackjack())
			System.out.println("blackjack!!");
		else
			for(int i = 0; i < player.getNHands(); ++i)
				if (player.hands.get(i).checkBlackjack()) {
					System.out.println("blackjack!!");
					break;
				}

			
		for(int i = 0; i < player.getNHands(); ++i) {
			float mult = 1;
			int bet =  player.hands.get(i).getBet();
			String res_str = new String();

			if(player.hands.get(i).isSurrender()) {
				player.updateBalance(0.5f*bet);
				System.out.println("Player's current balance is " + player.getBalance());
			}
			else {
				int res = result(i);
				if(res != 0) {
					if(res == -1) {
						mult = 0;
						res_str += "loses";
					} else {
						boolean splited_blackjack = (player.hands.get(i).isSplit() && 
								player.hands.get(i).cards.get(0).getValue().equals("A"));
						mult = (player.hands.get(i).checkBlackjack() && !(splited_blackjack) )  ? 2.5f : 2;
						res_str += "wins";
					}
				} else
					res_str += "pushes";
				
				player.updateBalance(mult*bet);
				
				hand_index = "";
				if(player.getNHands() > 1)
					hand_index = "[" + (i+1) + "]";
				
				System.out.println("Player " + res_str + " " + hand_index + " and his current balance is " + player.getBalance());
			}
		}
		System.out.println();
		
	}

	public int result(int i) { // 1 - player wins, 0 - player pushes, -1 - player loses
		if(player.hands.get(i).isBust()) return -1;
		if(dealer.hand.isBust()) return 1;
		if (player.hands.get(i).checkBlackjack())
			return dealer.hand.checkBlackjack() ? 0 : 1;
		else if (dealer.hand.checkBlackjack())
			return -1;
		int playerScore = player.hands.get(i).getValue();
		int dealerScore = dealer.hand.getValue();
		if (playerScore < dealerScore)
			return -1;
		else if (playerScore > dealerScore)
			return 1;
		else
			return 0;
	}

	public static void main(String args[]){
		char Mode_in = args[0].charAt(1);
		Game game = null;
		if ((Mode_in) == 'i') {
			game = new Game(Mode_in, Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), -1, null);
		} 
		else if ((Mode_in) == 'd') {
			game = new Game(Mode_in, Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4], args[5]); 
		}
		else if ((Mode_in) == 's') {
			game = new Game(Mode_in, Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7]), args[8]);
		}
		else {
			System.out.println("Incorrect arguments");
			System.exit(0);			
		}
		while(true) {
			game.play();
		}
	}
	

}
