package main.employeesystem;

import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;
import main.views.LoginView;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @author Mwiinga Kayuni 
 * @implSpec The App class contains
 * @version 1.1
 *
 */
public class App{
	
	private static PostgresDatabase db;

	/**
	 * 
	 * stores all department objects
	 */
	private static HashMap<String, Department> departmentsMap;
	
	public static int lastEmployeeId;
	
	public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static HashMap<String, JobPosition> loginInfo = null;
	
	/**
	 * 
	 * run() initializes the employee and department objects then displays 
	 * the login page
	 */
	
	public static void run() {
		connectDatabase();
		setDepartments();
		addLoginInfo();
		printInfo();
		showLoginView();
	}
	
	private static void addLoginInfo() {
		loginInfo = new HashMap<String, JobPosition>();
		
		Iterator<Department> deptItr = departmentsMap.values().iterator();
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			Iterator<JobPosition> jobItr = dept.getJobPositions().values().iterator();
			while(jobItr.hasNext()) {
				JobPosition job = jobItr.next();
				if(job.getPassword() != null) loginInfo.put(job.getJobTitle(), job);
			}
		}
		
	}
	
	public static JobPosition getLoginInfo(String jobTitle){
		return loginInfo.get(jobTitle);
	}

	private static void printInfo() {
		Iterator<Department> deptItr = departmentsMap.values().iterator();
		
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			
			System.out.println(dept.getDepartmentName() + "\n-----------------------------");
			
			Iterator<JobPosition> jobItr = dept.getJobPositions().values().iterator();
			
			while(jobItr.hasNext()) {
				JobPosition job = jobItr.next();
				
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
	
	public static void logInEmployee(Employee employee) {
		departmentsMap.get(employee.getDepartmentName()).isLoggedIn = true;
	}
	
	
	public static void logOutEmployee(Employee employee) {
		departmentsMap.get(employee.getDepartmentName()).isLoggedIn = false;
	}
	
	public static HashMap<String, Department> getDepartments(){
		return departmentsMap;
	}

}

