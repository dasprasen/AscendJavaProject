package ascend.java.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao {
	public String registerUser(RegisterBean registerBean)
    {
		String userName = registerBean.getUserName();
        String firstName = registerBean.getFirstName();
        String lastName = registerBean.getLastName();
        String password = registerBean.getPassword();
        try {
        	PasswordEncryption passwordEncryption = new PasswordEncryption();
        	password=passwordEncryption.encrypt(password);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        Connection con = null;
        PreparedStatement preparedStatement = null;         
        try
        {
            con = DatabaseConnection.getConnection();
            String query = "insert into users(userName,firstName,lastName,password) values (?,?,?,?)";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, password);
            int i= preparedStatement.executeUpdate();
            if (i!=0) {
            	return "SUCCESS";
            } 
        }
        catch(SQLException e){
           e.printStackTrace();
        }       
        return "Something went wrong there..!";  
    }

	public int searchByUsername(String userName) {
		Connection con = null;
        PreparedStatement preparedStatement = null; 
        ResultSet rs=null;
        try {
        	con=DatabaseConnection.getConnection();
        	String query="Select * from users where username=?";
        	preparedStatement = con.prepareStatement(query);
        	preparedStatement.setString(1, userName);
        	rs=preparedStatement.executeQuery();
        	if(rs.next()) {
        		return 1;
        	}
        }catch(SQLException e) {
        	e.printStackTrace();
        }
		
		return 0;
	}
}
