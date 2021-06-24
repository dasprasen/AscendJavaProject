package ascend.java.project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	public static Connection getConnection()
    {
	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/xe", "prasen", "12345");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return null;
    }
}
