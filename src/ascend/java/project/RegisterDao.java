package ascend.java.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

public class RegisterDao {
	public String registerUser(RegisterBean registerBean) {
		String userName = registerBean.getUserName();
		String firstName = registerBean.getFirstName();
		String lastName = registerBean.getLastName();
		String password = registerBean.getPassword();
		int age = registerBean.getAge();
		String phone = registerBean.getPhone();
		String email = registerBean.getEmail();
		try {
			PasswordEncryptor encryptor = new BasicPasswordEncryptor();
			password = encryptor.encryptPassword(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = DatabaseConnection.getConnection();
			String query = "insert into users(userName,firstName,lastName,age,phone,email,password) values (?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setInt(4, age);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, password);
			int i = preparedStatement.executeUpdate();
			if (i != 0) {
				return "SUCCESS";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong there..!";
	}

	public int searchByUsername(String userName) {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query;
		int count = 0;
		try {
			if (userName.length() == 8) {
				userName = userName.substring(0, 7);
			}
			con = DatabaseConnection.getConnection();
			query = "Select count(*) from users where username like '" + userName + "%'";
			preparedStatement = con.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				con.close();
			}
			if (count >= 10) {
				userName = userName.substring(0, 6);
				con = DatabaseConnection.getConnection();
				query = "Select count(*) from users where username like '" + userName + "%'";
				preparedStatement = con.prepareStatement(query);
				rs = preparedStatement.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
					con.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}
}
