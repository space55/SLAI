package SLAI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class serverCommsInterface
{
	private static final int port = 1635;

	public static int sCI(String word) throws IOException
	{
		int ret = 0;

		/*
		 * if (args.length != 1)
		 * {
		 * System.err.println("Usage: java kkClient <host name> <port number>");
		 * System.exit(1);
		 * }
		 */

		String hostName = "localhost";
		int portNumber = port;

		try (Socket clSocket = new Socket(hostName, portNumber); PrintWriter out = new PrintWriter(clSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream()));)
		{
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;

			if (word.equals("3"))
			{
				out.println(word);
			}

			while ((fromServer = in.readLine()) != null)
			{
				if (fromServer.equals("1"))
				{
					ret = 1;
				}

				if (fromServer.equals("2"))
				{
					ret = 2;
				}

				if (word != "1" || word != "2" || word != "3")
				{
					out.println(word);
				}
			}
		}
		catch (UnknownHostException e)
		{
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
		return ret;
	}
}