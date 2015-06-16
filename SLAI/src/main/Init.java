package main;

public class Init {
	public static void start(String[] args)
	{
		for (int argscounter = 0; argscounter < args.length; argscounter++)
		{
			if (args[argscounter].equalsIgnoreCase("v"))
			{
				Start.verbose = true;
			}

			if (args[argscounter].equalsIgnoreCase("tlog"))
			{
				Start.timestamp = false;
			}

			if (args[argscounter].equalsIgnoreCase("exp"))
			{
				Start.experimental = true;
			}
			
			if (args[argscounter].equalsIgnoreCase("logf"))
			{
				Start.logfile = false;
			}
		}
	}
}
