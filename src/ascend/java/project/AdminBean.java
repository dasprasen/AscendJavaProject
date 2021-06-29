package ascend.java.project;

public class AdminBean {
	private String username;
	private String firstname;
	private String lastname;
	private int age;
	private String phone;
	private String email;
	private String role;

	public AdminBean() {

	}

	public AdminBean(String username, String firstname, String lastname, int age, String phone, String email,
			String role) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
