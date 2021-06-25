package ascend.java.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

	public String authenticateUser(LoginBean loginBean)
	{
	    String userName = loginBean.getUserName();
	    String password = loginBean.getPassword();
	    PasswordEncryption passwordEncryption = new PasswordEncryption();
		//password=passwordEncryption.encrypt(password);
	 
	    Connection con = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	 
	    String userNameDB = "";
	    String passwordDB = "";
	    String roleDB = "";
	 
	    try
	    {
	        con = DatabaseConnection.getConnection();
	        String query="select username,password,role from users where userName=?";
	        statement=con.prepareStatement(query);
	        statement.setString(1, userName);
	        resultSet = statement.executeQuery();
	 
	        while(resultSet.next())
	        {
	            userNameDB = resultSet.getString("username");
	            passwordDB = resultSet.getString("password");
	            passwordDB=passwordEncryption.decrypt(passwordDB);
	            roleDB = resultSet.getString("role");
	 
	            if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("admin"))
	            return "Admin_Role";
	            else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("users"))
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
