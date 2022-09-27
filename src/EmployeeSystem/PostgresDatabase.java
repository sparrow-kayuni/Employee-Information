package EmployeeSystem;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Models.Employee;
import Models.Manager;


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
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Employee> readEmployeesTable() {
		Vector<Employee> employeesList = new Vector<Employee>();
		
		try {
			if (conn != null) {
				String query = "SELECT * FROM public.employees";
				statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				
				while(resultSet.next()) {
					employeesList.addElement(
						createEmployee(
							resultSet.getString("first_name"), resultSet.getString("surname"),
							resultSet.getInt("employee_id"), resultSet.getString("email"), 
							resultSet.getString("phone")
						)	
					);
						
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return employeesList;
	}

	private Employee createEmployee(String fName, String surname, int empId, String email, String phone) {
		return new Employee(fName, surname, empId, email, phone);
	}
	
	private Manager createManager(String fName, String surname, int empId, String email, String phone, String password) {
		Manager manager = (Manager) createEmployee(fName, surname, empId, email, phone);
		manager.setPassword(password);
		return manager;
	}
}
