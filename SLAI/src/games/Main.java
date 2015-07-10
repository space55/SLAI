package games;

import java.io.IOException;

import backend.Logger;

public class Main
{
	public void play(String game) throws IOException
	{
		if (game.equals("programGuessBirthday"))
		{
			Logger.write("Running programGuessBirthday");
			ProgramGuessBirthday.run();
		}
		if (game.equals("basicInfo"))
		{
			Logger.write("Running basicInfo");
			BasicInfo.run();
		}
		if (game.equals("chat"))
		{
			Logger.write("running chat");
			Chat.run();
		}
	}
}
