package SLAI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger
{
	private static boolean verboseLogger = true;
	private static boolean timestampLogger = true;

	public static void init(boolean vL, boolean tL)
	{
		verboseLogger = vL;
		timestampLogger = tL;
	}
	
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
		if (verboseLogger)
		{
			if (timestampLogger)
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