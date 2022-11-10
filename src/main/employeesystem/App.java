package main.employeesystem;

import main.models.Department;
import main.models.Employee;
import main.models.JobPosition;
import main.views.LoginFrame;
import main.views.home.AbstractHomeFrame;

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
	
	public static int lastEmployeeId = 0;
	
	public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static HashMap<Integer, JobPosition> loginJobPositions = null;

	private static AbstractHomeFrame homeView = null;
	
	private static LoginFrame loginFrame = null;
	
	/**
	 * 
	 * run() initializes the employee and department objects then displays 
	 * the login page
	 */
	
	public static void run() {
		connectDatabase();
		setDepartments();
		addManagerLoginInfo();
		showLoginView();
	}
	
	
	public static void addManagerLoginInfo() {
		loginJobPositions = new HashMap<Integer, JobPosition>();
		
		Iterator<Department> deptItr = departmentsMap.values().iterator();
		while(deptItr.hasNext()) {
			Department dept = deptItr.next();
			Iterator<JobPosition> jobItr = dept.getAllJobPositions().values().iterator();
			while(jobItr.hasNext()) {
				JobPosition job = jobItr.next();
				if(job.getPassword() != null) {
					loginJobPositions.put(job.getJobId(), job);
					System.out.println(loginJobPositions.get(job.getJobId()).getJobTitle() + 
							" - " + loginJobPositions.get(job.getJobId()).getPassword());
				}
			}
		}
		
	}
	
	//checks if job position of given job id is present
	public static boolean isLoginPositionPresent(int jobId) {
		boolean isPresent = false;
		Iterator<JobPosition> jobItr = loginJobPositions.values().iterator();
		while(jobItr.hasNext()) {
			JobPosition job = jobItr.next();
			
			isPresent = (job.getJobId() == jobId)? true: false;
			
			if(isPresent) break;
		}
		
		return isPresent;
	}
	
	
	public static JobPosition getLoginPosition(int jobId){
		return loginJobPositions.get(jobId);
	}
	
	
	public static HashMap<Integer, JobPosition> getAllLoginPositions(){
		return loginJobPositions;
	}

	/**
	 * Connects App to postgres database 
	 */
	public static void connectDatabase() {
		setDb(new PostgresDatabase());
	}
	
	
	public static void setDepartments() {
		departmentsMap = new HashMap<String, Department>();
		departmentsMap.putAll(getDb().createDepartmentsMap());
	}
	
	
	public static void showLoginView() {
		try {
			loginFrame = new LoginFrame(departmentsMap);
			loginFrame.setVisible(true);
			
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


	public static AbstractHomeFrame getHomeView() {
		return homeView;
	}
	
	public static void setHomeView(AbstractHomeFrame homeView) {
		App.homeView = homeView;
	}

}