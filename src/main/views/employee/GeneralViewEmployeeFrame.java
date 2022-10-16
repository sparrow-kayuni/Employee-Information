package main.views.employee;

import java.awt.event.ActionEvent;

import main.models.Employee;
import main.views.listeners.DefaultActionListener;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec GeneralViewEmployeeFrame is an implementation of AbstractViewEmployeeFrame for General Use
 *
 */
public class GeneralViewEmployeeFrame extends AbstractViewEmployeeFrame 
		implements DefaultActionListener {


	private static final long serialVersionUID = 1L;

	public GeneralViewEmployeeFrame(Employee emp) {
		setEmployee(emp);
		setTitle("Employee Details");
		initializePanels();
		addTextPanes();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(closeButton)) {
			this.dispose();
		}	
	}

}
