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
 * @implSpec 
 *
 */
public class DatabaseAccessObject {
	
	private String sqliteURL = "jdbc:sqlite:src/main/employeesystem/human_resources.db";
	private static Connection conn = null;
	private static Statement statement = null;
	private static LinkedHashMap<String, Department> departmentsMap = null;
	
	//Initialize and connect to the database
	public DatabaseAccessObject() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(sqliteURL);
			conn.setAutoCommit(false);
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
		String query = "SELECT * FROM job_positions ORDER BY job_title ASC";
		
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
				String query = "SELECT * FROM employees ORDER BY job_id ASC";
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
							Iterator<JobPosition> jobsItr = dept.getAllJobPositions().values().iterator();
							while(jobsItr.hasNext()) {
								JobPosition job = jobsItr.next();
								
								if(resultSet.getInt("job_id") == job.getJobId()) {
									emp.setDepartmentName(dept.getDepartmentName());
									
									job.setEmployee(emp);
									
									//set last employee id
									if(App.lastEmployeeId < emp.getEmployeeId()) {
										App.lastEmployeeId = emp.getEmployeeId();
									}
									dept.getAllJobPositions().replace(job.getJobTitle(), job);
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
	
	public void updateEmployee(Employee employee) {
		try {
			if(conn != null) {
				String query = String.format("UPDATE employees "
						+ "SET first_name='%s', surname='%s', email='%s', phone='%s', job_id=%d"
						+ "	WHERE employee_id=%d;", employee.getFirstName(), employee.getSurname(),
						employee.getEmail(), employee.getPhoneNumber(), employee.getJobId(), employee.getEmployeeId());
				
				if(!statement.execute(query)) {
					System.out.println(employee.getFirstName() + " " + employee.getSurname() + "'s info updated");
				}
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
	}
	
	public void addNewEmployee(Employee employee) {
		try {
			if(conn != null) {
				String query = String.format("INSERT INTO employees "
						+ "(employee_id, first_name, surname, email, phone, job_id) "
						+ "	VALUES (%d, '%s', '%s', '%s', '%s', %d);", employee.getEmployeeId(), 
						employee.getFirstName(), employee.getSurname(),
						employee.getEmail(), employee.getPhoneNumber(), employee.getJobId());
				
				if(!statement.execute(query)) {
					System.out.println(employee.getFirstName() + " " + employee.getSurname() + " inserted into database");
				}
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
	}
	
	public void deleteEmployee(Employee employee) {
		try {
			if(conn != null) {
				String query = String.format("DELETE FROM employees WHERE employee_id=%d", 
						employee.getEmployeeId());
				
				if(!statement.execute(query)) {
					System.out.println(employee.getFirstName() + " was deleted");
				}
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
	}
	

	public void updateJobPosition(JobPosition jobPosition) {
		try {
			if(conn != null){
				String query = String.format("UPDATE job_positions "
						+ "SET hourly_pay = %f WHERE job_id=%d", 
						jobPosition.getHourlyPay(), jobPosition.getJobId());
				
				if(!statement.execute(query)) {
					System.out.println(jobPosition.getJobTitle() + " is updated");
				}
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
	}
	
	public void undoChanges() {
		try {
			if(conn != null) {
				conn.rollback();
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
	}
	
	public void commitChanges() {
		try {
			if(conn != null) {
				conn.commit();
			}
		}catch(Exception err) {
			err.printStackTrace();
		}
	}
}