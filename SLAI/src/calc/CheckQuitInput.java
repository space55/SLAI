package calc;

import main.Quit;

public class CheckQuitInput {
	public void run(String input)
	{
		Quit quit = new Quit();
		if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("close"))
		{
			quit.run();
		}
	}
}
