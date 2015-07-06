package data;

public class DataStorage {
	private String birthdayDay;
	private String birthdayMonth;
	private String name;
	private String age;
	private String weather;
	public void store(String type, String value)
	{
		if (type.equalsIgnoreCase("birthdayDay"))
		{
			birthdayDay = value;
		}
		else if (type.equalsIgnoreCase("birthdayMonth"))
		{
			birthdayMonth = value;
		}
		else if (type.equalsIgnoreCase("name"))
		{
			name = value;
		}
		else if (type.equalsIgnoreCase("age"))
		{
			age = value;
		}
		else if (type.equalsIgnoreCase("weather"))
		{
			weather = value;
		}
	}
	
	public String get(String type)
	{
		String value = null;

		if (type.equalsIgnoreCase("birthdayDay"))
		{
			value = birthdayDay;
		}
		else if (type.equalsIgnoreCase("birthdayMonth"))
		{
			value = birthdayMonth;
		}
		else if (type.equalsIgnoreCase("name"))
		{
			value = name;
		}
		else if (type.equalsIgnoreCase("age"))
		{
			value = age;
		}
		else if (type.equalsIgnoreCase("weather"))
		{
			value = weather;
		}
		
		return value;
	}
}
