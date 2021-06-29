package ascend.java.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {

	public String updateRecords(String username, int age, String phone, String email) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = DatabaseConnection.getConnection();
			String query = "update users set age=?,phone=?,email=? where username=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, age);
			preparedStatement.setString(2, phone);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, username);
			int i = preparedStatement.executeUpdate();
			if (i != 0) {
				return "Record Updated Successfully";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Record couldn't be updated";
	}

}
