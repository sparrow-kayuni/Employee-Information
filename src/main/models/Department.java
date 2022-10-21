package main.models;

import java.util.LinkedHashMap;
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
	private LinkedHashMap<String, JobPosition> jobPositionsMap = null;
	private LinkedHashMap<Integer, String> filledJobPositions = null;
	
	public boolean isLoggedIn = false;
	
	public Department(int id, String deptName, int manager_id) {
		setDepartmentId(id);
		setDepartmentName(deptName);
		initializeJobPositions();
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
	
	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public LinkedHashMap<String, JobPosition> getJobPositions() {
		return jobPositionsMap;
	}
	
	public void initializeJobPositions() {
		this.jobPositionsMap = new LinkedHashMap<String, JobPosition>();
		this.setFilledJobPositions(new LinkedHashMap<Integer, String>());
	}
	
	public void addJobPosition(JobPosition jobPosition) {
		this.jobPositionsMap.put(jobPosition.getJobTitle(), jobPosition);
	}

	public LinkedHashMap<Integer, String> getFilledJobPositions() {
		return filledJobPositions;
	}

	public void setFilledJobPositions(LinkedHashMap<Integer, String> filledJobPositions) {
		this.filledJobPositions = filledJobPositions;
	}
	
	public void addToFilledPosition(int jobId, String jobTitle) {
		this.filledJobPositions.put(jobId, jobTitle);
	}
	
}
