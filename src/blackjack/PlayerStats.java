package blackjack;

public class PlayerStats extends Stats {
	private int wins;
	private int losses;
	private int pushes;
	private int initBalance;

	public PlayerStats(int init_balance) {
		super();
		initBalance = init_balance;
		wins = 0;
		losses = 0;
		pushes = 0;
	}
	
	public void incWLP(int r) {
		if(r == 1) ++wins;
		if(r == -1) ++losses;
		if(r == 0) ++pushes;
	}

	public double getWLPavg(int r) {
		if(r == 1) return (double)wins/handsPlayed;
		if(r == -1) return (double)losses/handsPlayed;
		if(r == 0) return (double)pushes/handsPlayed;
		return 0;
	}
	
	public double percentageOfGain(double cur_balance) {
		return cur_balance/initBalance;
	}

}