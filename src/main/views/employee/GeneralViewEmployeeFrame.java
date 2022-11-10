package main.views.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.models.Employee;
import main.views.events.CloseEvent;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec GeneralViewEmployeeFrame is an implementation of AbstractViewEmployeeFrame for General Use
 *
 */
public class GeneralViewEmployeeFrame extends AbstractViewEmployeeFrame 
		implements ActionListener {


	private static final long serialVersionUID = 1L;

	public GeneralViewEmployeeFrame(Employee emp) {
		setEmployeeInformation(emp);
		setTitle("Employee Details");
		initializePanels();
		setFrameHeader();
		initializeTextPanes();
		setTextPaneValues();
		
		closeButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(closeButton)) {
			notifyClosedListeners(CloseEvent.Event.CANCEL);
			this.dispose();
		}	
	}

}
