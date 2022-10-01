package Models;

public class JobPosition {
	private int jobId;
	private String jobTitle;
	private int departmentId;
	private float hourlyPay;
	private String password;
	public boolean isLoggedIn = false;
	private Department department;
	
	public JobPosition(int id) {
		setJobId(id);
	}
	
	public void setJobPositionDetails(String title, int deptId, float pay, String pw) {
		setJobTitle(title);
		setDepartmentId(deptId);
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		
		this.department = department;
	}

}
