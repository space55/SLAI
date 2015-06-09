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

	public static int sCI(String word, int newText) throws IOException
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

			String servOut = "";
			if (newText == 1)
			{
				servOut += "*";
			}
			else if (newText == 0)
			{
				servOut += "^";
			}
			else if (newText == 2)
			{
				servOut += "@";
			}
			
			servOut += word;
			
			out.println(servOut);
			
			//Protocol: (YN)\(VAL)\(TEXT)
			//Example: Y\3\Sure

			while ((fromServer = in.readLine()) != null)
			{
				Logger.write("FromServer: " + fromServer);
				int val = 0;
				int valStart = fromServer.indexOf("\\", 2);
				try
				{
					val = Integer.parseInt(fromServer.substring(2, valStart));
				}
				catch (Exception e)
				{
					Logger.write("Response is invalid");
				}
				if (fromServer.charAt(0) == 'Y')
				{
					if (start.experimental)
					{
						ret = 1;
					}
					
					else if (val < 15)
					{
						ret = 2;
					}
					
					else
					{
						ret = 1;
					}
				}

				if (fromServer.charAt(0) == 'N')
				{
					if (start.experimental)
					{
						ret = 0;
					}
					
					else if (val < 15)
					{
						ret = 2;
					}
					
					else
					{
						ret = 0;
					}
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