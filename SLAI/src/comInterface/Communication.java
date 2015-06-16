package comInterface;

import java.util.Scanner;

import calc.CheckQuitInput;

public class Communication {
	public void write(String message)
	{
		System.out.println(message);
	}
	
	public String read()
	{
		CheckQuitInput checkQuit = new CheckQuitInput();
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String ret = in.next();
		ret.toLowerCase();
		checkQuit.run(ret);
		return ret;
	}
}
