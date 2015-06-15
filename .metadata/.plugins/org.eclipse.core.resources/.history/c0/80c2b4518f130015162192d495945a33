import java.io.IOException;
import java.net.ServerSocket;

public class server
{
	private static final int port = 1635;

	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = null;
		boolean listening = true;

		for (int argscounter = 0; argscounter < args.length; argscounter++)
		{
			if (args[argscounter].equalsIgnoreCase("v"))
			{
				// Logger.verbose = true;
			}

			if (args[argscounter].equalsIgnoreCase("tlog"))
			{
				// Logger.timestamp = true;
			}
			
			if (args[argscounter].equalsIgnoreCase("logf"))
			{
				// Logger.logfile = true;
			}
		}
		Logger.write("Completed args reading");

		try
		{
			serverSocket = new ServerSocket(port);
		}
		catch (IOException e)
		{
			System.err.println("Could not listen on port: " + port + ".");
			System.exit(-1);
		}

		while (listening)
			new serverThread(serverSocket.accept()).start();

		serverSocket.close();
	}
}
