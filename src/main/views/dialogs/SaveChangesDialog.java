package main.views.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.models.Employee;
import main.views.employee.HumanResourceViewEmployeeFrame;
import main.views.factories.EmployeeFrameFactory;

public class SaveChangesDialog extends AbstractUpdateChangesDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaveChangesDialog(Employee newEmployee) {
		initializeDialog(newEmployee, "Are you sure you want to update changes made?");
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okButton)) {
			if(isValidEmployee()) {
				HumanResourceViewEmployeeFrame frame = EmployeeFrameFactory.returnToViewEmployeeFrame(employee);
				frame.setVisible(true);
				this.dispose();
			}	
		}
		
		if(e.getSource().equals(cancelButton)) {
			this.dispose();
		}
		
	}

	private boolean isValidEmployee() {
		// TODO Auto-generated method stub
		return false;
	}

}
