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

	static boolean verbose;
	static boolean timestamp;

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
				timestamp = true;
			}
		}

		log("Verbose mode Initialized");
		log("Timestamps Initialized");

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
			log("pongBool true; going to play pong");
			pong();
		}
		else if (pacManBool)
		{
			allFalse = false;
			log("pacManBool true; going to play PacMan");
			pacMan();
		}
		else if (breakoutBool)
		{
			allFalse = false;
			log("breakoutBool true; going to play breakout");
			breakout();
		}

		if (allFalse)
		{
			log("allFalse true; invalid input");
			System.out.println("Sorry, but I didn't quite understand what you meant to say. Perhaps you wanted to just chat?");
		}
	}

	public static void answerNo(boolean answer)
	{
		log("Checking if answer should cause quit");
		if (!answer)
		{
			System.out.println("All right, see you later!");
			log("Answer causes quit");
			System.exit(1000);
		}
		log("Answer not causing quit");
	}

	public static boolean checkGame(String gameName, String input)
	{
		log("Starting checkGame");
		boolean gameRunFinal = true;
		for (int counter = 0; counter < input.length(); counter++)
		{
			if (input.charAt(counter) == gameName.charAt(counter))
			{
				boolean gameRun[] = new boolean[gameName.length()];
				for (int counter2 = 0; counter2 < gameName.length(); counter2++)
				{
					if (input.charAt(counter2 + counter) == gameName.charAt(counter2))
					{
						gameRun[counter] = true;
					}
				}
				for (int counter2 = 0; counter < gameName.length(); counter2++)
				{
					if (!gameRun[counter2])
					{
						gameRunFinal = false;
					}
				}
			}
		}
		log("Completed checkGame");
		return gameRunFinal;
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
		log("Starting yesNoQuestion");
		boolean gameYes = false;
		int type = 0;
		if (input.charAt(0) == '1' || input.charAt(0) == '0')
		{
			System.out.println("Answer uses protected characters. Assuming you meant yes.");
			gameYes = true;
		}
		if (!gameYes)
		{
			type = serverCommsInterface.sCI(input);
		}
		if (type == 2)
		{
			log("(yesNoQuestion) server=2");
			System.out.println("My apologies, but I didn't understand your response. Would your response count as a \"Yes\" answer, a \"No\" answer, or a mess up?");
			System.out.println("Please answer with \"Y\" for \"Yes\", \"N\" for \"No\", or hit enter if it was a mess up.");
			String messup = in.nextLine();
			if (messup.equalsIgnoreCase("Y"))
			{
				type = serverCommsInterface.sCI("1" + input);
				gameYes = true;
				log("answer set to yes & added to DB");
			}
			if (messup.equalsIgnoreCase("N"))
			{
				type = serverCommsInterface.sCI("0" + input);
				gameYes = false;
				log("answer set to no & added to DB");
			}
			else
			{
				log("SERVER IS BROKEN, FIX IT BEFORE IT EXPLODES");
			}
		}
		if (type == 1)
		{
			gameYes = true;
		}
		answerNo(gameYes);
		log("Completed yesNoQuestion");
	}

	public static String time()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	public static void log(String msg)
	{
		if (verbose)
		{
			if (timestamp)
			{
				System.out.println("[" + time() + "] " + msg);
			}
			else
			{
				System.out.println(msg);
			}
		}
	}

	public static void checkIfQuit(String input)
	{
		if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("close"))
		{
			answerNo(true);
		}
	}
}
