import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class serverThread extends Thread
{
	private Socket socket = null;

	public serverThread(Socket socket)
	{
		super("MultiServerThread");
		this.socket = socket;
	}

	public void run()
	{
		try
		{
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine;
			String outputLine;
			serverProtocol sP = new serverProtocol();
			outputLine = sP.processInput("");
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null)
			{
				outputLine = sP.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("Bye"))
					break;
			}
			out.close();
			in.close();
			socket.close();
		}
		catch (IOException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}