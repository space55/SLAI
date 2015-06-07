import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger
{
	public static boolean verbose = true;
	public static boolean timestamp = true;

	public static String time()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
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
				System.out.println("[" + time() + "] " + msg);
			}
			else
			{
				System.out.println(msg);
			}
		}
	}
}