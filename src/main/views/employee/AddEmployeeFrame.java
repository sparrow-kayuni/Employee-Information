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
		try {
			employeeNameHeader.setText("New Employee");
		}catch(Exception err){
			err.printStackTrace();
		}
		addEditableFields();
		addSaveButton();
	}

	private static void createNewEmployee() {
		int newEmployeeId = ++App.lastEmployeeId;
		employee = new Employee("", "", newEmployeeId, "", "", 0);
		employee.setDepartmentName(deptName);
		currentDepartment = App.getDepartments().get(deptName);
		jobPosition = currentDepartment.getJobPositions().values().iterator().next();
		
	}

}
