package EmployeeSystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import Models.Department;
import Models.Employee;


/* PostgresDatabase class contains all methods involving the database.
 * 
 * 
 * */
public class PostgresDatabase {
	
	private String pgURL = "jdbc:postgresql://localhost:5432/human_resources";
	private String userName = "test";
	private String password = "123";
	private static Connection conn = null;
	private static Statement statement;
	private static int  minimumId = 220000;
	
	public PostgresDatabase() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(pgURL, userName, password);
			statement = conn.createStatement();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public HashMap<Integer, Employee>  generateEmployeesList() {
		HashMap<Integer, Employee> employeesList = new HashMap<Integer, Employee>();
		
		employeesList.putAll(addEmployees(employeesList));
		
		employeesList.putAll(addEmployeeJobPositionDetails(employeesList));

		return employeesList;
	}
	
	
	private HashMap<Integer, Employee> addEmployees(HashMap<Integer, Employee> empList) {
		try {
			if (conn != null) {
				String query = "SELECT * FROM public.employees";
				ResultSet resultSet = statement.executeQuery(query);
				
				while(resultSet.next()) {
					empList.put(
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
		return empList;
	}


	private Employee createEmployee(String fName, String surname, int empId, String email, String phone, int jobId) {
		return new Employee(fName, surname, empId, email, phone, jobId);
	}
	
	
	public HashMap<Integer, Employee> addEmployeeJobPositionDetails(HashMap<Integer, Employee> employeesList) {
		String query = "SELECT * FROM public.job_positions ORDER BY job_id ASC";
		try {
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				for(int id = minimumId; id < minimumId + employeesList.size(); id++) {
					if(employeesList.get(id).getJobPosition().getJobId() == results.getInt("job_id")){
						employeesList.get(id).getJobPosition().setJobPositionDetails(
							results.getString("job_title"), results.getInt("department_id"),
							results.getFloat("hourly_pay"), results.getString("password")
						);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employeesList;
	}
	
	
	public HashMap<Integer, Department> organizeEmployeesByDepartment(HashMap<Integer, Department> deptList, 
			HashMap<Integer, Employee> empList) {
		try {
			if(conn != null) {
				String query = "SELECT * FROM departments ";
				ResultSet results = statement.executeQuery(query);
				while(results.next()) {
					Department dept = new Department(
							results.getInt("department_id"), results.getString("department_name"), 
							results.getInt("department_manager_id"));
					
					for(int id = minimumId; id < minimumId + empList.size(); id++) {
						if(empList.get(id).getJobPosition().getDepartmentId() == results.getInt("department_id")) {
							dept.addEmployeeToDepartment(empList.get(id));
						}
					}
					
					deptList.put(results.getInt("department_id"), dept);
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return deptList;
	}
}