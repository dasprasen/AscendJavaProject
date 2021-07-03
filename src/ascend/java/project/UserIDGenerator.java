package ascend.java.project;

import java.util.Random;

public class UserIDGenerator {

	public int counter;

	public String getUserId(String firstName, String lastName) {

		String userName = "";
		if (lastName.length() >= 7) {
			userName = firstName.substring(0, 1) + lastName.substring(0, 7);
		} else {
			userName = firstName.substring(0, 1) + lastName.substring(0);
		}

		RegisterDao registerDao = new RegisterDao();
		int count = registerDao.searchByUsername(userName);
		if (count != 0) {
			if (count > 0 && count <= 9) {
				if (userName.length() == 8) {
					userName = userName.substring(0, userName.length() - 1) + count;
					return userName;
				} else {
					userName = userName.substring(0) + count;
					return userName;
				}
			} else if (count > 9 && count < 99) {
				if (userName.length() >= 7) {
					userName = userName.substring(0, userName.length() - 2) + count;
					return userName;
				} else {
					userName = userName.substring(0) + count;
					return userName;
				}
			} else {
				if (userName.length() >= 5) {
					userName = userName.substring(0, userName.length() - 3) + count;
					return userName;
				} else {
					userName = userName.substring(0) + count;
					return userName;
				}
			}
		}
		return userName;
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
