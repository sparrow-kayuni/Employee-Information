package EmployeeSystem;

import Views.LoginView;
import Models.Employee;
import Models.Manager;

import java.util.Vector;

public class App {
	private static PostgresDatabase db;
	private static Vector<Employee> employeesList = null;
	private static Vector<Manager> managersList = null;
	
	public static void run() {
		connectDatabase();
		setEmployeesList();
		setManagersList();
		showLoginView();
	}
	
	public static void showLoginView() {
		try {
			LoginView window = new LoginView(managersList);
			window.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void connectDatabase() {
		db = new PostgresDatabase();
	}
	
	public static void setEmployeesList() {
		employeesList = getAllEmployees();
		System.out.println("EMPLOYEES\n------------");
		for(int i = 0; i < employeesList.size(); i++) {
			System.out.println(employeesList.get(i).getFirstName() + " " + employeesList.get(i).getSurname());
		}
		
	}
	
	public static void setManagersList() {
		managersList = getAllManagers();
		System.out.println("MANAGERS\n-------------");
		for(int i = 0; i < managersList.size(); i++) {
			System.out.println(managersList.get(i).getFirstName() + "\t" + managersList.get(i).getEmployeeId() +
					"\t" + managersList.get(i).getPassword());
		}
	}
	
	private static Vector<Employee> getAllEmployees() {
		return db.readEmployeesTable();
	}
	
	private static Vector<Manager> getAllManagers(){
		return db.readDepartmentTable(employeesList);
	}

}

