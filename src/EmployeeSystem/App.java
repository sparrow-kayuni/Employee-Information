package EmployeeSystem;

import Views.HomeView;
import Views.LoginView;
import Models.Department;
import Models.Employee;

import java.util.HashMap;
import java.util.Map;

public class App{
	private static PostgresDatabase db;
	public static HashMap<Integer, Employee> employeesList;
	public static HashMap<Integer, Department> departmentsList;
	
	public static void run() {
		connectDatabase();
		setEmployeesList();
		setDepartmentsList();
		printInfo();
		showLoginView();
	}


	private static void printInfo() {
		System.out.println("EMPLOYEES\n------------");
		for(int id = 220000; id < 220000 + employeesList.size(); id++) {
			System.out.println(employeesList.get(id).getEmployeeId() + " - " + employeesList.get(id).getFirstName() 
					+ " " + employeesList.get(id).getSurname()
					+ " - " + employeesList.get(id).getJobPosition().getJobTitle() 
//					+ " - " + employeesList.get(id).getJobPosition().getPassword()
					+ " - " + departmentsList.get(employeesList.get(id).getJobPosition().getDepartmentId()).getDepartmentName());
		}
		
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
		
	}
	
	
	private static HashMap<Integer, Employee>  createEmployeesList() {
		return db.generateEmployeesList();
	}
	
	private static void setDepartmentsList() {
		departmentsList = new HashMap<Integer, Department>();
		departmentsList.putAll(generateDepartmentsList(departmentsList, employeesList));
		
	}
	
	
	private static HashMap<Integer, Department> generateDepartmentsList(
			HashMap<Integer, Department> deptList, HashMap<Integer, Employee> empList) {
		return db.organizeEmployeesByDepartment(deptList, empList);
	}


	public static void updateEmployeesList(HashMap<Integer, Employee> list) {
		employeesList = list;
	}

}

