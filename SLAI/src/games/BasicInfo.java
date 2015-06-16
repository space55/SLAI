package games;

import comInterface.Communication;

import data.DataStorage;

public class BasicInfo {
	public static void run()
	{
		DataStorage data = new DataStorage();
		Communication com = new Communication();
		com.write("Hello!");
		com.write("Can I start by asking your name?");
		data.store("name", com.read());
		com.write("Then hello there, " + data.get("name") + "!");
		com.write(data.get("name") + ", can I please ask your age?");
		data.store("age", com.read());
		com.write(data.get("age") + " is a wonderful age, I must say.");
	}
}
