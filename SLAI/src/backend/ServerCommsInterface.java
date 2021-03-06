package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Start;

public class ServerCommsInterface
{
	private static final int port = 1635;
	private static final String HostName = "localhost";

	public static int sCI(String word, int newText) throws IOException
	{
		int ret = 0;

		String hostName = HostName;
		int portNumber = port;

		try (Socket clSocket = new Socket(hostName, portNumber); PrintWriter out = new PrintWriter(clSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(new InputStreamReader(clSocket.getInputStream()));)
		{
			String fromServer;

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
			fromServer = in.readLine();
			//while ((fromServer = in.readLine()) != null)
			//{
			Logger.write("FromServer: " + fromServer);
			if (fromServer.equals("%"))
			{
				ret = 2;
			}
			else if (fromServer.equals("$"))
			{
				ret = 4;
				Logger.write("Update completed");
			}
			else
			{
				int val = 0;
				int valStart = fromServer.indexOf(92, 2);
				try
				{
					val = Integer.parseInt(fromServer.substring(2, valStart));
				}
				catch (Exception e)
				{
					Logger.write("Response is invalid");
				}
				if (fromServer.charAt(0) == '1')
				{
					if (Start.experimental)
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

				if (fromServer.charAt(0) == '0')
				{
					if (Start.experimental)
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
			//}
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