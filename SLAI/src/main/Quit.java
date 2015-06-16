package main;

import backend.Logger;

public class Quit {
	public void run()
	{
		comInterface.Communication com = new comInterface.Communication();
		com.write("All right, see you later!");
		Logger.write("Answer causes quit");
		System.exit(1000);
	}
}
