package games;

import java.io.IOException;

public class Main {
	public void play(String game) throws IOException
	{
		if (game.equals("programGuessBirthday"))
		{
			ProgramGuessBirthday.run();
		}
		if (game.equals("basicInfo"))
		{
			BasicInfo.run();
		}
		if (game.equals("chat"))
		{
			Chat.run();
		}
	}
}
