package calc;

import backend.Logger;

import main.Quit;

public class checkQuitAnswer {	
	public void run(boolean answer)
	{
		Quit quit = new Quit();
		Logger.write("Checking if answer should cause quit");
		if (!answer)
		{
			quit.run();
		}
		Logger.write("Answer not causing quit");
	}
}
