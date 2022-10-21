package main.employeesystem;

import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;
import main.views.LoginView;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author Mwiinga Kayuni 
 * @implSpec The App class contains start up logic of the application.
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
	
	public static int lastEmployeeId;
	
	/**
	 * 
	 * run() initializes the employee and department objects then displays 
	 * the login page
	 */
	
	public static void run() {
		connectDatabase();
		setDepartments();
//		printInfo();
		showLoginView();
	}
	
	private static void printInfo() {
		Iterator<Department> deptItr = departmentsMap.values().iterator();
		
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			
			System.out.println(dept.getDepartmentName() + "\n-----------------------------");
			
			Iterator<String> jobTitleItr = dept.getFilledJobPositions().values().iterator();
			
			while(jobTitleItr.hasNext()) {
				JobPosition job = dept.getJobPositions().get(jobTitleItr.next());
				
				if(job.isFilled) {
					System.out.println(job.getJobId() + " - " + job.getJobTitle() + 
							" - " + job.getEmployee().getFirstName() + " - " + job.getPassword());
				}
				
			}
		}
	}

	/**
	 * Connects App to postgres database 
	 */
	private static void connectDatabase() {
		db = new PostgresDatabase();
	}
	
	
	private static void setDepartments() {
		departmentsMap = new HashMap<String, Department>();
		departmentsMap.putAll(db.createDepartmentsMap());
	}
	
	
	public static void showLoginView() {
		try {
			LoginView window = new LoginView(departmentsMap);
			window.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static int getLastEmployeeId() {
//		return lastEmployeeId;
//	}
//	
//	public static void setLastEmployeeId(int empId) {
//		lastEmployeeId = empId;
//	}

}

