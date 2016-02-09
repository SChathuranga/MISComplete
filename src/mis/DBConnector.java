package mis;

import java.sql.*;


public class DBConnector {
	static Connection con = null;
	static Statement stmt = null;
	
	public static Connection dbConnector()
	{
		try
		{
			String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(Driver);
			String url = "jdbc:sqlserver://DESKTOP-SKHEU8H\\SQLEXPRESS:59189;" + "databaseName=misdb;";
			con = DriverManager.getConnection(url, "genius", "mastermind");
			return con;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
