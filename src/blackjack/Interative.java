package blackjack;

import java.util.Scanner;

public class Interative implements GameMode {

	String cmd;
	private static Scanner kb = new Scanner(System.in);
	
	public Interative () {
		cmd = null;
	}

	public String getCommand() {
		
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
		return "\0";
	}

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static void main(String args[]) {
		GameMode inter = new Interative();
		System.out.println(inter.getCommand());
//		while(true) {
//			String str = inter.getCommand();
//			System.out.println(str);
//			if(str.equals("q"))
//				System.exit(0);
//		}
	}
	
}
