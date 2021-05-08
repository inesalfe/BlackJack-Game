package blackjack;

interface GameMode {
		
	public String getCommand();
	
	public static void main(String args[]){
		GameMode inter = new Interative();
		System.out.println(inter.getCommand());
		GameMode debug = new Debug("shoe-file.txt", "cmd-file.txt");
		System.out.println(debug.getCommand());
	}

}
