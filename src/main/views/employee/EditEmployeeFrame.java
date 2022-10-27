package main.views.employee;

import main.models.Employee;
import main.views.events.UpdateEvent;
import main.views.listeners.EmployeeUpdateListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec EditEmployeeFrame is a concrete implementation of AbstractEditEmployeeFrame 
 * for editing existing employee information
 *
 */
public class EditEmployeeFrame extends AbstractEditEmployeeFrame implements EmployeeUpdateListener{

	private static final long serialVersionUID = 1L;
	
	public EditEmployeeFrame(Employee emp) {
		setEmployeeInformation(emp);
		setTitle("Edit Employee Details");
		initializePanels();
		addEditableFields();
		addSaveButton();
		addDeleteButton();
	}
	
	@Override
	public void onEmployeeUpdate(UpdateEvent event) {
		if(event.getSource().equals(saveChangesDialog)) {
			this.dispose();
		}
	}

}
