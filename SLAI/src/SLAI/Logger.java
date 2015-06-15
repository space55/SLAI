package SLAI;

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
	
	static PrintWriter out = new PrintWriter(define(), true);
	
	public static void init(boolean verb, boolean timest, boolean logfi)
	{
		verbose = verb;
		timestamp = timest;
		logfile = logfi;
	}
	
	public static BufferedWriter define()
	{
		BufferedWriter bout = null;
		try {
			bout = new BufferedWriter(new FileWriter(date() + " " + timeW() + "-log.txt"));
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
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(cal.getTime());
		time = time + "." + System.currentTimeMillis() % 1000;
		return time;
	}
	
	public static String timeW()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
		String time = sdf.format(cal.getTime());
		time = time + "." + System.currentTimeMillis() % 1000;
		return time;
	}
	
	public static String date()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String time = sdf.format(cal.getTime());
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
					out.println("[" + date() + " " + time() + "] " + msg);
				}
				System.out.println("[" + date() + " " + time() + "] " + msg);
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
