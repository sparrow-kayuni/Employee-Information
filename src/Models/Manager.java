package Models;

public class Manager extends Employee {
	private String password;
	public boolean isLoggedIn = false;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
