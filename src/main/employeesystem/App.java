package main.employeesystem;

import main.models.Department;
import main.models.Employee;
import main.views.LoginView;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author Mwiinga Kayuni 
 * @implSpec The App class defines the entire logic of the application.
 * @implSpec This class is never instantiated, however the App.run() method is 
 * called in the main file.
 * @version 1.1
 *
 */
public class App{
	
	private static PostgresDatabase db;
	/**
	 * 
	 * stores all employee objects
	 */
	public static HashMap<Integer, Employee> allEmployeesMap;
	
	/**
	 * 
	 * stores all department objects
	 */
	public static HashMap<String, Department> departmentsMap;
	
	/**
	 * 
	 * run() initializes the employee and department objects then displays 
	 * the login page
	 */
	
	public static void run() {
		connectDatabase();
		setEmployeesList();
		setDepartmentsList();
		printInfo();
		showLoginView();
	}
	
	private static void printInfo() {
		Iterator<Department> deptItr = departmentsMap.values().iterator();
		
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			System.out.println(dept.getDepartmentName() + "\n----------------------------");
			
			Iterator<Employee> empItr = dept.getEmployeesList().values().iterator();
			
			while(empItr.hasNext()) {
				System.out.println(empItr.next().getEmployeeInfoFormatted());
			}
		}
		
	}

	/**
	 * Connects App to postgres database 
	 */
	private static void connectDatabase() {
		db = new PostgresDatabase();
	}
	
	
	/**
	 * 
	 * @return Hashmap containing all Employee objects generated from the db 
	 */
	private static HashMap<Integer, Employee>  createEmployeesList() {
		return db.generateEmployeesList();
	}
	
	
	public static void setEmployeesList() {
		allEmployeesMap = new HashMap<Integer, Employee>();
		allEmployeesMap.putAll(createEmployeesList());
		
	}
	
	/**
	 * 
	 * @return Hashmap containing all Department objects generated from the db 
	 */
	private static HashMap<String, Department> generateDepartmentsList(
			HashMap<String, Department> deptList, HashMap<Integer, Employee> empList) {
		return db.organizeEmployeesByDepartment(deptList, empList);
	}
	
	
	private static void setDepartmentsList() {
		departmentsMap = new HashMap<String, Department>();
		departmentsMap.putAll(generateDepartmentsList(departmentsMap, allEmployeesMap));
		
	}
	
	/**
	 * Creates login window object and passes 
	 */
	
	public static void showLoginView() {
		try {
			LoginView window = new LoginView(departmentsMap);
			window.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void updateEmployeesList(HashMap<Integer, Employee> list) {
		allEmployeesMap = list;
	}

}

