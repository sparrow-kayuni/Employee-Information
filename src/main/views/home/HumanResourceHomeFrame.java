package main.views.home;

import java.awt.Color;

import main.views.components.EmployeeActionButton;
import main.views.events.UpdateEvent;


/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec HumanResourcesHomeView is an implementation of AbstractHomeView and includes an add employee buttons 
 * @version 1.1
 *
 */
public class HumanResourceHomeFrame extends AbstractHomeFrame {

	private static final long serialVersionUID = 1L;
	//private HumanResourceEmployeeView employeeDetailsView = null;

	public HumanResourceHomeFrame() {
		setResizable(false);
		setTitle("Employee Information (HR)");
		initialize();
		initializeEditButtons();
	}

	private void initializeEditButtons() {
		addEmployeeButton = new EmployeeActionButton("Add Employee", new Color(0, 215, 120));
		addEmployeeButton.addActionListener(this);
		addEmployeeButton.enableButton();
		center_panel.add(addEmployeeButton, "cell 4 13,growx");
		
	}

	@Override
	public void onEmployeeUpdate(UpdateEvent e) {
		refreshEmployeesDisplay();
		
	}
}
