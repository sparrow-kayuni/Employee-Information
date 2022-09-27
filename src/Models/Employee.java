package Models;

public class Employee {
	private String firstName;
	private String surname;
	private int employeeId;
	private String email;
	private String phoneNumber;
	
	public Employee() {
		//
	}
	
	public Employee(String fName, String lName, int empId, String email2, String phone) {
		setFirstName(fName);
		setSurname(lName);
		setEmployeeId(empId);
		setEmail(email2);
		setPhoneNumber(phone);
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
}
