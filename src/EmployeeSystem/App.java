package EmployeeSystem;

import Views.HomeView;
import Views.LoginView;
import Models.Employee;

import java.util.HashMap;

public class App{
	private static PostgresDatabase db;
	public static HashMap<Integer, Employee> employeesList;
	
	public static void run() {
		connectDatabase();
		setEmployeesList();
		showLoginView();
	}
	
	public static void showHomeView() {
		try {
			HomeView window = new HomeView();
			window.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void showLoginView() {
		try {
			LoginView window = new LoginView(employeesList);
			window.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void connectDatabase() {
		db = new PostgresDatabase();
	}
	
	public static void setEmployeesList() {
		employeesList = new HashMap<Integer, Employee>();
		employeesList.putAll(createEmployeesList());
		
		System.out.println("EMPLOYEES\n------------");
		for(int i = 220000; i < 220000 + employeesList.size(); i++) {
			System.out.println(employeesList.get(i).getEmployeeId() + " - " + employeesList.get(i).getFirstName() 
					+ " " + employeesList.get(i).getSurname()
					+ " - " + employeesList.get(i).getJobPosition().getJobTitle() 
					+ " - " + employeesList.get(i).getJobPosition().getPassword());
		}
		
	}
	
	
	private static HashMap<Integer, Employee>  createEmployeesList() {
		return db.generateEmployeesList();
	}

	
	public static void updateEmployeesList(HashMap<Integer, Employee> list) {
		employeesList = list;
	}

}

