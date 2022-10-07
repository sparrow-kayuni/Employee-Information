package EmployeeSystem;

import Views.HomeView;
import Views.LoginView;
import main.models.Department;
import main.models.Employee;

import java.util.HashMap;
import java.util.Iterator;

public class App{
	private static PostgresDatabase db;
	public static HashMap<Integer, Employee> allEmployeesList;
	public static HashMap<String, Department> departmentsList;
	
	public static void run() {
		connectDatabase();
		setEmployeesList();
		setDepartmentsList();
//		printInfo();
		showLoginView();
	}


	private static void printInfo() {
		System.out.println("EMPLOYEES\n------------");
		Iterator<Department> deptItr = departmentsList.values().iterator();
		
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			System.out.println(dept.getDepartmentName().toUpperCase() + "\n---------------");
			Iterator<Employee> empItr = dept.getEmployeesList().values().iterator();
			
			while(empItr.hasNext()) {
				System.out.println(empItr.next().getEmployeeInfoFormatted());
			}
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
			LoginView window = new LoginView(allEmployeesList);
			window.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void connectDatabase() {
		db = new PostgresDatabase();
	}
	
	
	public static void setEmployeesList() {
		allEmployeesList = new HashMap<Integer, Employee>();
		allEmployeesList.putAll(createEmployeesList());
		
	}
	
	
	private static HashMap<Integer, Employee>  createEmployeesList() {
		return db.generateEmployeesList();
	}
	
	private static void setDepartmentsList() {
		departmentsList = new HashMap<String, Department>();
		departmentsList.putAll(generateDepartmentsList(departmentsList, allEmployeesList));
		
	}
	
	
	private static HashMap<String, Department> generateDepartmentsList(
			HashMap<String, Department> deptList, HashMap<Integer, Employee> empList) {
		return db.organizeEmployeesByDepartment(deptList, empList);
	}


	public static void updateEmployeesList(HashMap<Integer, Employee> list) {
		allEmployeesList = list;
	}

}

