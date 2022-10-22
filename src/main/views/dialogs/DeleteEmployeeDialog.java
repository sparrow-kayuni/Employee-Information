package main.views.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.models.Employee;

public class DeleteEmployeeDialog extends AbstractUpdateChangesDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeleteEmployeeDialog(Employee emp) {
		initializeDialog(emp, "This action will permanently delete employee. Continue?");
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
