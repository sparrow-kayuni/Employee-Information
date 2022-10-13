package main.views.home;

import java.awt.Color;

import main.views.components.EmployeeActionButton;

public class HumanResourceHomeView extends AbstractHomeView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HumanResourceHomeView() {
		setResizable(false);
		setTitle("Employee Information System (HR)");
		initialize();
		initializeEditButtons();
	}

	private void initializeEditButtons() {
		
		editEmployeeButton = new EmployeeActionButton("Edit Employee", new Color(130, 240, 215));
		editEmployeeButton.addActionListener(this);
		editEmployeeButton.disableButton();
		center_panel.add(editEmployeeButton, "cell 5 13,grow");
		
		addEmployeeButton = new EmployeeActionButton("Add Employee", new Color(0, 215, 120));
		addEmployeeButton.addActionListener(this);
		addEmployeeButton.disableButton();
		center_panel.add(addEmployeeButton, "cell 6 0,growx");
		
	}
}
