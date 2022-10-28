package main.views.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.employeesystem.App;
import main.models.Employee;
import main.views.events.UpdateEvent;
import main.views.listeners.EmployeeUpdateListener;

public class DeleteEmployeeDialog extends AbstractUpdateChangesDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeleteEmployeeDialog(Employee emp) {
		employee = emp;
		initializeDialog("Action will permanently delete employee. Continue?");
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okButton)) {
			
			removeEmployee(employee);
			
			App.getDb().deleteEmployee(employee);
			
			notifyListeners();
			
			this.dispose();
		}
		
		if(e.getSource().equals(cancelButton)) {
			this.dispose();
		}
		
	}
	
	private void notifyListeners() {
		//notify all update listeners
		UpdateEvent event = new UpdateEvent(this);
		for(int i = 0; i < updateListeners.size(); i++) {
			((EmployeeUpdateListener) updateListeners.get(i)).onEmployeeUpdate(event);
		}
	}

}
