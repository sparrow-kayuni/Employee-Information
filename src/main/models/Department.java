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
	private LinkedHashMap<String, JobPosition> jobPositions = null;
	
	//maps job id 
	private LinkedHashMap<Integer, String> jobTitlesMap = null;
	
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
		return jobPositions;
	}
	
	public void initializeJobPositions() {
		this.jobPositions = new LinkedHashMap<String, JobPosition>();
		this.setJobPositionsMap(new LinkedHashMap<Integer, String>());
	}
	
	public void addJobPosition(JobPosition jobPosition) {
		this.jobPositions.put(jobPosition.getJobTitle(), jobPosition);
	}

	public String getJobTitle(int jobId) {
		return jobTitlesMap.get(jobId);
	}
	
	public boolean containsJobPosition(int jobId) {
		return jobTitlesMap.containsKey(jobId);
	}

	public void setJobPositionsMap(LinkedHashMap<Integer, String> jobTitlesMap) {
		this.jobTitlesMap = jobTitlesMap;
	}
	
	public void addToJobTitlesMap(int jobId, String jobTitle) {
		this.jobTitlesMap.put(jobId, jobTitle);
	}
	
}
