package blackjack;

import java.util.Scanner;

/** Class relative to the selection of the interactive game mode
 * 
 * @param kb Selection of game mode
 * 
 */
public class Interative implements GameMode {

	private static Scanner kb;
	
	public Interative () {
		kb = new Scanner(System.in);
	}
	
	/** Gets the playable command
	 * 
	 * @return <h> Hit
	 * @return <s> Stand
	 * @return <d> Deal
	 * @return <i> Insurance
	 * @return <b> Bet
	 * @return <u> Surrender
	 * @return <p> Split
	 * @return <2> Double
	 * @return <ad> advice
	 * @return <st> statistics
	 * @return <$> Balance
	 * @return <q> Quit
	 * 
	 */
	@Override
	public String getPlayCommand() {
		String line = kb.nextLine();
		if (line.length() <= 2) {
			if (line.equals("h"))
				return "h";
			else if (line.equals("s"))
				return "s";
			else if (line.equals("d"))
				return "d";
			else if (line.equals("b"))
				return "b";
			else if (line.equals("i"))
				return "i";
			else if (line.equals("u"))
				return "u";
			else if (line.equals("p"))
				return "p";
			else if (line.equals("2"))
				return "2";
			else if (line.equals("ad"))
				return "a";
			else if (line.equals("st"))
				return "t";
			else if (line.equals("$"))
				return "$";
			else if (line.equals("q"))
				return "q";
		}
		else if ((line.charAt(0) == 'b') && (line.charAt(1) == ' '))  {
			if (isNumeric(line.substring(2)) == true) {
				return line;					
			}
		}
		System.out.println(line + ": illegal command");
		return "";
	}

	/** Checks if it is reading a number.
	 * 
	 * @param strNum String.
	 * @return true If the string is a number.
	 * @return false If the string is not a number.
	 * 
	 */
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}

	/** Gets valid command relative to bet
	 */
	@Override
	public String getBetCommand() {
		return getPlayCommand();
	}

	/** Gets valid playable command
	 */
	@Override
	public String getPlayCommand(int nHands, PlayerHand p_hand, Hand d_hand, int bet) {
		return getPlayCommand();
	}
	
//	public static void main(String args[]) {
//		GameMode inter = new Interative();
//		System.out.println(inter.getPlayCommand());
//	}
	
}
