package games;

import java.io.IOException;

import backend.Logger;
import calc.YNAnswer;
import comInterface.Communication;
import data.DataStorage;

public class BasicInfo
{
	static Communication com = new Communication();

	public static void run() throws IOException
	{
		DataStorage data = new DataStorage();
		com.write("Hello!");
		com.write("Can I start by asking your name?");
		Logger.write("Storing name");
		data.store("name", com.read());
		checkJ(data.get("name"));
		com.write("Then hello there, " + data.get("name") + "!");
		com.write(data.get("name") + ", can I please ask your age?");
		Logger.write("Storing age");
		data.store("age", com.read());
		com.write(data.get("age") + " is a wonderful age, I must say.");
	}

	public static void checkJ(String name) throws IOException
	{
		Logger.write("Checking if packers joke should be used");
		if (name.indexOf("Jonah") != -1)
		{
			YNAnswer yn = new YNAnswer();
			com.write("Is your last name \"Wolmark\"?");
			if (yn.check(com.read()))
			{
				com.write("So, how about 'dem packers?");
			}
		}
	}
}
