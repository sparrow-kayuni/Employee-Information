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
	
	public static HashMap<Integer, JobPosition> loginJobPositions = null;
	
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
		loginJobPositions = new HashMap<Integer, JobPosition>();
		
		Iterator<Department> deptItr = departmentsMap.values().iterator();
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			Iterator<JobPosition> jobItr = dept.getAllJobPositions().values().iterator();
			while(jobItr.hasNext()) {
				JobPosition job = jobItr.next();
				if(job.getPassword() != null) loginJobPositions.put(job.getJobId(), job);
			}
		}
		
	}
	public static boolean isLoginPositionPresent(int jobId) {
		boolean isPresent = false;
		Iterator<JobPosition> jobItr = loginJobPositions.values().iterator();
		while(jobItr.hasNext()) {
			isPresent = (jobItr.next().getJobId() == jobId)? true: false;
		}
		
		return isPresent;
	}
	
	public static JobPosition getLoginPosition(int jobId){
		return loginJobPositions.get(jobId);
	}
	
	public static HashMap<Integer, JobPosition> getAllLoginPositions(){
		return loginJobPositions;
	}

	private static void printInfo() {
		Iterator<Department> deptItr = departmentsMap.values().iterator();
		
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			
			System.out.println(dept.getDepartmentName() + "\n-----------------------------");
			
			Iterator<JobPosition> jobItr = dept.getAllJobPositions().values().iterator();
			
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
		setDb(new PostgresDatabase());
	}
	
	
	private static void setDepartments() {
		departmentsMap = new HashMap<String, Department>();
		departmentsMap.putAll(getDb().createDepartmentsMap());
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
	
	public static Department getDepartment(String deptName){
		return departmentsMap.get(deptName);
	}
	
	public static HashMap<String, Department> getAllDepartments() {
		return departmentsMap;
	}

	public static PostgresDatabase getDb() {
		return db;
	}

	public static void setDb(PostgresDatabase db) {
		App.db = db;
	}

}

