package calc;

import java.io.IOException;

import backend.Logger;
import backend.ServerCommsInterface;

import comInterface.Communication;

public class YNAnswer {
	public boolean checkFinal(String input) throws IOException
	{
		checkQuitAnswer checkQuit = new checkQuitAnswer();
		checkQuit.run(check(input));
		return true;
	}
	public boolean check(String input) throws IOException
	{
		Logger.write("Starting yesNoQuestion");
		Communication com = new Communication();
		//0=false, 1=true, 2=ask, 3=invalid, 4=completed
		input.toLowerCase();
		boolean answer = false;
		int type = 0;
		if (input.charAt(0) == '1' || input.charAt(0) == '0' || input.charAt(0) == '#' || input.charAt(0) == '\"' || input.charAt(0) == '\\')
		{
			com.write("Your answer uses protected characters. I'm just going to assume you meant yes.");
			answer = true;
		}
		if (!answer)
		{
			type = ServerCommsInterface.sCI(input, 2);
			Logger.write("Type: " + type);
		}
		if (type == 2)
		{
			Logger.write("(yesNoQuestion) server=2");
			com.write("My apologies, but I didn't understand your response. Would your response count as a \"Yes\" answer, a \"No\" answer, or a mess up?");
			com.write("Please answer with \"Y\" for \"Yes\", \"N\" for \"No\", or hit enter if it was a mess up.");
			String debugAnswer = com.read();
			if (debugAnswer.equalsIgnoreCase("Y"))
			{
				type = ServerCommsInterface.sCI(input, 1);
				answer = true;
				Logger.write("answer set to yes & added to DB");
			}
			else if (debugAnswer.equalsIgnoreCase("N"))
			{
				type = ServerCommsInterface.sCI(input, 0);
				answer = false;
				Logger.write("answer set to no & added to DB");
			}
			else
			{
				Logger.write("Messup");
			}
		}
		else if (type == 1)
		{
			answer = true;
		}
		else if (type == 3)
		{
			Logger.write("Invalid input; assuming yes");
			answer = true;
		}
		//answerNo(gameYes);
		Logger.write("Completed yesNoQuestion");
		return answer;
	}
}
