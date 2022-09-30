package EmployeeSystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

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
	private Statement statement;
	
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
		
		try {
			if (conn != null) {
				String query = "SELECT * FROM public.employees";
				ResultSet resultSet = statement.executeQuery(query);
				
				while(resultSet.next()) {
					employeesList.put(
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
		employeesList.putAll(addEmployeeJobPositionDetails(employeesList));

		return employeesList;
	}
	
	
	private Employee createEmployee(String fName, String surname, int empId, String email, String phone, int jobId) {
		return new Employee(fName, surname, empId, email, phone, jobId);
	}
	
	
	public HashMap<Integer, Employee> addEmployeeJobPositionDetails(HashMap<Integer, Employee> employeesList) {
		String query = "SELECT * FROM public.job_positions ORDER BY job_id ASC";
		try {
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				for(int i = 220000; i < 220000 + employeesList.size(); i++) {
					if(employeesList.get(i).getJobPosition().getJobId() == results.getInt("job_id")){
						employeesList.get(i).getJobPosition().setJobPositionDetails(
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
}