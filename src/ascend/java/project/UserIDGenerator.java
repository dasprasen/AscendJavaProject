package ascend.java.project;

import java.util.concurrent.ThreadLocalRandom;

public class UserIDGenerator {
	public String getUserId(String firstName,String lastName) {
		
		String userName="";
		if(lastName.length()>=7) {
			userName = firstName.substring(0, 1)+lastName.substring(0,7);
		}else {
			userName = firstName.substring(0, 1)+lastName.substring(0);
		}
		
		RegisterDao registerDao = new RegisterDao();
		int status=registerDao.searchByUsername(userName);
		if(status==1) {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			int rand = random.nextInt(1, 10);
			if(userName.length()==8) {
				userName=userName.substring(0,userName.length()-1)+rand;
				return userName;
			}else {
				userName=userName.substring(0)+rand;
				return userName;
			}
		}
		return userName;
	}
}
