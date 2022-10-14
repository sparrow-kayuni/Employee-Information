package main.views.employee;

import java.awt.Color;

import main.models.Employee;
import main.views.components.EmployeeActionButton;

/**
 * 
 * @author Mwiinga Kayuni
 * @implSpec HumanResourcesEmployeeView extends AbstractEmployeeView and includes an edit and delete button 
 * @version 1.1
 *
 */
public class HumanResourceEmployeeView extends AbstractEmployeeView {
	
	private static final long serialVersionUID = 1L;

	public HumanResourceEmployeeView(Employee emp) {
		setEmployee(emp);
		setTitle("Employee Details");
		initialize();
		initializeEditButton();
	}

	private void initializeEditButton() {
		deleteEmployeeButton = new EmployeeActionButton("Delete Employee", new Color(215, 120, 120));
		deleteEmployeeButton.addActionListener(this);
		deleteEmployeeButton.enableButton();
		
		north_panel.add(deleteEmployeeButton, "cell 6 0 2 1,growx, aligny center");
		
		editEmployeeButton = new EmployeeActionButton("Edit Employee", new Color(0, 120, 215));
		editEmployeeButton.addActionListener(this);
		editEmployeeButton.enableButton();
		
		south_panel.add(editEmployeeButton, "cell 5 0 2 1,growx,aligny center");
		
	}
}
