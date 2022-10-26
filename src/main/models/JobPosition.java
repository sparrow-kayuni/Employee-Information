package main.models;
/**
 * 
 * @author Mwiinga Kayuni
 * @version 1.1 
 *
 */
public class JobPosition {
	private int jobId;
	private String jobTitle;
	private float hourlyPay;
	private String password;
	public boolean isLoggedIn = false;
	public boolean isFilled = false;
	private Employee employee = null;
	
	public JobPosition(int id, String title, float pay, String pw) {
		setJobId(id);
		setJobTitle(title);
		setHourlyPay(pay);
		setPassword(pw);
	}
	
	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public float getHourlyPay() {
		return hourlyPay;
	}

	public void setHourlyPay(float hourlyPay) {
		this.hourlyPay = hourlyPay;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
		this.isFilled = true;
	}
	
	public void removeEmployee() {
		this.employee = null;
		this.isFilled = false;
	}

}
