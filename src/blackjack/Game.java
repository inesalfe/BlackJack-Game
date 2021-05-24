package blackjack;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cardCounting.Ace5;
import cardCounting.Basic;
import cardCounting.BettingStrategy;
import cardCounting.HiLo;
import cardCounting.PlayerStrategy;
import cardCounting.StandardStrategy;


/** Class relative to the game.
 * 
 * @param minBet Minimum value for the bet.
 * @param maxBet Maximum value for the bet.
 * @param nDecks Number of decks.
 * @param intShuffle Percentage of the shoe that has been played with.
 * @param isReady Flag that checks if everything is set.
 * @param shuffling Flag that checks if the deck is being shuffled.
 * @param player Represents a regular player.
 * @param dealer Represents the dealer.
 * @param bet_strat Defines the betting strategy.
 * 
*/
public class Game {
	
	private GameMode mode;
	
	private int minBet;
	private int maxBet;
	private int nDecks;
	private int intShuffle;

	private boolean isReady = false;
	private boolean startRound = false;
	private boolean shuffling;
	private boolean noHandsLeft;
	
	private Player player;
	private Dealer dealer;
	
	private Stats dStats;
	private Stats pStats;
	
	private ArrayList<BettingStrategy> bet_strat;
	protected ArrayList<PlayerStrategy> game_strat;
	
	/** Interactive && Simulation Modes.
	 * 
	 * @param Mode_in Selection of the simulation/strategy mode.
	 * @param minBet_in Minimum value for the bet that is allowed.
	 * @param maxBet_in Maximum value for the bet that is allowed.
	 * @param balance_in Current balance.
	 * @param nDecks_in Number of decks in the shoe.
	 * @param intShuffle_in Percentage of the shoe that has been played with.
	 * @param sNumber_in Number of shuffles until the end of the simulation.
	 * @param strategy_in Represents the strategy that will be used.
	 * 
	 */
	public Game(char Mode_in, int minBet_in, int maxBet_in, int balance_in, int nDecks_in, int intShuffle_in, int sNumber_in, String strategy_in) {
		if (Mode_in == 'i') {
			mode = new Interative();
			game_strat = new ArrayList<PlayerStrategy>();
			game_strat.add(new HiLo(maxBet_in, 9, 11, nDecks_in));
			game_strat.add(new Basic(maxBet_in, 9, 11)); 
			bet_strat = new ArrayList<BettingStrategy>();
			bet_strat.add(new StandardStrategy(minBet_in, maxBet_in));
			bet_strat.add(new Ace5(minBet_in, maxBet_in));
		}
		else {
			game_strat = new ArrayList<PlayerStrategy>();
			bet_strat = new ArrayList<BettingStrategy>();
			if (strategy_in.equals("BS")) {
				game_strat.add(new Basic(maxBet_in, 9, 11));
				bet_strat.add(new StandardStrategy(minBet_in, maxBet_in));
			}
			else if (strategy_in.equals("BS-AF")) {
				game_strat.add(new Basic(maxBet_in, 9, 11));
				bet_strat.add(new Ace5(minBet, maxBet));
			}
			else if (strategy_in.equals("HL")) {
				game_strat.add(new HiLo(maxBet_in, 9, 11, nDecks_in));
				bet_strat.add(new StandardStrategy(minBet_in, maxBet_in));
			}
			else if (strategy_in.equals("HL-AF")) {
				game_strat.add(new HiLo(maxBet_in, 9, 11, nDecks_in));
				bet_strat.add(new Ace5(minBet_in, maxBet_in));
			}
			else {
				System.out.println("Invalid betting strategy");
				System.exit(0);				
			}
			mode = new Simulation(sNumber_in, game_strat.get(0), bet_strat.get(0));
		}
		minBet = minBet_in;
		maxBet = maxBet_in;
		nDecks = nDecks_in;
		intShuffle = intShuffle_in;
	
		shuffling = true;
		
		player = new Player(minBet, balance_in);
		dealer = new Dealer(nDecks_in);
		
		dStats = new Stats();
		pStats = new PlayerStats(balance_in);
		
	}

	/** Debug Mode.
	 * 
	 * @param Mode_in Selection of the simulation/strategy mode.
	 * @param minBet_in Minimum value for the bet that is allowed.
	 * @param maxBet_in Maximum value for the bet that is allowed.
	 * @param balance_in Current balance.
	 * @param shoeFile_in Shoe going in the game.
	 * @param cmdFile_in Name of the file with the commands.
	 * 
	 */
	public Game(char Mode_in, int minBet_in, int maxBet_in, int balance_in, String shoeFile_in, String cmdFile_in) {
		mode = new Debug(cmdFile_in);
		
		minBet = minBet_in;
		maxBet = maxBet_in;
	
		shuffling = false;

		player = new Player(minBet, balance_in);
		dealer = new Dealer(shoeFile_in);
		
		dStats = new Stats();
		pStats = new PlayerStats(balance_in);
		
		nDecks = dealer.shoe.getNDecks();
				
		game_strat = new ArrayList<PlayerStrategy>();
		game_strat.add(new Basic(maxBet_in, 9, 11));
		game_strat.add(new HiLo(maxBet_in, 9, 11, nDecks));
		bet_strat = new ArrayList<BettingStrategy>();
		bet_strat.add(new StandardStrategy(minBet, maxBet));
		bet_strat.add(new Ace5(minBet, maxBet));

	}
	
	
	private String getFullAdvice(String cmd) {
		if (cmd.equals("s"))
			return "stand";
		else if (cmd.equals("i"))
 			return "insurance";
 		else if (cmd.equals("u"))
			return "surrender";
		else if (cmd.equals("p"))
			return "split";
		else if (cmd.equals("h"))
			return "hit";
		else if (cmd.equals("2"))
			return "double";
		else
			return "Invalid option";
	}
	
	private void shuffleState() {
		if ((mode instanceof Simulation))
			((Simulation) mode).incCurrSNumber();
		for (int i = 0; i < bet_strat.size(); i++)
			bet_strat.get(i).resetCount();
		if ((game_strat.get(0) instanceof HiLo))
			((HiLo) game_strat.get(0)).resetCounts();
		System.out.println("shuffling the shoe...");
		dealer.shuffle();
	}
	
	private boolean bettingState(int bet) {
		if (bet < minBet || bet > maxBet) {
			System.out.println("b " + bet + ": illegal command");
			return false;
		}
		player.placeBet(bet);
		for (int i = 0; i < bet_strat.size(); i++)
			bet_strat.get(i).setBet(bet);
		System.out.println("player is betting " + bet);
		return true;
	}
	
	private void printStatsState() {
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("BJ P/D \t" + df.format(pStats.getBJavg())+ " / " + df.format(dStats.getBJavg()));
		System.out.println("Win  \t" + df.format(((PlayerStats) pStats).getWLPavg(1)));
		System.out.println("Lose \t" + df.format(((PlayerStats) pStats).getWLPavg(-1)));
		System.out.println("Push \t" + df.format(((PlayerStats) pStats).getWLPavg(0)));
		System.out.println("Balance\t" + player.getBalance() + " / " + 
				df.format(((PlayerStats) pStats).percentageOfGain(player.getBalance())));
	}
	
	public void play() {
				
		while(true) {
			player.clearHands();
			dealer.clearHand();
			/* Shuffle if the number of dealt cards surpassed the intShuffle threshold,
			* unless the program is running on Debug Mode */
			if(!(mode instanceof Debug) && dealer.shoe.getNDealtCards() >= Math.ceil(intShuffle/100.0f * nDecks * 52)) {
				shuffling = true;
			}
			if(shuffling) {
				shuffleState();
				shuffling = false;
			}
			/* Gets a command from a source, either the command line, a file or the simulator 
			 * Valid commands at this stage: b [<value>] and d, in this order;
			 * isReady flags that a "d" command should be issued; no more "b" commands are accepted.*/
			String s = mode.getBetCommand();
			if(s.isBlank()) continue; // Command in a bad format; "\0" was returned;
			String[] toks = s.split(" ", 0);
			if(toks[0].charAt(0) == 'b') {
				if(isReady)
					System.out.println(s + ": illegal command");
				else {
					int bet = (toks.length == 1) ? minBet : Integer.parseInt(toks[1]);
					isReady = bettingState(bet);
					if(mode instanceof Simulation) startRound = true;
				}
			}else if(toks[0].charAt(0) == 'd') {
				if(isReady) {
					startRound = true;
				} else {
					System.out.println(toks[0] + ": illegal command");
				}
			} else if(toks[0].charAt(0) == '$') {
				System.out.println("Player's current balance is " + player.getBalance());
			} else if(toks[0].charAt(0) == 't') {
				printStatsState();
			} else if(toks[0].charAt(0) == 'a') {
				if(isReady)
					System.out.println(s + ": illegal command");
				else {
					System.out.println("Ace5 \t\tbet " + bet_strat.get(1).getNextBet());
					System.out.println("Standard Bet\tbet " + bet_strat.get(0).getNextBet());
				}
			} else if(toks[0].charAt(0) == 'q') {
				System.out.println("bye");
				System.exit(0);				
			} else
				System.out.println(s + ": illegal command");
			
			if(startRound) {
				playRound();
				isReady = false;
				startRound = false;
			}
		}
	}
		
	private void dealState() {
		for(int i = 0; i < 2; ++i) {
			Card c = dealer.dealCards();
			if (i == 0) {
				for (int j = 0; j < bet_strat.size(); j++)
					bet_strat.get(j).updateCount(c);
				if(game_strat.get(0) instanceof HiLo)
					((HiLo) game_strat.get(0)).updateCounts(c);
			}
			dealer.addCard(c);
		}
		dStats.incHandsPlayed();

		for(int i = 0; i < 2; ++i) {
			Card c = dealer.dealCards();
			for (int j = 0; j < bet_strat.size(); j++)
				bet_strat.get(j).updateCount(c);
			if(game_strat.get(0) instanceof HiLo)
				((HiLo) game_strat.get(0)).updateCounts(c);
			player.addCard(0, c);
		}
		pStats.incHandsPlayed();
	}
	
	private void playersTurn() {
		
		String line;
		String hand_index;
		int print_index;
		
		for(int i = 0; i < player.getNHands(); ++i) {
			print_index = -1;
			hand_index = "";
			if(player.hands.get(i).isSplit() && player.hands.get(i).getNCards() == 1) {
				Card c = dealer.dealCards();
				for (int j = 0; j < bet_strat.size(); j++)
					bet_strat.get(j).updateCount(c);
				if(game_strat.get(0) instanceof HiLo)
					((HiLo) game_strat.get(0)).updateCounts(c);
				player.addCard(i, c);
				if(player.hands.get(i).getFirst().getValue().equals("A") && !(c.getValue().equals("A"))) {
					player.stand(i);
				}
			}
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
			
			if(player.isStandingHand(i)) {
				continue;
			}
			
			while(true) {
				if(mode instanceof Simulation)
					line = mode.getPlayCommand(player.getNHands(), player.hands.get(i), dealer.hand, player.getBet());
				else
					line = mode.getPlayCommand(); // get command from console or file
				if(line.isBlank()) continue;
				char cmd = line.charAt(0);
				if(cmd == 'h') {
					if ((player.hands.get(i).isSplit() && (player.hands.get(i).getNCards() == 2) && (player.hands.get(i).getFirst().getValue().equals("A"))))
						System.out.println(line + ": illegal command");
					else
						player.hit(i);
				}
				else if(cmd == 's') {
					player.stand(i);
				} 
				else if(cmd == 'i') {
					if (player.hands.get(i).isOpening() && dealer.hand.getFirst().getValue().equals("A") &&
							!player.isInsuring()) {
						player.insurance();
						System.out.println("player is insuring");
					}
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
				else if(cmd == 'a') { 
					String play = game_strat.get(1).getNextPlay(player.getNHands(), player.hands.get(i), dealer.hand, player.getBet());
					System.out.println("Basic\t\t" + getFullAdvice(play));
					play = game_strat.get(0).getNextPlay(player.getNHands(), player.hands.get(i), dealer.hand, player.getBet());
					System.out.println("HiLo\t\t" + getFullAdvice(play));
				} else if(cmd == 't') { 
					printStatsState();
				} else if(cmd == '$') {
					System.out.println("Player's current balance is " + player.getBalance());
				} else if(cmd == 'q') {
					System.out.println("bye");
					System.exit(0);
				} else
					System.out.println(line + ": illegal command");					
		
				if(player.isHittingHand(i)) {
					if(!player.isDoubleDHand(i)) System.out.println("player hits");
					Card c = dealer.dealCards();
					for (int j = 0; j < bet_strat.size(); j++)
						bet_strat.get(j).updateCount(c);
					if(game_strat.get(0) instanceof HiLo)
						((HiLo) game_strat.get(0)).updateCounts(c);
					player.addCard(i, c);
					player.printPlayersHand(print_index);
					player.hands.get(i).setHitting(false);
					if(player.hands.get(i).isBust()) {
						System.out.println("player busts" + hand_index);
						break;
					}
					if(player.isDoubleDHand(i)) {
						player.stand(i);
						break;					
					}
				} else if(player.isStandingHand(i)) {
					System.out.println("player stands" + hand_index);
					break;
				} else if(player.isSurrendingHand(i)) {
					System.out.println("player is surrendering" + hand_index);
					break;
				} else if(player.isSplitting()) {
					pStats.incHandsPlayed();
					System.out.println("player is splitting");
					player.split(i);
					Card c = dealer.dealCards();
					for (int j = 0; j < bet_strat.size(); j++)
						bet_strat.get(j).updateCount(c);
					if(game_strat.get(0) instanceof HiLo)
						((HiLo) game_strat.get(0)).updateCounts(c);	
					player.addCard(i, c);
					if(player.hands.get(i).getFirst().getValue().equals("A") && !(c.getValue().equals("A")))
						player.stand(i);
					i--;
					break;
				}
			}
		}
	}
	
	private void dealersTurn() {

		dealer.setVisible();
		for (int j = 0; j < bet_strat.size(); j++)
			bet_strat.get(j).updateCount(dealer.hand.cards.get(1));
		if(game_strat.get(0) instanceof HiLo)
			((HiLo) game_strat.get(0)).updateCounts(dealer.hand.getFirst());
		
		while(true) {
			dealer.printDealersHand();
			int dHandValue = dealer.hand.getValue();
			if(dHandValue > 21) {
				System.out.println("dealer busts");
				break;
			}
			if(dHandValue < 17 && !noHandsLeft) {
				Card c = dealer.dealCards();
				for (int j = 0; j < bet_strat.size(); j++)
					bet_strat.get(j).updateCount(c);
				if(game_strat.get(0) instanceof HiLo)
					((HiLo) game_strat.get(0)).updateCounts(c);
				dealer.addCard(c);
				System.out.println("dealer hits");
			} else {
				dealer.hand.setIsStanding(true);
				System.out.println("dealer stands");
				break;
			}
		}
	}
	
	public void playRound() {
		
		dealState();
		
		dealer.printDealersHand();
		player.printPlayersHand(-1);
		
		playersTurn();
		
		noHandsLeft = true;
		for(PlayerHand h : player.hands) {
			if(h.isStanding()) {
				noHandsLeft = false;
				break;
			}
		}

		dealersTurn();
		
		if (dealer.hand.checkBlackjack())
			System.out.println("blackjack!!");
		else
			for(int i = 0; i < player.getNHands(); ++i)
				if (player.hands.get(i).checkBlackjack()) {
					System.out.println("blackjack!!");
					break;
				}
		
		resultsState();
		
	}
	
	private void resultsState() {

		if (dealer.hand.checkBlackjack()) dStats.incBlackjacks();
		for(int i = 0; i < player.getNHands(); ++i) {
			if (player.hands.get(i).checkBlackjack()) pStats.incBlackjacks();
			float mult = 1;
			int res;
			int bet =  player.hands.get(i).getBet();
			String res_str = new String();
			if(player.isInsuring() && dealer.hand.checkBlackjack()) {
				System.out.println("Player wins insurance");
				player.updateBalance(2*bet);
			}
			if(player.hands.get(i).isSurrender()) {
				mult = 0.5f;
				res = -1;
				res_str += "loses";
			}
			else {
				res = result(i);
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
			}
				
			((PlayerStats) pStats).incWLP(res);
			if(bet_strat.get(0) instanceof StandardStrategy)
				((StandardStrategy) bet_strat.get(0)).updateBet(res);
			player.updateBalance(mult*bet);
			
			String hand_index = "";
			if(player.getNHands() > 1)
				hand_index = " [" + (i+1) + "]";
			
			System.out.println("Player " + res_str + hand_index + " and his current balance is " + player.getBalance());
		}
		System.out.println();
	}
	
	/** Calculates the results
	 * 
	 * @param i Flag relative to result
	 * @return 1 - player wins, 0 - player pushes, -1 - player loses
	 * 
	 */
	private int result(int i) { // 1 - player wins, 0 - player pushes, -1 - player loses
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

}


