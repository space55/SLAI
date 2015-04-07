import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class serverProtocol
{
	public String processInput(String sentInput) throws SQLException
	{
		String input = sentInput;
		String output = null;

		try
		{
			Logger.write("Loading driver...");
			Class.forName("com.mysql.jdbc.Driver");
			Logger.write("Driver loaded!");
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}

		if (input.charAt(0) == '1')
		{
			Logger.write("Creating a row (with true)");
			createRow(input, 1, true, getConnection(), input);
		}
		else
		{
			Logger.write("Creating a row (with false)");
			createRow(input, 1, false, getConnection(), input);
		}
		String query = "select yesno_id from yesno_answers where yesno_id = \"" + input + "\"";
		String result = executeCommandString(query, "yesno_id", getConnection());
		if (result.equalsIgnoreCase(""))
		{
			Logger.write("No row with id " + input + " found; returning find ans");
			output = "2";
		}
		else
		{
			String findCommand = "select yesno_validations from yesno_answers where yesno_id = \"" + input + "\";";
			int val = executeCommandInt(findCommand, "yesno_validations", getConnection());
			String valAdd = "insert into yesno_answers yesno_validations value " + (val + 1) + ";";
			executeUpdate(valAdd, getConnection());
		}
		input = "null";
		return output;
	}

	public static void viewTable(Connection con, String dbName) throws SQLException
	{
		Logger.write("viewTable invoked");

		Statement stmt = null;
		String query = "select yesno_validations from yesno_answers where yesno_id = \"yes\";";
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			ResultSet rs;
			Logger.write("rs declared");
			rs = stmt.executeQuery(query);
			Logger.write("Query executed");
			while (rs.next())
			{
				Logger.write("Getting validations");
				int yesnoValidations = rs.getInt("yesno_validations");
				Logger.write("Creating rs & ynV strings");
				String rsL = "rs: " + rs;
				String ynVL = "ynV: " + yesnoValidations;
				Logger.write(rsL);
				Logger.write(ynVL);
			}
		}
		catch (SQLException e)
		{
			Logger.write("Exception caused by sending a query");
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
				Logger.write("Closing connection");
			}
		}
	}

	public static void executeUpdate(String command, Connection con) throws SQLException
	{
		Logger.write("executeUpdate invoked");

		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			stmt.executeUpdate(command);
			Logger.write("Update executed");
		}
		catch (SQLException e)
		{
			Logger.write("Exception caused by sending a query");
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
				Logger.write("Closing connection");
			}
		}
	}

	public static void createRow(String id, int validations, boolean tf, Connection con, String input) throws SQLException
	{
		Logger.write("createRow invoked");

		Statement stmt = null;
		String findCommand = "select yesno_validations from yesno_answers where yesno_id = \"" + input + "\";";
		int val = executeCommandInt(findCommand, "yesno_validations", con);
		String command = "insert into yesno_answers (yesno_id, yesno_validations, yesno_tf) values (" + id + ", " + (validations + val) + ");";
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			stmt.executeUpdate(command);
			Logger.write("Update executed");
		}
		catch (SQLException e)
		{
			Logger.write("Exception caused by sending a query");
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
				Logger.write("Closing connection");
			}
		}
	}

	public static int executeCommandInt(String command, String wantedResult, Connection con) throws SQLException
	{
		int result = 0;
		Logger.write("executeCommandInt invoked");

		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			ResultSet rs;
			Logger.write("rs declared");
			rs = stmt.executeQuery(command);
			Logger.write("Query executed");
			while (rs.next())
			{

				Logger.write("Getting " + wantedResult);
				result = rs.getInt(wantedResult);
				Logger.write("Creating rs, wR, & r strings");
				String rsL = "rs: " + rs;
				String wRL = "wR: " + wantedResult;
				String rL = "rL: " + result;
				Logger.write(rsL);
				Logger.write(wRL);
				Logger.write(rL);
			}
		}
		catch (SQLException e)
		{
			Logger.write("Exception caused by sending an update");
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
				Logger.write("Closing connection");
			}
		}
		return result;
	}

	public static String executeCommandString(String command, String wantedResult, Connection con) throws SQLException
	{
		String result = "";
		Logger.write("executeCommandString invoked");

		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			ResultSet rs;
			Logger.write("rs declared");
			rs = stmt.executeQuery(command);
			Logger.write("Query executed");
			while (rs.next())
			{

				Logger.write("Getting " + wantedResult);
				result = rs.getString(wantedResult);
				Logger.write("Creating rs, wR, & r strings");
				String rsL = "rs: " + rs;
				String wRL = "wR: " + wantedResult;
				String rL = "rL: " + result;
				Logger.write(rsL);
				Logger.write(wRL);
				Logger.write(rL);
			}
		}
		catch (SQLException e)
		{
			Logger.write("Exception caused by sending a query");
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
				Logger.write("Closing connection");
			}
		}
		return result;
	}

	public static boolean executeCommandBoolVInt(String command, String wantedResult, Connection con) throws SQLException
	{
		boolean result = false;
		int result1 = 0;
		Logger.write("executeCommandBoolVInt invoked");

		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			ResultSet rs;
			Logger.write("rs declared");
			rs = stmt.executeQuery(command);
			Logger.write("Query executed");
			while (rs.next())
			{

				Logger.write("Getting " + wantedResult);
				result1 = rs.getInt(wantedResult);
				if (result1 == 1)
				{
					result = true;
				}
				Logger.write("Creating rs, wR, & r strings");
				String rsL = "rs: " + rs;
				String wRL = "wR: " + wantedResult;
				String rL = "rL: " + result1;
				Logger.write(rsL);
				Logger.write(wRL);
				Logger.write(rL);
			}
		}
		catch (SQLException e)
		{
			Logger.write("Exception caused by sending a query");
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
				Logger.write("Closing connection");
			}
		}
		return result;
	}

	public static boolean executeCommandBool(String command, String wantedResult, Connection con) throws SQLException
	{
		boolean result = false;
		Logger.write("executeCommandBool invoked");

		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			ResultSet rs;
			Logger.write("rs declared");
			rs = stmt.executeQuery(command);
			Logger.write("Query executed");
			while (rs.next())
			{

				Logger.write("Getting " + wantedResult);
				result = rs.getBoolean(wantedResult);
				Logger.write("Creating rs, wR, & r strings");
				String rsL = "rs: " + rs;
				String wRL = "wR: " + wantedResult;
				String rL = "rL: " + result;
				Logger.write(rsL);
				Logger.write(wRL);
				Logger.write(rL);
			}
		}
		catch (SQLException e)
		{
			Logger.write("Exception caused by sending a query");
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
				Logger.write("Closing connection");
			}
		}
		return result;
	}

	public static Connection getConnection() throws SQLException
	{

		Connection conn = null;
		String user = "ai-yna";
		String password = "a1Ynafwd$";
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yesno", user, password);
		Logger.write("Connected to database");
		return conn;
	}
}