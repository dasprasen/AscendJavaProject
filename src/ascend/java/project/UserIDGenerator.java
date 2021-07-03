package ascend.java.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UserIDGenerator {

	public int counter;

	public String getUserId(String firstName, String lastName) {

		String userName = "";
		addCounter();
		if (lastName.length() >= 7) {
			userName = firstName.substring(0, 1) + lastName.substring(0, 7);
		} else {
			userName = firstName.substring(0, 1) + lastName.substring(0);
		}

		RegisterDao registerDao = new RegisterDao();
		int status = registerDao.searchByUsername(userName);
		if (status == 1) {
			if (counter > 0 && counter <= 9) {
				if (userName.length() == 8) {
					userName = userName.substring(0, userName.length() - 1) + counter;
					return userName;
				} else {
					userName = userName.substring(0) + counter;
					return userName;
				}
			} else if (counter >= 10 && counter < 100) {
				if (userName.length() >= 7) {
					userName = userName.substring(0, userName.length() - 2) + counter;
					return userName;
				} else {
					userName = userName.substring(0) + counter;
					return userName;
				}
			} else {
				if (userName.length() >= 5) {
					userName = userName.substring(0, userName.length() - 3) + counter;
					return userName;
				} else {
					userName = userName.substring(0) + counter;
					return userName;
				}
			}
		}
		return userName;
	}

	private void addCounter() {
		counter = getCounter();
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = DatabaseConnection.getConnection();
			String query = "insert into trackuserid values(?)";
			statement = con.prepareStatement(query);
			statement.setInt(1, counter);
			int status = statement.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int getCounter() {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			con = DatabaseConnection.getConnection();
			String query = "select count(counter)+1 from trackuserid";
			statement = con.prepareStatement(query);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				counter = resultSet.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

	public String getRandomPassword() {
		int length = 8;
		String finalString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-/.^&*_!@%=+>)";
		Random random = new Random();
		char[] password = new char[length];
		for (int i = 0; i < length; i++) {
			password[i] = finalString.charAt(random.nextInt(finalString.length()));
		}
		return password.toString();
	}
}
