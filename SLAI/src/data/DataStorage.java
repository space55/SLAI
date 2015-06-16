package data;

public class DataStorage {
	private String birthdayDay;
	private String birthdayMonth;
	private String name;
	private String age;
	public void store(String type, String value)
	{
		if (type.equalsIgnoreCase("birthdayDay"))
		{
			birthdayDay = value;
		}
		if (type.equalsIgnoreCase("birthdayMonth"))
		{
			birthdayMonth = value;
		}
		if (type.equalsIgnoreCase("name"))
		{
			name = value;
		}
		if (type.equalsIgnoreCase("age"))
		{
			age = value;
		}
	}
	
	public String get(String type)
	{
		String value = null;

		if (type.equalsIgnoreCase("birthdayDay"))
		{
			value = birthdayDay;
		}
		if (type.equalsIgnoreCase("birthdayMonth"))
		{
			value = birthdayMonth;
		}
		if (type.equalsIgnoreCase("name"))
		{
			value = name;
		}
		if (type.equalsIgnoreCase("age"))
		{
			value = age;
		}
		
		return value;
	}
}
