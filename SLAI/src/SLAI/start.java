package SLAI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class start
{

	static ArrayList yes = new ArrayList();
	static ArrayList no = new ArrayList();

	static boolean verbose = false;
	static boolean timestamp = true;
	static boolean experimental = false;
	static boolean logfile = true;

	public static void main(String[] args) throws IOException
	{
		for (int argscounter = 0; argscounter < args.length; argscounter++)
		{
			if (args[argscounter].equalsIgnoreCase("v"))
			{
				verbose = true;
			}

			if (args[argscounter].equalsIgnoreCase("tlog"))
			{
				timestamp = false;
			}

			if (args[argscounter].equalsIgnoreCase("exp"))
			{
				experimental = true;
			}
			
			if (args[argscounter].equalsIgnoreCase("logf"))
			{
				logfile = false;
			}
		}
		
		Logger.init(verbose, timestamp, logfile);

		Logger.write("Verbose mode Initialized");
		Logger.write("Timestamps Initialized");

		Scanner in = new Scanner(System.in);

		System.out.println("Would you like to play a game?");
		String game = in.nextLine();
		game.toLowerCase();

		yesNoQuestion(game, in);

		System.out.println("What would you like to play?");
		String pickGameInput = in.nextLine();
		checkIfQuit(pickGameInput);

		boolean allFalse = true;

		boolean pongBool = checkGame("pong", pickGameInput);

		String pacManNames[] =
		{ "pacman", "pac man", "packman", "pack man", };

		boolean pacManBool = false;
		for (int counter = 0; counter < pacManNames.length; counter++)
		{
			pacManBool = checkGame(pacManNames[counter], pickGameInput);
		}

		boolean breakoutBool = checkGame("breakout", pickGameInput);

		if (pongBool)
		{
			allFalse = false;
			Logger.write("pongBool true; going to play pong");
			pong();
		}
		else if (pacManBool)
		{
			allFalse = false;
			Logger.write("pacManBool true; going to play PacMan");
			pacMan();
		}
		else if (breakoutBool)
		{
			allFalse = false;
			Logger.write("breakoutBool true; going to play breakout");
			breakout();
		}

		if (allFalse)
		{
			Logger.write("allFalse true; invalid input");
			System.out.println("Sorry, but I didn't quite understand what you meant to say. Perhaps you wanted to just chat?");
		}
	}

	public static void answerNo(boolean answer)
	{
		Logger.write("Checking if answer should cause quit");
		if (!answer)
		{
			System.out.println("All right, see you later!");
			Logger.write("Answer causes quit");
			System.exit(1000);
		}
		Logger.write("Answer not causing quit");
	}

	public static boolean checkGame(String gameName, String input)
	{
		Logger.write("Starting checkGame");
		boolean gameRun = false;
		if (input.indexOf(gameName) != -1)
		{
			gameRun = true;
		}
		Logger.write("Completed checkGame");
		return gameRun;
	}

	public static void pong()
	{
		System.out.println("A bit dissappointing, huh?");
		System.exit(1000);
	}

	public static void pacMan()
	{
		System.out.println("A bit dissappointing, huh?");
		System.exit(1000);
	}

	public static void breakout()
	{
		System.out.println("A bit dissappointing, huh?");
		System.exit(1000);
	}

	public static void yesNoQuestion(String input, Scanner in) throws IOException
	{
		//0=false, 1=true, 2=ask, 3=invalid, 4=completed
		Logger.write("Starting yesNoQuestion");
		input.toLowerCase();
		boolean gameYes = false;
		int type = 0;
		if (input.charAt(0) == '1' || input.charAt(0) == '0' || input.charAt(0) == '#' || input.charAt(0) == '\"' || input.charAt(0) == '\\')
		{
			System.out.println("Answer uses protected characters. Assuming you meant yes.");
			gameYes = true;
		}
		if (!gameYes)
		{
			type = serverCommsInterface.sCI(input, 2);
			Logger.write("Type: " + type);
		}
		if (type == 2)
		{
			Logger.write("(yesNoQuestion) server=2");
			System.out.println("My apologies, but I didn't understand your response. Would your response count as a \"Yes\" answer, a \"No\" answer, or a mess up?");
			System.out.println("Please answer with \"Y\" for \"Yes\", \"N\" for \"No\", or hit enter if it was a mess up.");
			String messup = in.nextLine();
			if (messup.equalsIgnoreCase("Y"))
			{
				type = serverCommsInterface.sCI(input, 1);
				gameYes = true;
				Logger.write("answer set to yes & added to DB");
			}
			else if (messup.equalsIgnoreCase("N"))
			{
				type = serverCommsInterface.sCI(input, 0);
				gameYes = false;
				Logger.write("answer set to no & added to DB");
			}
			else
			{
				Logger.write("Messup");
			}
		}
		else if (type == 1)
		{
			gameYes = true;
		}
		else if (type == 3)
		{
			Logger.write("Invalid input; assuming yes");
			gameYes = true;
		}
		answerNo(gameYes);
		Logger.write("Completed yesNoQuestion");
	}

	public static String time()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	public static void checkIfQuit(String input)
	{
		if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("close"))
		{
			answerNo(true);
		}
	}
}
