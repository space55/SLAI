import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class serverProtocol
{
	public String processInput(String dataInput) throws SQLException
	{
		if (dataInput.equalsIgnoreCase(""))
		{
			return "";
		}
		char type = dataInput.charAt(0);
		String input = dataInput.substring(1);
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
		
		if (type == '*')
		{
			createRow(input, 1, true, getConnection());
		}
		else if (type == '^')
		{
			createRow(input, 1, false, getConnection());
		}
		else if (type == '@')
		{
			String data = findData(input, getConnection());
			
			if (data.equals("#"))
			{
				output = "%";
			}
			else
			{
				output = data + "\\" + input;
			}
		}
		return output;
	}

	public static void executeUpdate(String id, int yn, Connection con) throws SQLException
	{
		Logger.write("executeUpdate invoked");

		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			String val = findData(id, con, "yesno_validations");
			int valida = 1;
			try
			{
				valida = Integer.parseInt(val);
			}
			catch (Exception e)
			{
				Logger.write("yesno_validations with id " + id + " had bad data.");
			}
			stmt.executeUpdate("update yesno_answers set yesno_validations=" + valida + " where yesno_id=" + id + ";");
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

	public static void createRow(String id, int validations, boolean tf, Connection con) throws SQLException
	{
		Logger.write("createRow invoked");

		Statement stmt = null;
		int yn = 1;
		if (!tf)
		{
			yn = 0;
		}
		String command = "insert into yesno_answers (yesno_id, yesno_validations, yesno_tf) values (" + id + ", " + validations + ", " + yn + ");";
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
	
	public static String findData(String id, Connection con) throws SQLException
	{
		String ret = findData(id, con, "yesno_validations") + "\\" + findData(id, con, "yesno_tf");
		if (ret.equals(null))
		{
			ret = "#";
		}
		return ret;
	}

	public static String findData(String id, Connection con, String wantedResult) throws SQLException
	{
		String result = "";
		Logger.write("executeCommand invoked");

		Statement stmt = null;
		try
		{
			stmt = con.createStatement();
			Logger.write("stmt initialized & declared");
			ResultSet rs;
			Logger.write("rs declared");
			rs = stmt.executeQuery("SELECT " + wantedResult + " FROM yesno_answers WHERE yesno_id = "+ id +";");
			Logger.write("Query executed");
			while (rs.next())
			{

				Logger.write("Getting " + wantedResult);
				result = "" + rs.getInt(wantedResult);
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