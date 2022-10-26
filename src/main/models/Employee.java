package main.models;

import main.employeesystem.App;

/**
 * 
 * @author Mwiinga Kayuni
 * @version 1.1 
 *
 */
public class Employee implements Comparable<Employee>{
	private String firstName;
	private String surname;
	private int employeeId;
	private String email;
	private String phoneNumber;
	private String departmentName;
	private int jobId;
	
	public Employee() {
		//
	}
	
	/**
	 * 
	 * @param fName
	 * @param lName
	 * @param empId
	 * @param email
	 * @param phone
	 * @param jobId
	 */
	public Employee(String fName, String lName, int empId, String email, String phone, int jobId) {
		setFirstName(fName);
		setSurname(lName);
		setEmployeeId(empId);
		setEmail(email);
		setPhoneNumber(phone);
		setJobId(jobId);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmployeeInfoFormatted() {
		String id = Integer.toString(employeeId);
		String fullName = firstName + " " + surname;
		String jobTitle = App.getDepartments().get(this.departmentName).getJobTitle(this.jobId);
		
		while(id.length() < 20) {
			id = id + " ";
		}
		while(fullName.length() < 25) {
			fullName = fullName + " ";
		}		
		
		return id + fullName + jobTitle;
	}

	@Override
	public int compareTo(Employee o) {
		return o.getEmployeeId();
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	public boolean isIdenticalTo(Employee emp) {
		return getFirstName().equals(emp.getFirstName()) && getSurname().equals(emp.getSurname()) &&
				getEmail().equals(emp.getEmail()) && getPhoneNumber().equals(emp.getPhoneNumber())
				&& getEmployeeId() == emp.getEmployeeId() ? true: false;
	}
	
	public boolean hasInfoFilled() {
		return !getFirstName().equals("") || !getSurname().equals("") ||
				!getEmail().equals("") || !getPhoneNumber().equals("")? true: false;
	}
}
