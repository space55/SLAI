import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MySQL_Test
{
	private static String input = "yes";
	private static boolean verbose = true;
	private static boolean timestamp = true;

	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException
	{
		try
		{
			log("Loading driver...");
			Class.forName("com.mysql.jdbc.Driver");
			log("Driver loaded!");
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
		try
		{
			log("Attempting to invoke viewTable");
			viewTable(getConnection(), "yesno_answers");
		}
		catch (SQLException err)
		{
			log("viewTable caused error (or getConnection)");
			System.out.println(err.getMessage());
		}
	}

	public static void viewTable(Connection con, String dbName) throws SQLException
	{
		log("viewTable invoked");

		Statement stmt = null;
		String query = "select yesno_validations from yesno_answers where yesno_id = \"yes\"";
		// String query = "select COF_NAME, SUP_ID, PRICE, " + "SALES, TOTAL " + "from " + dbName + ".COFFEES";
		try
		{
			stmt = con.createStatement();
			log("stmt initialized & declared");
			ResultSet rs;
			log("rs declared");
			rs = stmt.executeQuery(query);
			log("Query executed");
			while (rs.next())
			{
				/*
				 * String coffeeName = rs.getString("COF_NAME");
				 * int supplierID = rs.getInt("SUP_ID");
				 * float price = rs.getFloat("PRICE");
				 * int sales = rs.getInt("SALES");
				 * int total = rs.getInt("TOTAL");
				 */

				log("Getting validations");
				int yesnoValidations = rs.getInt("yesno_validations");
				log("Creating rs & ynV strings");
				String rsL = "rs: " + rs;
				String ynVL = "ynV: " + yesnoValidations;
				log(rsL);
				log(ynVL);
				// System.out.println(coffeeName + "\t" + supplierID + "\t" + price + "\t" + sales + "\t" + total);
			}
		}
		catch (SQLException e)
		{
			log("Exception caused by sending a query");
			// JDBCTutorialUtilities.printSQLException(e);
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
				log("Closing connection");
			}
		}
	}

	public static Connection getConnection() throws SQLException
	{

		Connection conn = null;
		String user = "ai-yna";
		String password = "a1Ynafwd$";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yesno", user, password);
		log("Connected to database");
		return conn;
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

	public static void log(String msg)
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