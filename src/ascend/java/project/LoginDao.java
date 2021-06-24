package ascend.java.project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {

	public String authenticateUser(LoginBean loginBean)
	{
	    String userName = loginBean.getUserName();
	    String password = loginBean.getPassword();
	 
	    Connection con = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	 
	    String userNameDB = "";
	    String passwordDB = "";
	    String roleDB = "";
	 
	    try
	    {
	        con = DatabaseConnection.getConnection();
	        statement = con.createStatement();
	        resultSet = statement.executeQuery("select username,password,role from users");
	 
	        while(resultSet.next())
	        {
	            userNameDB = resultSet.getString("username");
	            passwordDB = resultSet.getString("password");
	            roleDB = resultSet.getString("role");
	 
	            if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("admin"))
	            return "Admin_Role";
	            else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("user"))
	            return "User_Role";
	        }
	    }
	    catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
	    return "Invalid user credentials";
	}
}
