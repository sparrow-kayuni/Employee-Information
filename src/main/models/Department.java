package main.models;

import java.util.HashMap;

public class Department {
	private int departmentId;
	private String departmentName;
	private int managerId;
	private HashMap<Integer, Employee> employeesList;
	
	public Department(int id, String deptName, int manager_id) {
		setDepartmentId(id);
		setDepartmentName(deptName);
		initEmployeesList();
		setManagerId(manager_id);
	}
	
	public int getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public HashMap<Integer, Employee> getEmployeesList() {
		return employeesList;
	}
	
	public void initEmployeesList() {
		this.employeesList = new HashMap<Integer, Employee>();
	}
	
	public void setEmployeesList(HashMap<Integer, Employee> empList) {
		this.employeesList = empList;
	}
	
	public void addEmployeeToDepartment(Employee emp) {
		emp.getJobPosition().setDepartment(this);
		this.employeesList.put(emp.getEmployeeId(), emp);
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
//	private void sortEmployeesList() {
//		//
//	}
	
}
