package Models;

public class Manager extends Employee {
	private String password;
	private int department_id;
	private String department_name;
	public boolean isLoggedIn = false;
	
	public Manager(Employee employee) {
		setFirstName(employee.getFirstName());
		setSurname(employee.getSurname());
		setEmployeeId(employee.getEmployeeId());
		setEmail(employee.getEmail());
		setPhoneNumber(employee.getPhoneNumber());
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDepartmentId() {
		return department_id;
	}
	
	public String getDepartmentName() {
		return department_name;
	}

	public void setDepartment(int id, String dept_name) {
		this.department_id = id;
		this.department_name = dept_name;
	}
	
	

}
