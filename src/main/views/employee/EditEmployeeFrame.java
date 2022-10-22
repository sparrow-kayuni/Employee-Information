package main.views.employee;

import main.models.Employee;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec EditEmployeeFrame is a concrete implementation of AbstractEditEmployeeFrame 
 * for editing existing employee information
 *
 */
public class EditEmployeeFrame extends AbstractEditEmployeeFrame {

	private static final long serialVersionUID = 1L;
	
	public EditEmployeeFrame(Employee emp) {
		setEmployeeInformation(emp);
		setTitle("Edit Employee Details");
		initializePanels();
		addEditableFields();
		addSaveButton();
		addDeleteButton();
	}

}
