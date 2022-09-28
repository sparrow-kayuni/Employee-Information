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
			statement = conn.createStatement();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Vector<Employee> readEmployeesTable() {
		Vector<Employee> employeesList = new Vector<Employee>();
		
		try {
			if (conn != null) {
				String query = "SELECT * FROM public.employees";
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
	
	
	public Vector<Manager> readDepartmentTable(Vector<Employee> employeeList){
		Vector<Manager> managerList = new Vector<Manager>();
		
		try {
			if(conn != null) {
				String query = "SELECT * FROM public.departments";
				ResultSet resultSet = statement.executeQuery(query);
				
				while(resultSet.next()) {
					for(int i = 0; i < employeeList.size(); i++) {
						if(resultSet.getInt("department_manager_id") == employeeList.get(i).getEmployeeId()) {
							managerList.addElement(
									createManager(
										employeeList.get(i), resultSet.getInt("department_id"),
										resultSet.getString("department_name"), resultSet.getString("password")
									)
							);
						}
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return managerList;
	}

	
	private Employee createEmployee(String fName, String surname, int empId, String email, String phone) {
		return new Employee(fName, surname, empId, email, phone);
	}
	
	
	private Manager createManager(Employee employee, int department_id, String department_name, String password) {
		Manager manager = new Manager(employee);
		
		manager.setPassword(password);
		manager.setDepartment(department_id, department_name);
		return manager;
	}
}