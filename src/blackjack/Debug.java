package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/** Class relative to the selection of game mode 
 * 
 * @param cmds List of commands
 * @param cmdFile Name of the file with the commands
 * 
 */
public class Debug implements GameMode {

	ArrayList<String> cmds;
	String cmdFile;
	
	
	/** Debug Mode.
	 * 
	 * @param cmdFile_in Name of the file with the commands.
	 * 
	 */
	public Debug (String cmdFile_in) {
		
		cmdFile = cmdFile_in;		
		cmds = new ArrayList<String>();
		
		Scanner scanner;
		try {
			scanner = new Scanner(new File(cmdFile));
			String line = new String();
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				cmds.addAll(Arrays.asList(line.split(" ", 0)));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Invalid command file: " + e);
			System.exit(0);
		}		
		
		for (int i = 0; i < cmds.size(); i++) {
			if (cmds.get(i).equals("b") && (isNumeric(cmds.get(i+1)))) {
				cmds.add(i+1, cmds.get(i) + " " + cmds.get(i+1));
				cmds.remove(i);
				cmds.remove(i+1);
			}
		}
	}
	
	/** Gets a valid command.
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
		if(cmds.size() == 0) return "q";
		String next_cmd = cmds.get(0);
		cmds.remove(0);
		System.out.println("# " + next_cmd);
		if (next_cmd.length() <= 2) {
			if (next_cmd.equals("h"))
				return "h";
			else if (next_cmd.equals("s"))
				return "s";
			else if (next_cmd.equals("d"))
				return "d";
			else if (next_cmd.equals("b"))
				return "b";
			else if (next_cmd.equals("i"))
				return "i";
			else if (next_cmd.equals("u"))
				return "u";
			else if (next_cmd.equals("p"))
				return "p";
			else if (next_cmd.equals("2"))
				return "2";
			else if (next_cmd.equals("ad"))
				return "a";
			else if (next_cmd.equals("st"))
				return "t";
			else if (next_cmd.equals("$"))
				return "$";
			else if (next_cmd.equals("q"))
				return "q";
		}
		else if (next_cmd.charAt(0) == 'b')
			return next_cmd;
		
		System.out.println(next_cmd + ": illegal command");
		return "";
	}

	
	/** Converts to string
	 * 
	 * @return out Resulting String
	 * 
	 */
	@Override
	public String toString() {
		String out = new String();
		for (String cmd : cmds) {
			out += cmd;
			out += '\n';
		}
		return out;
	}

	/** Evaluates a string and checks for a number
	 * 
	 * @param strNum String 
	 * @return true If the string contains a number
	 * @return false If the string does not contain a number
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
	
	/** Gets Commands relative to the bet
	 */
	@Override
	public String getBetCommand() {
		return getPlayCommand();
	}

	/** Gets commands relative to play
	 * 
	 * @param p_hand Player's hand
	 * @param d_hand Dealer's hand
	 * @param bet Value of the bet
	 * 
	 */
	@Override
	public String getPlayCommand(int nHands, PlayerHand p_hand, Hand d_hand, int bet) {
		return getPlayCommand();
	}
	
//	public static void main(String args[]){
//		GameMode debug = new Debug("cmd-file.txt");
//		System.out.println(debug);
//	}	
}