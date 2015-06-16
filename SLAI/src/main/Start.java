package main;

import java.io.IOException;

import backend.Logger;

import calc.YNAnswer;
import comInterface.Communication;
import games.Main;

public class Start
{
	public static boolean verbose = false;
	public static boolean timestamp = true;
	public static boolean experimental = false;
	public static boolean logfile = true;

	public static void main(String[] args) throws IOException
	{
		Init.start(args);
		
		YNAnswer yn = new YNAnswer();
		Communication com = new Communication();
		Main gameBot = new Main();
		
		Logger.init(verbose, timestamp, logfile);

		Logger.write("Verbose mode Initialized");
		Logger.write("Timestamps Initialized");

		com.write("Would you like to play a game?");
		String game = com.read();
		boolean playGame = yn.check(game);
		
		if (playGame)
		{
			gameBot.play("basicInfo");
		}
		else
		{
			com.write("Then would you like to just chat?");
			if(yn.checkFinal(com.read()))
			{
				gameBot.play("chat");
			}
		}
	}
}
