import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger
{
	public static boolean verbose = true;
	public static boolean timestamp = true;
	public static boolean logfile = true;
	
	static PrintWriter out = new PrintWriter(define());
	
	public static BufferedWriter define()
	{
		BufferedWriter bout = null;
		try {
			bout = new BufferedWriter(new FileWriter(time() + "-log.txt"));
			bout.write("Starting logfile at " + time());
			bout.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bout;
	}
	
	public static String time()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
		String time = sdf.format(cal.getTime());
		time = time + "." + System.currentTimeMillis() % 1000;
		return time;
	}

	public static void write(String msg)
	{
		if (verbose)
		{
			if (timestamp)
			{
				if (logfile)
				{
					out.println("[" + time() + "] " + msg);
				}
				System.out.println("[" + time() + "] " + msg);
			}
			else
			{
				if (logfile)
				{
					out.println(msg);
				}
				System.out.println(msg);
			}
		}
	}
}
