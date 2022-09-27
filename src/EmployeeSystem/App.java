package EmployeeSystem;

import Views.LoginView;
import Models.Employee;

import java.util.Vector;

import EmployeeSystem.PostgresDatabase;

public class App {
	private static PostgresDatabase db;
	private static Vector<Employee> employeesList = null;
	
	
	public static void run() {
		connectDatabase();
		setEmployeesList();
		showLoginView();
	}
	
	public static void showLoginView() {
		try {
			LoginView window = new LoginView();
			window.setVisible(true);
			if(window.isLoggedIn) {
				window.dispose();
				System.out.println("Done");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void connectDatabase() {
		db = new PostgresDatabase();
	}
	
	private static Vector<Employee> getAllEmployees() {
		return db.readEmployeesTable();
	}
	
	public static void setEmployeesList() {
		employeesList = getAllEmployees();
		for(int i = 0; i < employeesList.size(); i++) {
			System.out.println(employeesList.get(i).getFirstName() + " " + employeesList.get(i).getSurname());
		}
		
	}

}

