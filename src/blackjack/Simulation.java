package blackjack;

public class Simulation implements GameMode {

	int sNumber;
	String strategy;
	
	public Simulation (int sNumber_in, String strategy_in) {
		sNumber = sNumber_in;
		strategy = strategy_in;
	}
	
	public String getCommand() {
		return "oioioi";
	}
	
	public static void main(String args[]){
		GameMode inter = new Simulation(1, "oi");
		System.out.println(inter.getCommand());
	}
	
}