package blackjack;

public class Simulation implements GameMode {

	private final int sNumber;
	private int currSNumber;
	PlayerStrategy play_strat;
	BettingStrategy bet_strat;
	
	public Simulation (int sNumber_in, PlayerStrategy play_s, BettingStrategy bet_s) {
		sNumber = sNumber_in;
		currSNumber = -1;
		play_strat = play_s;
		bet_strat = bet_s;
	}
	
	@Override
	public String getPlayCommand(int nHands, PlayerHand p_hand, Hand d_hand, int bet) {
		String cmd;
		if(currSNumber > sNumber)
			 cmd = "q";
		else
			cmd = play_strat.getNextPlay(nHands, p_hand, d_hand, bet);
		System.out.println("# " + cmd);
		return cmd;
	}

	@Override
	public String getBetCommand() {
		String cmd;
		if(currSNumber > sNumber)
			 cmd = "q";
		else
			cmd = "b " + bet_strat.getNextBet();
		System.out.println("# " + cmd);
		return cmd;
	}
	
	public void incCurrSNumber() {
		++currSNumber;
	}
	

	@Override
	public String getPlayCommand() {
		return null;
	}
	
//	public static void main(String args[]){
//		GameMode inter = new Simulation(1, "oi");
//		System.out.println(inter.getCommand());
//	}
	
}