package main.employeesystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;


/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec Contains 
 *
 */
public class PostgresDatabase {
	
	private String pgURL = "jdbc:postgresql://localhost:5432/human_resources";
	private String userName = "test";
	private String password = "123";
	private static Connection conn = null;
	private static Statement statement;
	private static LinkedHashMap<String, Department> departmentsMap = null;
	
	//Initialize and connect to the database
	public PostgresDatabase() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(pgURL, userName, password);
			statement = conn.createStatement();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public HashMap<String, Department> createDepartmentsMap() {
		departmentsMap = new LinkedHashMap<String, Department>();
		
		createDepartmentObjects();
		addJobPositionsToDepartments();		
		fillJobPositions();
		
		return departmentsMap;
	}
	
	
	//Creates departments objects and adds them to departments map
	public void createDepartmentObjects() {
		try {
			if(conn != null) {
				String query = "SELECT * FROM departments ORDER BY department_id ASC";
				ResultSet results = statement.executeQuery(query);

				while(results.next()) {
					Department dept = new Department(
							results.getInt("department_id"), 
							results.getString("department_name").toUpperCase(), 
							results.getInt("department_manager_id"));
					
					departmentsMap.put(results.getString("department_name").toUpperCase(), dept);
				}	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	//Add job positions to each department in the departments map
	public void addJobPositionsToDepartments() {
		String query = "SELECT * FROM public.job_positions ORDER BY job_title ASC";
		
		try {
			ResultSet results = statement.executeQuery(query);
			
			while(results.next()) {
				Iterator<Department> deptItr = departmentsMap.values().iterator();
				
				//iterate through the departments map and add corresponding job positions
				//to each department object then update departments map
				while(deptItr.hasNext()) {
					Department dept = deptItr.next();
					
					if(dept.getDepartmentId() == results.getInt("department_id")){
						dept.addJobPosition(
								new JobPosition(
									results.getInt("job_id"), results.getString("job_title"), 
									results.getFloat("hourly_pay"), results.getString("password")
							)
						);
					}
					departmentsMap.replace(dept.getDepartmentName(), dept);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//Queries employees table and fills the appropriate job position
	private void fillJobPositions() {
		try {
			if (conn != null) {
				String query = "SELECT * FROM public.employees ORDER BY job_id ASC";
				ResultSet resultSet = statement.executeQuery(query);
				
				while(resultSet.next()) {
					//create employee object
					Employee emp = new Employee(
							resultSet.getString("first_name"), resultSet.getString("surname"),
							resultSet.getInt("employee_id"), resultSet.getString("email"),
							resultSet.getString("phone"), resultSet.getInt("job_id")
						);
					
					//Iterate through each department
					Iterator<Department> deptItr = departmentsMap.values().iterator();
					try {
						while(deptItr.hasNext()) {
							Department dept = deptItr.next();
							
							//Iterate through job positions of the department
							Iterator<JobPosition> jobsItr = dept.getJobPositions().values().iterator();
							while(jobsItr.hasNext()) {
								JobPosition job = jobsItr.next();
								
								if(resultSet.getInt("job_id") == job.getJobId()) {
									emp.setDepartmentName(dept.getDepartmentName());
									
									job.setEmployee(emp);
									
									App.lastEmployeeId = emp.getEmployeeId();
									dept.getJobPositions().replace(job.getJobTitle(), job);
								}
								dept.addToJobTitlesMap(job.getJobId(), job.getJobTitle());
							}
							departmentsMap.replace(dept.getDepartmentName(), dept);
						}	
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}