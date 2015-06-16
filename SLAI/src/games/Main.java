package games;

public class Main {
	public void play(String game)
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
