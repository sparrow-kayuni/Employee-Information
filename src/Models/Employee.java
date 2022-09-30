package Models;

public class Employee {
	private String firstName;
	private String surname;
	private int employeeId;
	private String email;
	private String phoneNumber;
	private JobPosition jobPosition;
	
	public Employee() {
		//
	}
	
	public Employee(String fName, String lName, int empId, String email2, String phone, int jobId) {
		setFirstName(fName);
		setSurname(lName);
		setEmployeeId(empId);
		setEmail(email2);
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
		
		while(id.length() < 20) {
			id = id + " ";
		}
		while(fullName.length() < 50) {
			fullName = fullName + " ";
		}
		
		return id + fullName;
	}

	public JobPosition getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(JobPosition position) {
		this.jobPosition = position;
	}
	
	public void setJobId(int jobId) {
		this.jobPosition = new JobPosition(jobId);
		
	}
}
