package blackjack;

interface GameMode {
		
	public String getCommand();
	
	public static void main(String args[]){
		GameMode inter = new Interative();
		System.out.println(inter.getCommand());
		GameMode debug = new Debug("cmd-file.txt");
		while(true)
			System.out.println(debug.getCommand());
	}

}
