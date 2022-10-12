package main.views;

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
		initializeEditButton();
	}

	private void initializeEditButton() {
		
		editEmployeeButton = new EmployeeActionButton("Edit Employee", new Color(130, 240, 215), 187, 434);
		editEmployeeButton.addActionListener(this);
		editEmployeeButton.disableButton();
		
		center_panel.add(editEmployeeButton);
	}
}
