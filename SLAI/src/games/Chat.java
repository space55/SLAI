package games;

import java.io.IOException;
import java.util.Random;

import calc.YNAnswer;
import comInterface.Communication;
import data.DataStorage;

public class Chat {
	public static void run() throws IOException
	{
		Communication com = new Communication();
		YNAnswer yn = new YNAnswer();
		com.write("Hello there! Mind if we have a bit of a chat?");
		String askChat = com.read();
		boolean askChatBool = yn.check(askChat);
		if (askChatBool)
		{
			chat();
		}
	}
	
	public static void chat()
	{
		Communication com = new Communication();
		DataStorage data = new DataStorage();
		Random rand = new Random();
		int pickTopic = rand.nextInt(5);
		if (pickTopic == 0)
		{
			com.write("What's the weather like? Stormy? Clear?");
			String weather = com.read();
			if (weather.indexOf("storm") != -1)
			{
				data.store("weather", "storm");
			}
			else if (weather.indexOf("clear") != -1 || weather.indexOf("sun") != -1)
			{
				data.store("weather", "clear");
			}
			else if (weather.indexOf("cloud") != -1 || weather.indexOf("overcast") != -1)
			{
				data.store("weather", "cloudy");
			}
			
		}
	}
}
