package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Debug implements GameMode {

	ArrayList<String> cmds;
	
	String shoeFile;
	String cmdFile;
	
	public Debug (String shoeFile_in, String cmdFile_in) {
		shoeFile = shoeFile_in;
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
			e.printStackTrace();
		}		
		
		for (int i = 0; i < cmds.size(); i++) {
			if (cmds.get(i).equals("b") && (isNumeric(cmds.get(i+1)))) {
				cmds.add(i+1, cmds.get(i) + " " + cmds.get(i+1));
				cmds.remove(i);
				cmds.remove(i+1);
			}
		}
	}
	
	public String getCommand() {
		String next_cmd = cmds.get(0);
		cmds.add(next_cmd);
		cmds.remove(0);
		if (next_cmd.equals("h"))
			return "h";
		else if (next_cmd.equals("s"))
			return "s";
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
	    return "\0";
	}

	@Override
	public String toString() {
		String out = new String();
		for (int i = 0; i < cmds.size(); i++) {
			out += cmds.get(i);
			out += '\n';
		}
		return out;
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
	
	public static void main(String args[]){
		GameMode debug = new Debug("shoe-file.txt", "cmd-file.txt");
		System.out.println(debug.toString());
	}
	
}