package main.views.home;

import java.awt.Color;

import main.views.components.EmployeeActionButton;


/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec HumanResourcesHomeView is an AbstractHomeView and includes an add employee button 
 * @version 1.1
 *
 */
public class HumanResourceHomeView extends AbstractHomeView {

	private static final long serialVersionUID = 1L;
	//private HumanResourceEmployeeView employeeDetailsView = null;

	public HumanResourceHomeView() {
		setResizable(false);
		setTitle("Employee Information System (HR)");
		initialize();
		initializeEditButtons();
	}

	private void initializeEditButtons() {
		
		addEmployeeButton = new EmployeeActionButton("Add Employee", new Color(0, 215, 120));
		addEmployeeButton.addActionListener(this);
		addEmployeeButton.enableButton();
		center_panel.add(addEmployeeButton, "cell 4 13,growx");
		
	}
}
