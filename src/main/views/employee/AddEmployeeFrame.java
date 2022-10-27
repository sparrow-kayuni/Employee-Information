package main.views.employee;


import main.employeesystem.App;
import main.models.Employee;
import main.views.events.UpdateEvent;
import main.views.listeners.EmployeeUpdateListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AddEmployeeFrame contains empty AbstractEditEmployeeFrame
 *
 */
public class AddEmployeeFrame extends AbstractEditEmployeeFrame implements EmployeeUpdateListener{

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
		int newEmployeeId = App.lastEmployeeId + 1;
		employee = new Employee("", "", newEmployeeId, "", "", 0);
		employee.setDepartmentName(deptName);
		currentDepartment = App.getDepartments().get(deptName);
		jobPosition = currentDepartment.getJobPositions().values().iterator().next();
		
	}

	@Override
	public void onEmployeeUpdate(UpdateEvent event) {
		if(event.getSource().equals(saveChangesDialog)) {
			App.lastEmployeeId++;
			this.dispose();
		}
	}
}
