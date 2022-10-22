package main.views.employee;


import main.employeesystem.App;
import main.models.Employee;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AddEmployeeFrame contains empty AbstractEditEmployeeFrame
 *
 */
public class AddEmployeeFrame extends AbstractEditEmployeeFrame{

	private static final long serialVersionUID = 1L;
	private static String deptName = null;

	public AddEmployeeFrame(String dept) {
		deptName = dept;
		createNewEmployee();
		setTitle("Add New Employee");
		initializePanels();
		addEditableFields();
		addSaveButton();
	}

	private static void createNewEmployee() {
		int newEmployeeId = ++App.lastEmployeeId;
		employee = new Employee("New", "Employee", newEmployeeId, "", "", 100001);
		
		currentDepartment = App.departmentsMap.get(deptName);
		jobPosition = currentDepartment.getJobPositions().values().iterator().next();
		
	}

}
