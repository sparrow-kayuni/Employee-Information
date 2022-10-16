package main.views.employee;

import main.models.Employee;
import main.models.JobPosition;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec AddEmployeeFrame contains empty AbstractEditEmployeeFrame
 *
 */
public class AddEmployeeFrame extends AbstractEditEmployeeFrame{

	private static final long serialVersionUID = 1L;

	public AddEmployeeFrame() {
		employee = new Employee();
		employee.setFirstName("");
		employee.setSurname("");
		employee.setEmail("");
		employee.setPhoneNumber("");
		employee.setJobId(0);
		
		JobPosition job = new JobPosition(0);
		job.setJobTitle("");
		job.setHourlyPay(0);
		
		employee.setJobPosition(job);
		
		setEmployee(employee);
		setTitle("Edit Employee Details");
		initializePanels();
		addEditableFields();
	}

}
