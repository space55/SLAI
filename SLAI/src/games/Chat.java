package games;

import java.io.IOException;
import java.util.Random;

import backend.Logger;
import calc.YNAnswer;
import comInterface.Communication;
import data.DataStorage;

public class Chat
{
	public static void run() throws IOException
	{
		Logger.write("Chat class called");
		Logger.write("Instantiating objects");
		Communication com = new Communication();
		YNAnswer yn = new YNAnswer();
		com.write("Hello there! Mind if we have a bit of a chat?");
		String askChat = com.read();
		Logger.write("Checking if asking for chat was yes answer");
		boolean askChatBool = yn.check(askChat);
		if (askChatBool)
		{
			Logger.write("Chatting");
			chat();
		}
	}

	public static void chat()
	{
		Logger.write("Instantiating chat objects");
		Communication com = new Communication();
		DataStorage data = new DataStorage();
		Logger.write("Generating random");
		Random rand = new Random();
		int pickTopic = rand.nextInt(5);
		Logger.write("Picking topic");
		if (pickTopic == 0)
		{
			com.write("What's the weather like? Stormy? Clear?");
			String weather = com.read();
			if (weather.indexOf("storm") != -1)
			{
				Logger.write("Weather stormy, storing in local memory");
				data.store("weather", "storm");
			}
			else if (weather.indexOf("clear") != -1 || weather.indexOf("sun") != -1)
			{
				Logger.write("Weather clear, storing in local memory");
				data.store("weather", "clear");
			}
			else if (weather.indexOf("cloud") != -1 || weather.indexOf("overcast") != -1)
			{
				Logger.write("Weather cloudy, storing in local memory");
				data.store("weather", "cloudy");
			}

		}
	}
}
