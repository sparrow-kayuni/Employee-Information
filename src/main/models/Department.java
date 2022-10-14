package main.models;

import java.util.HashMap;
/**
 * 
 * @author Mwiinga Kayuni
 * @version 1.1 
 *
 */
public class Department {
	private int departmentId;
	private String departmentName;
	private int managerId;
	private HashMap<Integer, Employee> employeesMap;
	
	public boolean isLoggedIn = false;
	
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
		return employeesMap;
	}
	
	public void initEmployeesList() {
		this.employeesMap = new HashMap<Integer, Employee>();
	}
	
	public void setEmployeesList(HashMap<Integer, Employee> empList) {
		this.employeesMap = empList;
	}
	
	public void addEmployeeToDepartment(Employee emp) {
		emp.getJobPosition().setDepartment(this);
		this.employeesMap.put(emp.getEmployeeId(), emp);
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
