package main.employeesystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

import main.models.Department;
import main.models.Employee;


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
	
	/**
	 * 
	 * @return hashmap of the list of employees
	 */
	public HashMap<Integer, Employee>  generateEmployeesList() {
		HashMap<Integer, Employee> employeesMap = new HashMap<Integer, Employee>();
		
		employeesMap.putAll(addEmployees(employeesMap));
		
		employeesMap.putAll(addEmployeeJobPositionDetails(employeesMap));

		return employeesMap;
	}
	
	/**
	 * 
	 * @param empsMap
	 * @return employeelist hashmap
	 * @implNote queries the database for all the employees and creates employee objects
	 * which it adds to the employeemap hashmap
	 */
	private HashMap<Integer, Employee> addEmployees(HashMap<Integer, Employee> empsMap) {
		try {
			if (conn != null) {
				String query = "SELECT * FROM public.employees ORDER BY employee_id ASC";
				ResultSet resultSet = statement.executeQuery(query);
				
				while(resultSet.next()) {
					empsMap.put(
						resultSet.getInt("employee_id"),
						createEmployee(
							resultSet.getString("first_name"), resultSet.getString("surname"),
							resultSet.getInt("employee_id"), resultSet.getString("email"),
							resultSet.getString("phone"), resultSet.getInt("job_id")
						)	
					);	
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return empsMap;
	}


	private Employee createEmployee(String fName, String surname, int empId, String email, String phone, int jobId) {
		return new Employee(fName, surname, empId, email, phone, jobId);
	}
	
	/**
	 * 
	 * @param employeesMap
	 * @return complete employee hashmap
	 * @implNote Queries the database for job positions, then iterates through the empoyee list
	 * to set the employee job position details
	 */
	public HashMap<Integer, Employee> addEmployeeJobPositionDetails(HashMap<Integer, Employee> employeesMap) {
		String query = "SELECT * FROM public.job_positions ORDER BY job_id ASC";
		try {
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				Iterator<Employee> empItr = employeesMap.values().iterator();
				
				while(empItr.hasNext()) {
					Employee emp = empItr.next();
					if(emp.getJobPosition().getJobId() == results.getInt("job_id")){
						emp.getJobPosition().setJobPositionDetails(
							results.getString("job_title"), results.getInt("department_id"),
							results.getFloat("hourly_pay"), results.getString("password")
						);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employeesMap;
	}
	
	/**
	 * 
	 * @param departmentsMap
	 * @param employeesMap
	 * @return hashmap of departments
	 * @implNote Queries the database or the departments, then creates the department objects.
	 * Then it iterates through the employee hashmap and adds employee to corresponding department objects
	 * 
	 */
	public HashMap<String, Department> organizeEmployeesByDepartment(HashMap<String, Department> deptsMap, 
			HashMap<Integer, Employee> empsMap) {
		try {
			if(conn != null) {
				String query = "SELECT * FROM departments ORDER BY department_id ASC";
				ResultSet results = statement.executeQuery(query);
				
				Department universalDept = new Department(0, "All Departments", 0);
				universalDept.setEmployeesList(empsMap);

				while(results.next()) {
					Department dept = new Department(
							results.getInt("department_id"), results.getString("department_name"), 
							results.getInt("department_manager_id"));
					
					Iterator<Employee> empItr = empsMap.values().iterator();
					
					while(empItr.hasNext()) {
						Employee emp = empItr.next();
						if(emp.getJobPosition().getDepartmentId()
								== results.getInt("department_id")){
							dept.addEmployeeToDepartment(emp);
						}
					}
					
					deptsMap.put(results.getString("department_name").toUpperCase(), dept);
				}
				deptsMap.put(universalDept.getDepartmentName().toUpperCase(), universalDept);	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return deptsMap;
	}
}