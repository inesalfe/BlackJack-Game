package blackjack;

interface GameMode {
		
	public String getBetCommand();
	public String getPlayCommand();
	public String getPlayCommand(int nHands, PlayerHand p_hand, Hand d_hand, int bet);
	
//	public static void main(String args[]){
//		GameMode inter = new Interative();
//		System.out.println(inter.getCommand());
//		GameMode debug = new Debug("cmd-file.txt");
//		while(true)
//			System.out.println(debug.getCommand());
//	}

}
